# Tratamento de Erro

Há basicamente dois tipos de erro:

Erros internos: conhecidos como erros do lado do cliente, são aqueles que você pode pegar pela aplicação Angular. Erro de sintaxe, referência, tipagem, etc.

Erros externos: conhecidos como erros do lado do servidor, são aqueles provenientes do servidor, sinalizados pelos status de três dígitos como 500 para erros de conexão e outros.

A forma mais comum de se capturar erros é com blocos try/catch:

```typescript
try {
    throw new Error('Um erro ocorreu');
}
catch (error) {
    console.error('Mensagem de erro', error);
}
console.log('Execução continua');
```

Por mais útil que possa ser, try/catch é limitado porque, na prática, seria necessário colocar um bloco desses em cada função da aplicação, o que não é muito eficiente.


Felizmente o Angular tem um tipo de try/catch global que podemos usar de forma centralizada para capturar todas as exceções.


## Error Handler

O Angular tem um tratamento global de erro chamado de errorHandler que provê um hook para captura de exceções de forma centralizada. Ele basicamente intercepta todos os erros que acontecem na aplicação e registra (log) todos eles e impede a aplicação de simplesmente quebrar. A cara geral desse handler é essa:

```typescript
class MeuErrorHandler implements ErrorHandler {
   handleError(error) {
     // faz alguma coisa com a exceção
   }
 }
```

Dessa forma, podemos colocar qualquer lógica dentro de handleError e manipularmos o ocorrido da forma que preferirmos.

```typescript
export class MeuErrorHandler implements ErrorHandler {
   constructor() {}
   handleError(error: Error) {
       if (Error) {
           console.log("Logando e enviando dados para monitoramento");
       } else {
           console.log("Tudo ok");
       }
   }
}
```

Se esta classe for registrada como um provider no AppModule da nossa aplicação, todas as operações que antes tinham que ser capturadas por try/catch, agora são tratadas pelo nosso handler que vai registrar cada erro, mantendo o comportamento com menos repetição de código.


Para uma lógica um pouco mais sofisticada, podemos verificar o tipo de erro, se é interno ou externo:

```typescript
export class ErroService implements ErrorHandler{
   constructor(private injector: Injector) { }
   handleError(error: any) {

       const router = this.injector.get(Router);

       if (Error instanceof HttpErrorResponse) {
           console.log(error.status);
       }
       else {
           console.error("ocorreu um erro aqui");
           router.navigate(['erro']);
       }
   
    }
}
```

Note que se o erro for interno, o usuário é redirecionado para uma página mais amigável que uma tela com um log geral do Angular.


## HttpInterceptor

Quando um erro externo ocorre, sempre é disparado um HttpErrorResponse e ele vem com algumas informações:

* Error name: nome do erro
* Error message: mensagem de erro explicada (ou não!)
* Erro status: cada tipo de erro do lado do servidor tem um código para diferenciá-lo do resto. Esses códigos são os famosos 404, 500, entre outros

Enquanto o errorHandler é excelente para tratar erros internos do Angular, ele não consegue trabalhar diretamente com requisições HTTP nas nossas aplicações. Para isso, o Angular provê uma interface onde é possível aplicar o conceito do errorHandler para as requisições HTTP.

Uma forma de tratar erros de HTTP é usando o HttpClient da API padrão do angular em combinação com os operadores RxJS, dessa forma é possível termos um tipo de try/catch específico para requisições HTTP.


Verifique o código abaixo, e veja que o método getUsuarios está com uma chamada de API errada.

```typescript
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators'
import { throwError } from 'rxjs';


@Injectable({
   providedIn: 'root'
})
export class GenericoService {
   constructor(private http: HttpClient) { }
   getUsuarios() {
       return this.http.get('http://localhost:8080/usuariossssss')
           .pipe(
               catchError(this.handleErro)
           );
   }

   getUsuario(usuarioId) {
       return this.http.get('http://localhost:8080/usuarios/' + userId)
   }

   handleErro(error: HttpErrorResponse) {
       console.log("logando erro qualquer");
       return throwError(error);
   }
}
```

Para tratar um erro desse tipo, no serviço, tivemos que importar o catchError da biblioteca RxJS, usar o método pipe para redirecionar o erro gerado pela requisição HTTP e, por fim, criar um método para tratar o erro.


Além de um erro direto, também há uma situação muito comum que é a comunicação com um servidor lento, pode ser necessário refazer a requisição até de fato ter uma resposta do servidor. Isso pode ser feito pelo uso do método retry como abaixo:

```typescript
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, retry } from 'rxjs/operators'
import { throwError } from 'rxjs';


@Injectable({
   providedIn: 'root'
})
export class GenericoService {

   constructor(private http: HttpClient) { }

   getUsuarios() {
       return this.http.get('http://localhost:8080/usuariossssss')
           .pipe(
               retry(2),
               catchError(this.handleErro)
           );
   }

   getUsuario(usuarioId) {
       return this.http.get('http://localhost:8080/usuarios/' + userId)
   }

   handleErro(error: HttpErrorResponse) {
       console.log("logando erro qualquer");
       return throwError(error);
   }
}
```

Isso garante que a aplicação vai tentar fazer a requisição pelo menos 2 vezes antes de lançar o erro.

A solução acima funciona perfeitamente, mas quando nossa aplicação cresce e tem muitos serviços ou muitas requisições em cada serviço, essa solução se torna ineficiente. Isso porque teremos que sempre copiar a função handleErro() e repetir o código de captura de erro em cada serviço, o que é impraticável.

Para resolver esse problema vamos usar o Angular HttpInterceptor!

Como o próprio nome sugere, o Angular provê uma interface chamada HttpInterceptor que pode interceptar HttpRequest e HttpResponse. Isso significa que temos acesso direto às nossas requisições para o servidor, a sintaxe geral é essa:

```typescript
interface HttpInterceptor {
   intercept(req: HttpRequest<any>, next: HttpHandler):   Observable<HttpEvent<any>>
}
```

Para usar o HttpInterceptor, podemos remover o tratamento que fizemos dentro do serviço e centralizar no interceptador.

```typescript
import { Injectable } from '@angular/core';
import {
   HttpInterceptor, HttpRequest,
   HttpHandler, HttpEvent, HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
   providedIn: 'root'
})
export class ServicoInterceptor implements HttpInterceptor {

   constructor() { }

   handleError(error: HttpErrorResponse) {
       console.log("log do erro genérico");
       return throwError(error);
   }

   intercept(req: HttpRequest<any>, next: HttpHandler):
       Observable<HttpEvent<any>> {
       return next.handle(req)
           .pipe(
               catchError(this.handleError)
           )
   };
}
```

Essa é a melhor forma de tratar erros do servidor no Angular.

