# Módulo Admin

```
$ ng g module admin
```

## Módulo Supermercado

```
$ ng g module admin/supermercado
```

### Modelos

```
$ ng g class admin/supermercado/modelos/supermercado
```

`supermercado.ts`
```typescript
export class Supermercado {
   constructor(
       public id: number,
       public cnpj: string,
       public nome: string,
       public descricao: string,
       public cep: string,
       public endereco: string,
       public taxaDeEntregaEmReais: number,
       public tempoDeEntregaMinimoEmMinutos: number,
       public tempoDeEntregaMaximoEmMinutos: number,
       public favorito: boolean
   ) {}
}
```

```
$ ng g class admin/supermercado/modelos/tipo-pagamento
```

`tipo-pagamento.ts`
```typescript
export class TipoPagamento {
   constructor(
       public id: number,
       public nome: string,
       public forma: string
   ) {}
}
```

### Serviços


```
$ ng g service admin/supermercado/servicos/supermercado
```

`supermercado.service.ts`
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoPagamento } from '../modelos/tipo-pagamento';
import { Supermercado } from '../modelos/supermercado';


@Injectable({
 providedIn: 'root'
})
export class SupermercadoService {

 private API = environment.baseUrl;
 constructor(private http: HttpClient) { }


 getSupermercados(): Observable<Supermercado[]> {
   return this.http.get<Supermercado[]>(`${this.API}/admin/supermercados`);
 }


 getByName(nome: string): Observable<Supermercado[]> {
   return this.http.get<Supermercado[]>(`${this.API}/admin/supermercados/${nome}`);
 }

 salva(supermercado: Supermercado): Observable<Supermercado> {
   if (supermercado.id) {
     return this.http.put<Supermercado>(`${this.API}/admin/supermercados/${supermercado.id}`, supermercado);

   }

   return this.http.post<Supermercado>(`${this.API}/admin/supermercados`, supermercado);
 }

 favoritar(supermercado: Supermercado): Observable<Supermercado> {
   return this.http.put<Supermercado>(`${this.API}/supermercados/${supermercado.id}/favoritar`, supermercado);

 }

 remove(supermercado: Supermercado): Observable<void> {
   return this.http.delete<void>(`${this.API}/admin/supermercados/${supermercado.id}`);

 }

 getSupermercadoById(id: number): Observable<Supermercado> {
   return this.http.get<Supermercado>(`${this.API}/supermercados/${id}`);
 }

 parceiroPorId(id: number): Observable<Supermercado> {
   return this.http.get<Supermercado>(`${this.API}/parceiros/supermercados/${id}`);

 }

 tiposPagamento(supermercado: Supermercado): Observable<TipoPagamento[]>  {
   return this.http.get<TipoPagamento[]>(`${this.API}/supermercados/${supermercado.id}/tipo-pagamento`);

 }

}
```

### Componente Supermercado Busca

```
$ ng g component admin/supermercado/supermercado-busca
```

`supermercado-busca.component.scss`
```css
h1 {
   margin-top: 0;
}

input[type=text], select {
   width: 100%;
   padding: 12px 20px;
   margin: 8px 0;
   display: block;
   border: 1px solid #ccc;
   border-radius: 4px;
   box-sizing: border-box;
}
```

`supermercado-busca.component.ts`
```typescript
import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { Supermercado } from '../modelos/supermercado';
import { SupermercadoService } from '../servicos/supermercado.service';


@Component({
 selector: 'app-supermercado-busca',
 templateUrl: './supermercado-busca.component.html',
 styleUrls: ['./supermercado-busca.component.scss'],
})
export class SupermercadoBuscaComponent implements OnInit {

 supermercados: Supermercado[];

 inputSearch: string;

 constructor(
   private supermercadoService: SupermercadoService,
   private confirmationService: ConfirmationService,
   private notificaoServico: NotificacaoService
 ) { }

 ngOnInit(): void {
   this.loadSupermercados();
 }

 dialogDelete(supermercado: Supermercado): void {

   this.confirmationService.confirm({

       message: 'Tem certeza que deseja excluir este item?',
       acceptLabel: 'Sim',
       rejectLabel: 'Não',
       header: 'Confirmação',
       icon: 'pi pi-exclamation-triangle',

       accept: () => {
           this.deleteSupermercado(supermercado);
       },
       reject: () => {
       }
   });
 }

 getSupermercadosByName(nome: string): void {
   this.supermercadoService.getByName(nome)
     .subscribe(
       supermercados => {
         this.supermercados = supermercados;
       },
       error => {
         this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
         detail: 'Não foi possível carregar os itens. Tente novamente'});
       }
     );
 }

 private loadSupermercados(): void {
   this.supermercadoService.getSupermercados()
       .subscribe(
         supermercados => {
           this.supermercados = supermercados;
         },
         error => {
           this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
           detail: 'Não foi possível carregar os itens. Tente novamente'});
         }
       );
 }

 private deleteSupermercado(supermercado: Supermercado): void {
   this.supermercadoService.remove(supermercado)
     .subscribe(
       () => {
         this.loadSupermercados();
         this.notificaoServico.notificar({severity: 'info', summary: 'Sucesso', detail: 'Operação efetuada com sucesso!'});
       },
       error => {
         this.notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Não foi possível excluir o registro.'});
       }
     );
 }

}
```

`supermercado-busca.component.html`
```html
<div class="container">

   <p-confirmDialog [style]="{width: '425px'}"></p-confirmDialog>
   <div class="p-grid">
       <div class="p-col-12">
           <h1>Pesquisa de Supermercados</h1>
       </div>

       <div class="p-col-12">
           <button pButton type="button" label="Novo supermercado" routerLink="/admin/supermercados/novo"></button>
       </div>

       <div class="p-col-6">
           <input type="text" [ngModel]="inputSearch" (ngModelChange)="getSupermercadosByName($event)" pInputText placeholder="Buscar supermercados..."/>
       </div>

       <div class="p-col-12">
           <app-supermercado-grade (delete)="dialogDelete($event)" [supermercados]="supermercados"></app-supermercado-grade>
       </div>
   </div>

</div>
```

### Componente Supermercado Grade

```
$ ng g component admin/supermercado/supermercado-grade
```

`supermercado-grade.component.scss`
```css
 .col-taxa {
    width: 20%;
 }
 
 .col-content-taxa {
    width: 20%;
 }
 
 .col-tempomin {
    width: 20%;
 }
 
 .col-content-tempomin {
    width: 20%;
 }
 
 .col-tempomax {
    width: 20%;
 }
 
 .col-content-tempomax {
    width: 20%;
 }
 
 .col-btns {
    width: 15%;
 }
 
 .col-content-btns {
    width: 15%;
 }
 
 .col-nome {
    width: 25%;
 }
 
.col-content-nome {
    width: 25%;
 }
 
.btn-editar {
    margin-right: 5px;
 }
```

`supermercado-grade.component.ts`
```typescript
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Supermercado } from '../modelos/supermercado';

@Component({
 selector: 'app-supermercado-grade',
 templateUrl: './supermercado-grade.component.html',
 styleUrls: ['./supermercado-grade.component.scss']
})

export class SupermercadoGradeComponent {
 @Input() supermercados = [];
 @Output() delete = new EventEmitter();

 constructor() { }

 handleDelete(supermercado: Supermercado): void {
   this.delete.emit(supermercado);
 }

}
```

`supermercado-grade.component.html`
```html
<p-table [value]="supermercados" [paginator]="true" [rows]="5" >

    <ng-template pTemplate="header" >
        <tr>
            <th class="col-btns"></th>
            <th class="col-nome">Nome</th>
            <th class="col-taxa">Taxa de Entrega</th>
            <th class="col-tempomin">Tempo Mín. de Entrega</th>
            <th class="col-tempomax">Tempo Máx. de Entrega</th>
        </tr>
    </ng-template>
 
    <ng-template pTemplate="body" let-supermercado>
        <tr>
 
            <td class="col-content-btns">
                <p-button class="btn-editar" icon="pi pi-pencil" pTooltip="Editar" [routerLink]="['/admin/supermercados', supermercado.id]" tooltipPosition="bottom"></p-button>
                <p-button icon="pi pi-trash" pTooltip="Excluir" tooltipPosition="bottom" (onClick)="handleDelete(supermercado)"></p-button>
            </td>
 
            <td class="col-content-nome">{{supermercado.nome | titlecase}}</td>
            <td class="col-content-taxa">{{supermercado.taxaDeEntregaEmReais | currency:'BRL':'symbol':'1.2-2'}}</td>
            <td class="col-content-tempomin">{{supermercado.tempoDeEntregaMinimoEmMinutos}}</td>
            <td class="col-content-tempomax">{{supermercado.tempoDeEntregaMaximoEmMinutos}}</td>
        </tr>
    </ng-template>
 
    <ng-template pTemplate="emptymessage">
        Nenhum registro encontrado.
    </ng-template>
 
</p-table>
```

### Componente Supermercado Formulário

```
$ ng g component admin/supermercado/supermercado-formulario
```

`supermercado-formulario.component.scss`
```css
h1 {
   margin-top: 0;
   margin-bottom: 5px;
}

input[type=text] {
   width: 100%;
   margin: 8px 0;
   display: block;
   border: 1px solid #ccc;
   border-radius: 4px;
   box-sizing: border-box;
}
```

`supermercado-formulario.component.ts`
```typescript
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { Supermercado } from '../modelos/supermercado';
import { SupermercadoService } from '../servicos/supermercado.service';


@Component({
 selector: 'app-supermercado-formulario',
 templateUrl: './supermercado-formulario.component.html',
 styleUrls: ['./supermercado-formulario.component.scss'],
})
export class SupermercadoFormularioComponent implements OnInit {

 supermercadoForm: FormGroup;
 idSupermercado: number;
 supermercado = {};

 constructor(
   private fb: FormBuilder,
   private notificaoServico: NotificacaoService,
   private supermercadoService: SupermercadoService,
   private route: ActivatedRoute,
   private router: Router
 ) { }

 ngOnInit(): void {

   this.idSupermercado = this.route.snapshot.params.idSupermercado;

   if (this.idSupermercado) {
       this.supermercadoService.getSupermercadoById(this.idSupermercado)
           .subscribe( supermercado => {
               this.updateItemForm(supermercado);
           },
           erro => {
             this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
             detail: 'Não foi possível efetuar a operação. Tente novamente'});
           }
       );
   }

   this.supermercadoForm = this.fb.group({

     id: undefined,
     nome: new FormControl(undefined, Validators.compose([Validators.required, Validators.maxLength(50)])),
     cnpj: new FormControl(undefined, Validators.compose([Validators.required])),
     descricao: new FormControl(undefined, Validators.compose([Validators.required, Validators.maxLength(50)])),
     cep: new FormControl(undefined, Validators.compose([Validators.required])),
     endereco: new FormControl(undefined, Validators.compose([Validators.required, Validators.maxLength(50)])),
     taxaDeEntregaEmReais: new FormControl(undefined, Validators.compose([Validators.required])),
     tempoDeEntregaMinimoEmMinutos: new FormControl(undefined, Validators.compose([Validators.required])),
     tempoDeEntregaMaximoEmMinutos: new FormControl(undefined, Validators.compose([Validators.required])),
   });
 }

 onSubmit(supermercado: Supermercado): void {
   this.supermercadoService.salva(supermercado)
     .subscribe(
       () => {
         this.supermercadoForm.reset();
         this.router.navigateByUrl(`admin/supermercados`);
         this.notificaoServico.notificar({severity: 'info', summary: 'Sucesso', detail: 'Operação efetuada com sucesso!'});
       },
       erro => {
         this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
         detail: 'Não foi possível efetuar a operação. Tente novamente'});
       }
     );
 }

 formInvalid(): boolean {
   return !this.supermercadoForm.valid;
 }

 private updateItemForm(supermercado: Supermercado): void {
   this.supermercadoForm.patchValue({
       tempoDeEntregaMaximoEmMinutos: supermercado.tempoDeEntregaMaximoEmMinutos,
       tempoDeEntregaMinimoEmMinutos: supermercado.tempoDeEntregaMinimoEmMinutos,
       taxaDeEntregaEmReais: supermercado.taxaDeEntregaEmReais,
       endereco: supermercado.endereco,
       cep: supermercado.cep,
       descricao: supermercado.descricao,
       cnpj: supermercado.cnpj,
       nome: supermercado.nome,
       id: supermercado.id
   });
 }

}
```

`supermercado-formulario.component.html`
```html
<div class="container">

   <form autocomplete="false" [formGroup]="supermercadoForm" (ngSubmit)="onSubmit(supermercadoForm.value)">

      <div class="p-grid">
           <div class="p-col-12">
               <h1>Cadastro de Supermercado</h1>
           </div>
      </div>

      <div class="p-grid">
           <div class="p-col-12">
               <p style="font-style:italic; font-size: 0.9em;">Os campos com o simbolo asterisco (*) são de preenchimento obrigatório</p>
           </div>
      </div>

       <div class="p-grid">
           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>Nome *:</label>
               <input type="text" pInputText formControlName="nome" maxlength="50"/>
               <p-message severity="error" [text]="supermercadoForm.controls['nome'].errors['required'] ? 'Nome é obrigatório' : supermercadoForm.controls['nome'].errors['pattern'] ? 'Só é permitido letras para esse campo' : ''"
                       *ngIf="!supermercadoForm.controls['nome'].valid&&supermercadoForm.controls['nome'].dirty"></p-message>
           </div>

           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>CNPJ *:</label>
               <input type="text" pInputText formControlName="cnpj" maxlength="50"/>
               <p-message severity="error" text="CNPJ é obrigatório" *ngIf="!supermercadoForm.controls['cnpj'].valid&&supermercadoForm.controls['cnpj'].dirty"></p-message>
           </div>

           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>Logo:</label>
               <input type="text" pInputText formControlName="descricao"/>
               <p-message severity="error" text="Logo é obrigatório" *ngIf="!supermercadoForm.controls['descricao'].valid&&supermercadoForm.controls['descricao'].dirty"></p-message>
           </div>

           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>CEP *:</label>
               <input type="text" pInputText type="text" formControlName="cep"/>
               <p-message severity="error" text="CEP é obrigatório" *ngIf="!supermercadoForm.controls['cep'].valid&&supermercadoForm.controls['cep'].dirty"></p-message>
           </div>

           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>Endereço *:</label>
               <input type="text" pInputText type="text" formControlName="endereco"/>
               <p-message severity="error" text="Endereço é obrigatório" *ngIf="!supermercadoForm.controls['endereco'].valid&&supermercadoForm.controls['endereco'].dirty"></p-message>
           </div>

           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>Taxa de Entrega *:</label>
               <input pInputText pInputText type="text" formControlName="taxaDeEntregaEmReais"/>
               <p-message severity="error" text="Taxa é obrigatório" *ngIf="!supermercadoForm.controls['taxaDeEntregaEmReais'].valid&&supermercadoForm.controls['taxaDeEntregaEmReais'].dirty"></p-message>
           </div>

           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>Tempo Mín. de Entrega *:</label>
               <input pInputText pInputText type="text" formControlName="tempoDeEntregaMinimoEmMinutos"/>
               <p-message severity="error" text="Tempo min é obrigatório" *ngIf="!supermercadoForm.controls['tempoDeEntregaMinimoEmMinutos'].valid&&supermercadoForm.controls['tempoDeEntregaMinimoEmMinutos'].dirty"></p-message>
           </div>

           <div class="p-col-4 p-lg-4 p-md-4 p-sm-4">
               <label>Tempo Máx. de Entrega *:</label>
               <input pInputText pInputText type="text" formControlName="tempoDeEntregaMaximoEmMinutos"/>
               <p-message severity="error" text="Tempo máx é obrigatório" *ngIf="!supermercadoForm.controls['tempoDeEntregaMaximoEmMinutos'].valid&&supermercadoForm.controls['tempoDeEntregaMaximoEmMinutos'].dirty"></p-message>
           </div>

           <div class="p-col-12">
               <button pButton type="submit" style="margin-right: 10px" [disabled]="formInvalid()" label="Salvar" class="ui-button-info"></button>
               <button pButton type="button" routerLink="/admin/supermercados" label="Cancelar" class="ui-button-secondary"></button>
           </div>
       </div>

   </form>
</div>
```

### Supermercado Routing e Module


```
$ ng g module admin/supermercado/supermercado-routing
```

Mova **supermercado-routing.module.ts** para fora da pasta supermercado-routing, de forma que ela fique no mesmo nível de **supermercado.module.ts**, e apague a pasta supermercado-routing.

`supermercado-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorizacaoGuard } from '../../shared/guards/autorizacao.guard';
import { SupermercadoBuscaComponent } from './supermercado-busca/supermercado-busca.component';
import { SupermercadoFormularioComponent } from './supermercado-formulario/supermercado-formulario.component';


const routes: Routes = [
 {
     path: '',
     component: SupermercadoBuscaComponent ,
     canActivate: [AutorizacaoGuard],
     data: { role: ['ADMIN']}
 },

 {
     path: 'novo',
     component: SupermercadoFormularioComponent,
     canActivate: [AutorizacaoGuard],
     data: { role: ['ADMIN']}
 },

 {
     path: ':idSupermercado',
     component: SupermercadoFormularioComponent,
     canActivate: [AutorizacaoGuard],
     data: { role: ['ADMIN']}
 },
];

@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})
export class SupermercadoRoutingModule { }
```

O arquivo supermercado.module.ts deve ficar assim:

`supermercado.module.ts`
```typescript
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ConfirmationService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { TableModule } from 'primeng/table';
import { SharedModule } from 'src/app/shared/shared.module';
import { SupermercadoService } from './servicos/supermercado.service';
import { SupermercadoBuscaComponent } from './supermercado-busca/supermercado-busca.component';
import { SupermercadoFormularioComponent } from './supermercado-formulario/supermercado-formulario.component';
import { SupermercadoGradeComponent } from './supermercado-grade/supermercado-grade.component';
import { SupermercadoRoutingModule } from './supermercado-routing.module';


@NgModule({
 declarations: [
   SupermercadoFormularioComponent,
   SupermercadoGradeComponent,
   SupermercadoBuscaComponent
 ],

 imports: [
   CommonModule,
   SupermercadoRoutingModule,
   MessageModule,
   RouterModule,
   TableModule,
   ButtonModule,
   InputTextModule,
   ConfirmDialogModule,
   FormsModule,
   ReactiveFormsModule,
   SharedModule
 ],
 providers: [SupermercadoService, ConfirmationService]
})
export class SupermercadoModule { }
```

## Admin Routing e Module

```
$ ng g module admin/admin-routing
```

Mova **admin-routing.module.ts** para fora da pasta admin-routing para ficar no mesmo nível de admin.module.ts e apague a pasta admin-routing.

`admin-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
   { path: 'supermercados', loadChildren: () => import(`./supermercado/supermercado.module`).then(m => m.SupermercadoModule) },
];

@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})
export class AdminRoutingModule { }
```

O arquivo **admin.module.ts** ficará assim ao final de todas as etapas anteriores:

`admin.module.ts`
```typescript
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { AdminRoutingModule } from './admin-routing.module';

@NgModule({
 declarations: [
 ],
 imports: [
   CommonModule,
   AdminRoutingModule,
   SharedModule
  ]
})
export class AdminModule { }
```

## Ajustando o app-routing.module.ts para o AdminModule

Incluindo o caminho '/admin'.

`app-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrosComponent } from './erros/erros/erros.component';

const routes: Routes = [
{ path: 'admin', loadChildren: () => import(`./admin/admin.module`).then(m => m.AdminModule) },
{ path: 'login', loadChildren: () => import(`./login/login.module`).then(m => m.LoginModule) },
{ path: 'error', component: ErrosComponent },
{ path: '**', component: ErrosComponent, data: { error: 404 } },
];

@NgModule({
imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }
```

Suba o sistema, faça login com o usuário "admin" e senha "123456".

Vá no menu "Supermercados" para testar o CRUD.

Observar em `SupermercadoServico.java` que o método `adiciona(Supermercado supermercado)` estabelece como padrão para o novo supermercado o usuário como sendo igual ao nome sem espaços em branco e tudo minúsculo concatenado do id e a senha é "123456".

Isso pode ser verificado em `http://localhost:8080/h2` com as consultas:

```sql
select * from usuario;

select * from usuario_authorities;
```

Se cadastrar um supermercado com nome "Ultra" com a base de dados inicial, o login será "ultra4" e a senha "123456".

## Desafio 1

O supermercado está cadastrado e é possível logar com ele, porém não é possível fazer o fluxo de compra para o supermercado cadastrado pois este não tem Tipos de Pagamento associados.

Adicionar o campo correspondente ao tipo de pagamento aceito pelo supermercado.

Estude o componente [MultiSelect](https://www.primefaces.org/primeng/showcase/#/multiselect) para fazer o link entre `TipoPagamento` e `Supermercado` através da entidade `SupermercadoTipoPagamento`.

O endpoint no backend está em `SupermercadoTipoPagamentoAPI.java` mapeado para o método `adiciona(@PathVariable("idSupermercado") Long idRestaurante, @RequestBody TipoPagamento tipoPagamento)`.

Também é possível fazer essa associação entre supermercado e tipo pagamento com o SQL abaixo, observando os devidos id's:

```sql
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (4, 5);
```

## Desafio 2

Crie um CRUD novo para Tipo de Pagamento usando a mesma lógica do CRUD de supermercados.

A entidade `TipoPagamento.java` tem apenas 2 campos, Nome e Forma, sendo Forma um Enum fixo dentro da entidade.

Todos os endpoints necessários estão em `TipoPagamentoAPI.java`.

O SQL para inserir diretamente no banco seria assim:

```sql
INSERT INTO tipo_pagamento (id, nome, forma) values (8, 'Diners', 'CARTAO_CREDITO');
```
