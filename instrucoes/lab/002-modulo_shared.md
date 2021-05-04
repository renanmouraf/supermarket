# Módulo Shared

Crie o módulo shared que vai conter todas as partes do sistema que são comuns.

```
$ ng g module shared
```

## Módulo Pipes

Criar o módulo pipes que dentro de shared que vai conter os pipes que vamos usar na aplicação.


$ ng g module shared/pipes


Vamos criar agora o primeiro pipe como abaixo, que vai ser usado para apensar um ícone de coração para o supermercado quando este for favoritado.

```
$ ng g pipe shared/pipes/texto-favorito
```

Em **src/app/shared/pipes/texto-favorito.pipe.ts**, implementar seguinte:

`texto-favorito.pipe.ts`
```typescript
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
 name: 'textoFavorito'
})

export class TextoFavoritoPipe implements PipeTransform {

 transform(value: any, ...args: any[]): any {

   if (args[0]) {
    return value + ' \u2665';
   } else {
    return value;
   }
 }

}
```

Agora crie o pipe **situacao-pedido** que vai transformar o status do pedido em cada passo.

```
$ ng g pipe shared/pipes/situacao-pedido
```

Em **src/app/shared/pipes/situacao-pedido.pipe.ts**, implementar seguinte:

`situacao-pedido.pipe.ts`
```typescript
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
 name: 'situacaoPedido'
})
export class SituacaoPedidoPipe implements PipeTransform {

 descricaoSituacaoDoPedido = {
   REALIZADO: 'Realizado',
   PAGO: 'Pago',
   CONFIRMADO: 'Confirmado',
   PRONTO: 'Pronto',
   SAIU_PARA_ENTREGA: 'Saiu para entrega',
   ENTREGUE: 'Entregue'
 };

 transform(value: string): string {
   return this.descricaoSituacaoDoPedido[value] || value;
 }

}
```

Em **pipes.module.ts**, verificar se os declarations e exports estão corretos. 

Como vamos usar os pipes em outros lugares fora do módulo pipes, é necessário criar um bloco exports.

`pipes.module.ts`
```typescript
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SituacaoPedidoPipe } from './situacao-pedido.pipe';
import { TextoFavoritoPipe } from './texto-favorito.pipe';

@NgModule({
 declarations: [
   SituacaoPedidoPipe,
   TextoFavoritoPipe
 ],
 imports: [
   CommonModule
 ],
 exports: [
   SituacaoPedidoPipe,
   TextoFavoritoPipe
 ]
})
export class PipesModule { }
```

## Modelos

Crie as classes que vão servir de referência para tipagem.

```
$ ng g class shared/modelos/usuario
```

`usuario.ts`
```typescript
export class Usuario {
   constructor(
       public username: string,
       public password: string
   ) {}
}
```

```
$ ng g class shared/modelos/autenticacao
```

`autenticacao.ts`
```typescript
export class Autenticacao {
   constructor(
       public username: string,
       public roles: string [],
       public token: string,
       public targetId: number
   ) {}
}
```

## Serviços

Analogamente, crie agora uma pasta com o serviços gerais do projeto.

O primeiro vai ser o serviço centralizado de notificação que vai registrar quaisquer mensagens emitidas pelas diferentes funcionalidades.

```
$ ng g service shared/services/notificacao
```

Em **src/app/shared/services/notificacao.service.ts**:

`notificacao.service.ts`
```typescript
import { Injectable } from '@angular/core';
import { Message } from 'primeng/api';
import { BehaviorSubject, Observable } from 'rxjs';
import { publish, refCount } from 'rxjs/operators';

@Injectable()
export class NotificacaoService {

 private notificacao: BehaviorSubject<Message> = new BehaviorSubject(undefined);

 readonly notificacoes: Observable<Message> = this.notificacao.asObservable().pipe(
  publish(),
  refCount()
 );

 constructor() {}

 notificar(message: Message): void {
  this.notificacao.next(message);
 }

}
```

O segundo serviço é o de autenticação.

```
$ ng g service shared/services/autenticacao
```

`autenticacao.service.ts`
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Autenticacao } from '../modelos/autenticacao';
import { Usuario } from '../modelos/usuario';

@Injectable({
 providedIn: 'root'
})

export class AutenticacaoService {

 private API = environment.baseUrl + '/autenticacao';
 private currentUserSubject: BehaviorSubject<Autenticacao>;
 public currentUser: Observable<Autenticacao>;

 constructor(private http: HttpClient) {
   this.currentUserSubject = new BehaviorSubject<Autenticacao>(JSON.parse(localStorage.getItem('currentUser')));
   this.currentUser = this.currentUserSubject.asObservable();
 }

 public get currentUserValue(): Autenticacao {
   return this.currentUserSubject.value;
 }

 hasRole(roles: string[]): boolean {
   if (this.currentUserValue && this.currentUserValue.roles) {
     for (const role of roles) {
       if (this.currentUserValue.roles.includes(role)) {
         return true;
       }
     }
   }
   return false;
 }

 login(loginInfo: Usuario): Observable<Autenticacao> {
   return this.http.post(`${this.API}`, loginInfo)
     .pipe(map((authData: Autenticacao) => {
       if (authData && authData.token) {
         localStorage.setItem('currentUser', JSON.stringify(authData));
         this.currentUserSubject.next(authData);
       }
       return authData;
     }));
 }

 logout(): void {
   localStorage.removeItem('currentUser');
   this.currentUserSubject.next(undefined);
 }

 registraParceiro(userInfo: Usuario): Observable<number> {
   return this.http.post<number>(`${this.API}/supermercado`, userInfo);
 }

}
```

## Módulo Directives

De forma similar, crie agora o módulo específico para diretivas dentro de shared.

```
$ ng g module shared/directives
```

Crie agora a primeira diretiva, sendo esta do tipo de atributo.

```
$ ng g directive shared/directives/fade
```

Em **src/app/shared/directives/fade.directive.ts**, implemente o abaixo:

`fade.directive.ts`
```typescript
import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';

@Directive({
 selector: '[appFade]'
})
export class FadeDirective {

 constructor(private el: ElementRef, private renderer: Renderer2) {
   el.nativeElement.style.opacity = '.6';
   el.nativeElement.style.transition = '.4s opacity';
 }

 @HostListener('mouseover') mouseover(): void {
   this.renderer.setStyle(this.el.nativeElement, 'opacity', '1');
 }

 @HostListener('mouseout') mouseout(): void {
   this.renderer.setStyle(this.el.nativeElement, 'opacity', '.6');
 }

}
```

Agora vamos criar uma diretiva do tipo estrutural.

```
$ ng g directive shared/directives/tem-acesso
```

`tem-acesso.directive.ts`
```typescript
import { Directive, Input, OnInit, TemplateRef, ViewContainerRef } from '@angular/core';
import { Autenticacao } from '../modelos/autenticacao';
import { AutenticacaoService } from '../services/autenticacao.service';

@Directive({
 selector: '[appTemAcesso]'
})
export class TemAcessoDirective implements OnInit {

 private currentUser: Autenticacao;
 private isHidden = true;
 private roles: string[];

 constructor(
   private templateRef: TemplateRef<any>,
   private vcr: ViewContainerRef,
   private autenticacaoService: AutenticacaoService) { }

 ngOnInit(): void {
   this.autenticacaoService.currentUser.subscribe(user => {
     this.currentUser = user;
     this.updateView();
   });
 }

 @Input() set appTemAcesso(roles: string[]) {
   this.roles = roles;
   this.updateView();
 }

 private updateView(): void {
   if (this.autenticacaoService.hasRole(this.roles)) {
     if (this.isHidden) {
       this.vcr.createEmbeddedView(this.templateRef);
       this.isHidden = false;
     }
   } else {
     this.vcr.clear();
     this.isHidden = true;
   }
 }

}
```

Em `directives.module.ts`, verificar os declarations e exports constando as diretivas customizadas:

`directives.module.ts`
```typescript
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FadeDirective } from './fade.directive';
import { TemAcessoDirective } from './tem-acesso.directive';

@NgModule({
 declarations: [
   FadeDirective,
   TemAcessoDirective
 ],

 imports: [
   CommonModule
 ],

 exports: [
   FadeDirective,
   TemAcessoDirective
 ]
})
export class DirectivesModule { }
```

## Guards e Segurança de Rotas

```
$ ng g guard shared/guards/autorizacao
```

Nas opções que surgirão, escolha CanActivate e depois implemente o **autorização.guard.ts** desta forma:

`autorizacao.guard.ts`
```typescript
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AutenticacaoService } from 'src/app/shared/services/autenticacao.service';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';

@Injectable({
 providedIn: 'root'
})

export class AutorizacaoGuard implements CanActivate {

 constructor(private router: Router,
             private autenticacaoService: AutenticacaoService,
             private notificaoServico: NotificacaoService) { }

 canActivate(
   next: ActivatedRouteSnapshot,
   state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

     const role = next.data.role;

     if (role && this.autenticacaoService.hasRole(role)) {
       return true;
     }

     this.notificaoServico.notificar({severity: 'warn', summary: 'Aviso', detail: 'Efetue o login para ter acesso.'});
     this.router.navigate(['/login']);
     return false;
 }

}
```

## Interceptadores e Segurança por token usando JWT

```
$ ng g class shared/interceptors/jwt-interceptor
```

`jwt-interceptor.ts`
```typescript
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AutenticacaoService } from 'src/app/shared/services/autenticacao.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
   constructor(private autenticacaoService: AutenticacaoService) {}

   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       const currentUser = this.autenticacaoService.currentUserValue;
       if (currentUser && currentUser.token) {
           request = request.clone({
               setHeaders: {
                   Authorization: `Bearer ${currentUser.token}`
               }
           });
       }
       return next.handle(request);
   }
}
```

```
$ ng g class shared/interceptors/erro-servidor-interceptor
```

`erro-servidor-interceptor.ts`
```typescript
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable()
export class ErroServidorInterceptor implements HttpInterceptor {

 intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

   return next.handle(request).pipe(
     retry(1),
     catchError((error: HttpErrorResponse) => {
       if (error.status === 401) {
         // atualizar token
       } else {
         return throwError(error);
       }
     })
   );
 }
}
```

Alterar **shared.module.ts** para esta dessa forma:

```typescript
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { DirectivesModule } from './directives/directives.module';
import { ErroServidorInterceptor } from './interceptors/erro-servidor-interceptor';
import { JwtInterceptor } from './interceptors/jwt-interceptor';
import { PipesModule } from './pipes/pipes.module';

@NgModule({
 declarations: [],
 imports: [
   CommonModule,
   PipesModule,
   DirectivesModule
 ],
 exports: [
   PipesModule,
   DirectivesModule
 ],
 providers: [
   { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor , multi: true},
   { provide: HTTP_INTERCEPTORS, useClass: ErroServidorInterceptor , multi: true},
 ]
})

export class SharedModule { }
```
