# Pipes

Um pipe permite o uso de dados como entrada para ele e, então, transforma esses dados para um formato de saída mais desejável. Ao pensar nas nossas aplicações Angular, talvez a razão mais comum para criar aplicações é para acessar e mostrar dados nas aplicações web. Entretanto, os dados podem estar em algum formato não amigável, a exemplo do formato de data.

Um  formato muito comum de data é este:

Sat Jun 20 2019 04:00:00 GMT -0800 (Pacific Standard Time)

Apesar de ser relativamente legível, há muita informação desnecessária para quem só quer saber da data. Talvez seja o caso de mostrar o mês expandido no lugar do nome abreviado. Ou talvez mudar o formato para adaptar à sua localidade, como por exemplo, Brasil.

Pipes ajudam a conseguir isso.

O Angular tem uma coleção de pipes já embutidos para tarefas comuns como o DatePipe, UpperCasePipe, LowerCasePipe, e CurrencyPipe, entre outros.

No nosso projeto vamos usar pipes embutidos e alguns pipes customizados.

Para uso de pipes, usamos o símbolo |, muito familiar para quem é habituado a usar linha de comando. No shell, o símbolo é usado para enviar a saída de um comando para o próximo, por exemplo, $history | grep git.

Em Angular, nós vamos usar o símbolo de pipe para transformar os dados dos nossos templates. Segue abaixo um exemplo do projeto.

```html
 <td class="col-content-name">{{supermercado.nome | titlecase}}</td>
```

O TitleCasePipe capitaliza a primeira letra de cada palavra e transforma o resto da palavra em caixa baixa.

## Pipes Customizados

Quando precisamos fazer alguma transformação ainda não existente nos pipes embutidos no Angular, o que fazer?


Para uma transformação específica é necessário criarmos os nossos próprios pipes.

Em um Pipe customizado, você escreve a lógica em uma função e essa lógica realiza a transformação desejada.


O Angular CLI nos auxilia na criação de pipes. Rodar o seguinte comando vai gerar um pipe chamado texto-favorito.


$ ng g pipe texto-favorito


O Angular CLI deve informar que os arquivos necessários foram gerados e que o app.module.ts foi modificado ou o module dentro do qual o pipe foi criado.


Em app.module.ts, o novo pipe vai ser importado e vai constar em declarations, assim o pipe vai estar disponível para os outros componentes dentro do próprio module. Caso queira que ele seja usado em outros modules, é necessário colocar o pipe no bloco exports.


O nome do pipe é derivado do nome que foi usado no comando generate. Assim o comando criar o arquivo texto-favorito.pipe.ts, cujo conteúdo será semelhante a este:

```typescript
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'textoFavorito'
})
export class TextoFavoritoPipe implements PipeTransform {
  transform(value: any, ...args: any[]): any {
      return null;
  }

}
```

Note que a linha de import trás o Pipe e o PipeTransform de angular core.

Em seguida, note a declaração @Pipe com o nome especificado. Como em muitas classes Angular, o export garante que o pipe seja usado em outras classes em sua aplicação Angular.

Sua lógica vai existir dentro do método transform desta classe. Essa lógica é simplesmente o que for necessário para realizar a transformação desejada.

