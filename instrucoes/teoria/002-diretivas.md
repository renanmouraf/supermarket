# Diretivas

Diretivas em Angular são responsáveis por prover funcionalidades em uma aplicação Angular e ajudar a transformar o Document Object Model (DOM).


Quando implementamos diretivas em uma aplicação Angular, vamos usar um dos dois tipos, diretivas de atributo e diretivas estruturais.


Uma diretiva de atributo vai simplesmente alterar o comportamento ou aparência de um elemento existente do DOM, ao passo que diretivas estruturais são usadas para modificar o layout da página através da manipulação do DOM.

## Diretivas Estruturais

Diretivas estruturais manipulam o HTML por meio do DOM. A manipulação se dá na forma de adição, remoção, ou alteração de um elemento. Você sabe que está usando uma diretiva estrutural se o nome é precedido por um asterisk, a exemplo do *ngIf.


A diretiva *ngIf é uma diretiva embutida no Angular. Essa diretiva vai avaliar uma expressão booleana e, baseada no resultado, ela mostra ou retira um elemento HTML. Acompanhe o exemplo abaixo:

```html
<p *ngIf="true">
   Se a expressão for true e ngIf for true, este conteúdo aparece  no DOM.
</p>

<p *ngIf="false">
    Se uma expressão é false e ngIf é false, este conteúdo não vai aparecer no  DOM.
</p>
```

## Diretivas de atributo

Diretivas de atributo são responsáveis por mudar a aparência do elemento ao qual elas estão sendo aplicadas, ao passo que diretivas estruturais criam ou removem elementos.


O angular também tem algumas diretivas de atributo embutidas para que possamos usar. A mais comum é a ngClass.


A ngClass é projetada para permitir a aplicação de CSS dinamicamente. A diretiva usa uma expressão para avaliar o CSS que irá aplicar.

```html
<div [ngClass]="{
  'ativo': condicao,
  'inativo': !condicao,
  'emfoco': condicao && outraCondicao,
}">
</div>
```

O exemplo acima aplica uma determinada classe CSS de acordo com alguma condição.

```html
<p [ngClass]="{strike: textApagado, bold: importante}">Texto</p>

<label>
   <input type="checkbox" [ngModel]="textApagado">
   Traço em cima do texto (aplicar classe "strike")
</label><br>

<label>
   <input type="checkbox" [ngModel]="importante">
   Texto importante (aplicar classe "bold")
</label>
```

Neste segundo exemplo, usando os checkboxes, verificamos a expressão do checkbox no ngModel e aplicamos o CSS especificado nesta classe.

## Diretivas customizadas

Diretivas são basicamente um encapsulamento de comandos para o Angular. Até componentes são uma espécie de diretiva no sentido que eles aplicam uma lógica no template.

Assim, é possível criarmos nossas próprias diretivas para manipulações mais específicas.

O primeiro passo é usar o Angular CLI para gerar a diretiva.

```
$ ng g directive acesso
```

Ao rodar o comando, o CLI vai indicar que a diretiva foi devidamente criada. O comando vai mostrar que criou o arquivo acesso.spec.ts e o acesso.ts, também modificará o app.module.ts para constar a diretiva ou o módulo para o qual foi apontado o comando de criação.

O CLI vai importar Directive do angular core e anotar sua classe com o decorador @Directive.

O código abaixo mostra um exemplo de como o arquivo deve ter ficado.

```typescript
import {Directive} from '@angular/core';

@Directive({
   selector: '[appAcesso]'
})

export class AcessoDirective {
   constructor() {}
}
```