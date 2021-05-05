# Template Driven vs Reactive Forms

## Template Driven

`component.ts`
```typescript
import { Component } from '@angular/core';
import { Pessoa } from './pessoa';

@Component({
 selector: 'app-root',
 templateUrl: './app.component.html',
 styleUrls: ['./app.component.css']
})
export class AppComponent {
 pessoa = new Pessoa("", 0);

 submitForm(){
   console.log(this.pessoa.nome + " tem " + this.pessoa.idade + " anos" );
 }
}
```

`component.html`
```html
<form #meuForm="ngForm" (ngSubmit) = "submitForm()">

 <label>Nome:</label>
 <input type="text" [(ngModel)]="pessoa.nome" name="nome" #nomeField="ngModel" required />

 <label>Idade:</label>
 <input type="text" [(ngModel)]="pessoa.idade"  name="idade" #idadeField="ngModel"  />

 <button type="submit" [disabled]="!meuForm.valid">Submit</button>

</form>

<p>Nome: {{pessoa.nome}}</p>
<p>Idade: {{pessoa.idade}}</p>

<p style="color:red" [hidden] = "nomeField.valid || !nomeField.touched">O nome é obrigatório!</p>
```

## Reactive

`component.ts`
```typescript
import { Component } from '@angular/core';
import { Pessoa } from './pessoa';
import { FormGroup, FormControl, Validators} from '@angular/forms'

@Component({
 selector: 'app-root',
 templateUrl: './app.component.html',
 styleUrls: ['./app.component.css']
})
export class AppComponent {
 pessoa = new Pessoa("", 0);
 meuForm = new FormGroup({
   nome: new FormControl('', Validators.required),
   idade: new FormControl('')
 })

 submitForm(){
   console.log(this.meuForm.controls.nome.value + " tem " + this.meuForm.controls.idade.value + " ano" );
 }
}
```

`component.html`
```html
<form [formGroup]="meuForm" (ngSubmit) = "submitForm()">

 <label>Nome:</label>
 <input type="text" formControlName="nome" />

 <label>Idade:</label>
 <input type="text" formControlName="idade"  />

 <button type="submit" [disabled]="!meuForm.valid">Submit</button>

</form>

<p>Nome: {{meuForm.controls.nome.value}}</p>
<p>Idade: {{meuForm.controls.idade.value}}</p>

<p style="color:red" [hidden] = "meuForm.valid || !meuForm.touched">O nome é obrigatório!</p>
```

## Resumo

Template Driven

* Fácil uso
* Bons para cenários simples
* Similar ao AngularJS (e JSF e binding padrão)
* Two way data binding (usando sintaxe [(ngModel)] )
* Código no componente é mínimo
* Verifica o formulário e os dados automaticamente
* Teste unitário é mais complexo

Reactive Forms

* Mais flexíveis, mas precisam de muita prática
* Lidam com cenários mais complexos
* Não há binding dos dados
* Mais código no componente e menos marcação HTML
* Teste unitário mais fácil por não dependerem de elementos do DOM.
* Adicionar elementos dinamicamente

Se formulários são muito importantes para a sua aplicação, você deve usar Reactive Forms. Por outro lado, se sua aplicação tem requerimentos simples e básicos para formulários como um simples login, você pode e deveria usar Template-driven Forms.