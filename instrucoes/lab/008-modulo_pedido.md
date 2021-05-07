# Módulo Pedido

```
$ ng g module pedido
```

## Modelos

```
$ ng g class pedido/modelos/item-estoque
```

`item-estoque.ts`
```typescript
export class ItemEstoque {
   constructor(
       public id: number,
       public nome: string,
       public descricao: string,
       public quantidade: number,
       public preco: number,
       public precoPromocional: number,
       public supermercadoId: number
   ) {}
}
```

```
$ ng g class pedido/modelos/item-pedido
```

`Item-pedido.ts`
```typescript
import { ItemEstoque } from './item-estoque';

export class ItemPedido {
   constructor(
       public quantidade?: number,
       public observacao?: string,
       public itemEstoque?: ItemEstoque,
       public id?: number
   ) {}
}
```

```
$ ng g class pedido/modelos/cliente
```

`cliente.ts`
```typescript
export class Cliente {
   constructor(
       public nome?: string,
       public cpf?: string,
       public email?: string,
       public telefone?: string
   ) {}
}
```

```
$ ng g class pedido/modelos/entrega
```

`entrega.ts`
```typescript
import { Cliente } from './cliente';

export class Entrega {
   constructor(
       public id?: number,
       public cliente?: Cliente,
       public cep?: string,
       public endereco?: string,
       public complemento?: string
   ) {}
}
```


```
$ ng g class pedido/modelos/supermercado-com-avaliacao
```

`supermercado-com-avaliacao.ts`
```typescript
import { Supermercado } from '../../admin/supermercado/modelos/supermercado';

export class SupermercadoComAvaliacao {
   constructor(
       public supermercado?: Supermercado,
       public mediaDasAvaliacoes?: number
   ) {}
}
```

```
$ ng g class pedido/modelos/pedido
```

`pedido.ts`
```typescript
import { Supermercado } from '../../admin/supermercado/modelos/supermercado';
import { Entrega } from './entrega';
import { ItemPedido } from './item-pedido';

export class Pedido {
   constructor(
       public id?: number,
       public dataHora?: string,
       public situacao?: string,
       public supermercado?: Supermercado,
       public entrega?: Entrega,
       public itens?: ItemPedido[],
       public total?: number
   ) {}
}
```

```
$ ng g class pedido/modelos/media-avaliacoes
```

`media-avaliacoes.ts`
```typescript
export class MediaAvaliacoes {
   constructor(
       public supermercadoId: number,
       public media: number
   ) {}
}
```


```
$ ng g class pedido/modelos/avaliacao
```

`avaliacao.ts`
```typescript
import { Pedido } from './pedido';

export class Avaliacao {
   constructor(
       public id?: number,
       public nota?: number,
       public comentario?: string,
       public pedido?: Pedido
   ) {}
}
```


```
$ ng g class pedido/modelos/pagamento
```

`pagamento.ts`
```typescript
import { Pedido } from './pedido';
import { TipoPagamento } from 'src/app/admin/supermercado/modelos/tipo-pagamento';


export class Pagamento {
  constructor(
      public id?: number,
      public valor?: number,
      public nome?: string,
      public numero?: string,
      public expiracao?: string,
      public codigo?: string,
      public situacao?: string,
      public tipoPagamento?: TipoPagamento,
      public pedido?: Pedido
  ) {}
}
```

## Serviços

```
$ ng g service pedido/servicos/avaliacoes
```

`avaliacoes.services.ts`
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Avaliacao } from '../modelos/avaliacao';


@Injectable({
 providedIn: 'root'
})
export class AvaliacoesService {

 private API = environment.baseUrl + '/supermercados';

 constructor(
   private http: HttpClient
 ) { }

 porIdDoSupermercado(supermercadoId: string): Observable<Avaliacao[]> {
   return this.http.get<Avaliacao[]>(`${this.API}/${supermercadoId}/avaliacoes`);
 }

 salva(avaliacao: Avaliacao, supermercadoId: number): Observable<Avaliacao> {
   return this.http.post<Avaliacao>(`${this.API}/${supermercadoId}/avaliacoes`, avaliacao);
 }

}
```

```
$ ng g service pedido/servicos/pedido
```

`pedido.service.ts`
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pedido } from '../modelos/pedido';
import { SupermercadoComAvaliacao } from '../modelos/supermercado-com-avaliacao';


@Injectable({
 providedIn: 'root'
})
export class PedidoService {

 private API = environment.baseUrl;

 constructor(private http: HttpClient) { }

 getSupermercadosComAvaliacao(): Observable<SupermercadoComAvaliacao[]> {
   return this.http.get<SupermercadoComAvaliacao[]>(`${this.API}/pedidos/supermercados-avaliados`);
 }

 getSupermercadoComAvaliacaoPorId(supermercadoId: string): Observable<SupermercadoComAvaliacao> {
   return this.http.get<SupermercadoComAvaliacao>(`${this.API}/pedidos/supermercado-avaliado/${supermercadoId}`);
 }

 adiciona(pedido: Pedido): Observable<Pedido> {
   return this.http.post<Pedido>(`${this.API}/pedidos`, pedido);
 }

 porId(pedidoId: number): Observable<Pedido> {
   return this.http.get<Pedido>(`${this.API}/pedidos/${pedidoId}`);
 }

 pendentes(supermercadoId: number): Observable<Pedido[]> {
   return this.http.get<Pedido[]>(`${this.API}/parceiros/supermercados/${supermercadoId}/pedidos/pendentes`);
 }

 atualizaSituacao(pedido: Pedido): Observable<Pedido> {
   return this.http.put<Pedido>(`${this.API}/pedidos/${pedido.id}/situacao`, pedido);
 }

}
```

```
$ ng g service pedido/servicos/pagamento
```

`pagamento.service.ts`
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pagamento } from '../modelos/pagamento';


@Injectable({
 providedIn: 'root'
})
export class PagamentoService {

 private API = environment.baseUrl + '/pagamentos';

 constructor(private http: HttpClient) {
 }

 cria(pagamento: Pagamento): Observable<Pagamento> {
   return this.http.post<Pagamento>(`${this.API}`, pagamento);
 }

 confirma(pagamento: Pagamento): Observable<Pagamento> {
   return this.http.put<Pagamento>(`${this.API}/${pagamento.id}`, undefined);
 }

 cancela(pagamento: Pagamento): Observable<void> {
   return this.http.delete<void>(`${this.API}/${pagamento.id}`);
 }

}
```

```
$ ng g service pedido/servicos/estoque
```

`estoque.service.ts`
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ItemEstoque } from '../modelos/item-estoque';


@Injectable({
 providedIn: 'root'
})
export class EstoqueService {

 private API = environment.baseUrl;

 constructor(private http: HttpClient) { }

 getByName(idSupermercado: number, nome: string): Observable<ItemEstoque[]> {
   return this.http.get<ItemEstoque[]>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${nome}`);
 }

 salva(idSupermercado: number, itemEstoque: ItemEstoque): Observable<ItemEstoque> {
   itemEstoque.supermercadoId = idSupermercado;
   if (itemEstoque.id) {
     return this.http.put<ItemEstoque>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${itemEstoque.id}`, itemEstoque);
   }
   return this.http.post<ItemEstoque>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque`, itemEstoque);
 }

 remove(idSupermercado: number, idItemEstoque: number): Observable<void> {
   return this.http.delete<void>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${idItemEstoque}`);
 }

 getItemEstoqueById(idSupermercado: number, idItemEstoque: number): Observable<ItemEstoque> {
   return this.http.get<ItemEstoque>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${idItemEstoque}`);
 }

 estoquePorSupermercadoId(supermercadoId: number): Observable<ItemEstoque[]> {
   return this.http.get<ItemEstoque[]>(`${this.API}/supermercados/${supermercadoId}/estoque`);
 }

 detalhaEstoqueDoSupermercado(supermercadoId: number): Observable<ItemEstoque[]> {
   return this.http.get<ItemEstoque[]>(`${this.API}/parceiros/supermercados/${supermercadoId}/estoque/detalha`);
 }

}
```


## Componente Lista Supermercados, raíz da aplicação

```
$ ng g component pedido/lista-supermercados
```

`lista-supermercados.component.scss`
```css
.favorite_icon {
   position: relative;
   float: right;
   bottom: 35px;
   font-size: 16px;
   cursor: pointer;
}

.favoritado {
   color: red;
   text-shadow:
   -1px -1px 0 #000,
   1px -1px 0 #000,
   -1px 1px 0 #000,
   1px 1px 0 #000;
}
```


`lista-supermercados.component.ts`
```typescript
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Supermercado } from 'src/app/admin/supermercado/modelos/supermercado';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { SupermercadoService } from '../../admin/supermercado/servicos/supermercado.service';
import { SupermercadoComAvaliacao } from '../modelos/supermercado-com-avaliacao';
import { PedidoService } from '../servicos/pedido.service';


@Component({
 selector: 'app-lista-supermercados',
 templateUrl: './lista-supermercados.component.html',
 styleUrls: ['./lista-supermercados.component.scss'],
})
export class ListaSupermercadosComponent implements OnInit {

 supermercadosComAvaliacao: Array<SupermercadoComAvaliacao>;

 constructor(
   private pedidoService: PedidoService,
   private router: Router,
   private supermercadoService: SupermercadoService,
   private notificaoServico: NotificacaoService,
 ) { }

 ngOnInit(): void {
   this.pedidoService.getSupermercadosComAvaliacao()
   .subscribe(supermercados => {
     this.supermercadosComAvaliacao = supermercados;
   },

   error => {
       this.notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Não foi possível carregar os supermercados.'});
     }
   );
 }

 escolher(supermercado: Supermercado): void {
   this.router.navigateByUrl(`/pedidos/supermercado/${supermercado.id}`);
 }

 favoritar(supermercado: Supermercado): void {
   supermercado.favorito = supermercado.favorito ? false : true;
   this.supermercadoService.favoritar(supermercado).subscribe( () => {
       this.notificaoServico.notificar({severity: 'success', summary: 'Sucesso', 
       detail: supermercado.favorito ? 'Supermercado favoritado.' : 'Supermercado desfavoritado.'});
     }
   );
 }

}
```

`lista-supermercados.component.html`
```html
<div class="container">

   <div >
       <h1>Supermercados</h1>
   </div>

   <div class="p-grid">
       <div *ngFor="let supermercadoComAvaliacao of supermercadosComAvaliacao">
           <div appFade class="p-col">

               <p-card header="{{supermercadoComAvaliacao.supermercado.nome}}"
                       subheader=""
                       [style]="{width: '260px'}" styleClass="ui-card-shadow">

                   <span class='favorite_icon' *ngIf="!supermercadoComAvaliacao.supermercado.favorito"

                   (click)="favoritar(supermercadoComAvaliacao.supermercado)">&#9825;</span>

                   <span class='favorite_icon favoritado' *ngIf="supermercadoComAvaliacao.supermercado.favorito"

                   (click)="favoritar(supermercadoComAvaliacao.supermercado)">&#9829;</span>

                 
                   <p-rating [ngModel]="supermercadoComAvaliacao.mediaDasAvaliacoes" readonly="true" stars="5" [cancel]="false"></p-rating>

                   <span *ngIf="supermercadoComAvaliacao.supermercado.tempoDeEntregaMinimoEmMinutos"> Entrega: {{supermercadoComAvaliacao.supermercado.tempoDeEntregaMinimoEmMinutos}} min</span>

                   <span *ngIf="supermercadoComAvaliacao.supermercado.tempoDeEntregaMaximoEmMinutos"> - {{supermercadoComAvaliacao.supermercado.tempoDeEntregaMaximoEmMinutos}} min</span>

                   <p>Taxa: {{ supermercadoComAvaliacao.supermercado.taxaDeEntregaEmReais | currency:'BRL' }}</p>

                   <p-footer>
                       <button pButton
                       [routerLink]="['/pedidos/supermercado/', supermercadoComAvaliacao.supermercado.id]"
                       type="button" label="Escolher" icon="pi pi-check" style="margin-right: .25em"></button>
                   </p-footer>
               </p-card>
           </div>
       </div>
   </div>
</div>
```

## Pedido Routing

```
$ ng g module pedido/pedido-routing
```

Mova **pedido-routing.module.ts** para fora de pedido-routing e apague essa pasta. **pedido-routing.module.ts** deve fica no mesmo nível que pedido.module.ts

`pedido-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';


const routes: Routes = [
 {
   path: '',
   component: ListaSupermercadosComponent ,
 },
];

@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})
export class PedidoRoutingModule { }
```

Agora vamos atualizar o app-routing.module.ts com a nova rota de pedidos:

`app-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrosComponent } from './erros/erros/erros.component';


const routes: Routes = [
{ path: '', redirectTo: 'pedidos', pathMatch: 'full' },
{ path: 'pedidos', loadChildren: () => import(`./pedido/pedido.module`).then(m => m.PedidoModule) },
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


Alterar agora o **pedido.module.ts** com o PedidoRoutingModule e o ListaSupermercadosComponent. Além disso, vamos aproveitar e já configurar os providers para que o Websocket funcione e adicionar os componentes que estamos usando do PrimeNG.


`pedido.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { PedidoRoutingModule } from './pedido-routing.module';
import { PedidoService } from './servicos/pedido.service';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { rxStompConfig } from '../rx-stomp.config';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';


@NgModule({
 declarations: [
   ListaSupermercadosComponent
 ],

 imports: [
   CommonModule,
   PedidoRoutingModule,
   CardModule,
   ButtonModule,
   RatingModule,
   FormsModule
 ],

 providers: [
   PedidoService,
   {
     provide: InjectableRxStompConfig,
     useValue: rxStompConfig
   },
   {
     provide: RxStompService,
     useFactory: rxStompServiceFactory,
     deps: [InjectableRxStompConfig]
   }
 ]
})
export class PedidoModule { }
```

Ao final, `localhost:4200/` vai estar acessível com uma lista dos supermercados disponíveis.

## Componente Supermercado e listagem de produtos

```
$ ng g component pedido/supermercado
```

`supermercado.component.scss` permanece vazio.

`supermercado.component.ts`
```typescript
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Avaliacao } from 'src/app/pedido/modelos/avaliacao';
import { AvaliacoesService } from 'src/app/pedido/servicos/avaliacoes.service';
import { PedidoService } from 'src/app/pedido/servicos/pedido.service';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { ItemEstoque } from '../modelos/item-estoque';
import { EstoqueService } from '../servicos/estoque.service';
import { Cliente } from '../modelos/cliente';
import { Entrega } from '../modelos/entrega';
import { ItemPedido } from '../modelos/item-pedido';
import { Pedido } from '../modelos/pedido';
import { SupermercadoComAvaliacao } from '../modelos/supermercado-com-avaliacao';


@Component({
 selector: 'app-supermercado',
 templateUrl: './supermercado.component.html',
 styleUrls: ['./supermercado.component.scss']
})
export class SupermercadoComponent implements OnInit {

 supermercadoComAvaliacao: SupermercadoComAvaliacao;
 avaliacoes: Avaliacao[];
 estoqueProdutos: ItemEstoque[];
 pedido: Pedido;
 itemDoPedidoEscolhido: ItemPedido;
 adicionandoItemAoPedido = false;
 displayModalPedido = false;
 displayModalEntrega = false;

 constructor(
   private router: Router,
   private route: ActivatedRoute,
   private avaliacoesService: AvaliacoesService,
   private pedidoService: PedidoService,
   private estoqueService: EstoqueService,
   private notificaoServico: NotificacaoService
 ) { }

 ngOnInit(): void {
   const supermercadoId = this.route.snapshot.params.supermercadoId;

   this.pedidoService.getSupermercadoComAvaliacaoPorId(supermercadoId)
     .subscribe(supermercado => {
       this.supermercadoComAvaliacao = supermercado;
       this.pedido = new Pedido();
       this.pedido.supermercado = this.supermercadoComAvaliacao.supermercado;
       this.pedido.entrega = new Entrega();
       this.pedido.entrega.cliente = new Cliente();
       this.pedido.itens = [];
     }, error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
       detail: 'Erro ao carregar dados do supermercado.'})
     );

   this.avaliacoesService.porIdDoSupermercado(supermercadoId)
     .subscribe(avaliacoes => {
       this.avaliacoes = avaliacoes;
   },
   error => {
       this.notificaoServico.notificar({severity: 'warn', summary: 'Aviso', detail: 'Este supermercado ainda não foi avaliado.'});
     }
   );

   this.estoqueService.estoquePorSupermercadoId(supermercadoId)
     .subscribe(estoqueProdutos => {
       this.estoqueProdutos = estoqueProdutos;
     }, error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
     detail: 'Erro ao carregar lista de produtos.'})
     );
 }

 escolheItem(itemEstoque: ItemEstoque): void {
   const indice = this.pedido.itens.findIndex(i => i.itemEstoque.id === itemEstoque.id);

   if (indice < 0) {
     this.itemDoPedidoEscolhido = { quantidade: 1, observacao: '', itemEstoque};
     this.adicionandoItemAoPedido = true;
   } else {
     this.itemDoPedidoEscolhido = Object.assign({}, this.pedido.itens[indice]);
   }
   this.showHideDialogPedido();
 }

 editaItemDoPedido(itemPedido: ItemPedido): void {
   this.itemDoPedidoEscolhido = Object.assign({}, itemPedido);
   this.showHideDialogPedido();
 }

 removeItemDoPedido(itemPedido: ItemPedido): void {
   this.pedido.itens = this.pedido.itens.filter(i => i.itemEstoque.id !== itemPedido.itemEstoque.id);
   this.itemDoPedidoEscolhido = undefined;
   this.adicionandoItemAoPedido = false;
 }

 fazPedido(): void {
   this.pedido.supermercado = this.supermercadoComAvaliacao.supermercado;
   this.pedido.entrega.cliente = new Cliente();
   this.showHideDialogEntrega();
 }

 salvaItemNoPedido(): void {

   if (this.adicionandoItemAoPedido) {
     this.pedido.itens.push(this.itemDoPedidoEscolhido);
   } else if (this.itemDoPedidoEscolhido) {
     const indice = this.pedido.itens.findIndex(i => i.itemEstoque.id === this.itemDoPedidoEscolhido.itemEstoque.id);
     this.pedido.itens[indice] = this.itemDoPedidoEscolhido;
   }

   this.itemDoPedidoEscolhido = undefined;
   this.adicionandoItemAoPedido = false;
 }

 calculaSubTotal(itemPedido: ItemPedido): number {
   const item = itemPedido.itemEstoque;
   const preco = item.precoPromocional || item.preco;
   return itemPedido.quantidade * preco;
 }

 totalDoPedido(): number {
   let total = this.supermercadoComAvaliacao.supermercado.taxaDeEntregaEmReais || 0;

   if (this.pedido.itens) {
     this.pedido.itens.forEach(item => {
       total += this.calculaSubTotal(item);
     });
   }
   return total;
 }

 registraEntrega(): void {
   this.pedidoService.adiciona(this.pedido)
   .subscribe(pedido => {
     this.router.navigateByUrl(`pedidos/${pedido.id}/pagamento`);
     this.showHideDialogEntrega();
   });
 }

 showHideDialogPedido(): void {
   this.displayModalPedido = (this.displayModalPedido ? false : true);
 }

 showHideDialogEntrega(): void {
   this.displayModalEntrega = (this.displayModalEntrega ? false : true);
 }

}
```

`supermercado.component.html`
```html
<div class="container">

    <div class="p-grid">
 
        <div class="p-col-12 p-lg-2 p-md-2">
 
            <div *ngIf="supermercadoComAvaliacao">
                <h3>Pedido</h3>
 
                <div *ngFor="let itemPedido of pedido.itens">
                    <hr>
                    <p>{{ itemPedido.quantidade }}x {{ itemPedido.itemEstoque.nome }}
                        {{ calculaSubTotal(itemPedido) | currency:'BRL' }}<br>
 
                        <span class="text-muted"><small>{{ itemPedido.observacao }}</small></span>
                    </p>
 
                    <div class="p-grid">
                        <div class="p-col">
 
                            <button pButton style="margin-right: 10px"
 
                            (click)="editaItemDoPedido(itemPedido)"
 
                            label="Editar" class="ui-button-secondary"></button>
 
                            <button pButton (click)="removeItemDoPedido(itemPedido)"
                            label="Remover" class="ui-button-secondary"></button>
                        </div>
                    </div>
                </div>
 
                <hr>
 
                <p>Taxa de entrega:
                {{ supermercadoComAvaliacao.supermercado.taxaDeEntregaEmReais ? (supermercadoComAvaliacao.supermercado.taxaDeEntregaEmReais | currency:'BRL') : 'Grátis' }}</p>
                <p class="font-weight-bolder">Total: {{ totalDoPedido() | currency:'BRL' }}</p>
                <button pButton (click)="fazPedido()" label="Fazer Pedido"></button>
            </div>
        </div>
 
        <div class="p-col-12 p-lg-10 p-md-10">
 
            <div *ngIf="supermercadoComAvaliacao">
                <div>
                    <div>
 
                        <h2>{{supermercadoComAvaliacao.supermercado.nome | textoFavorito:supermercadoComAvaliacao.supermercado.favorito }}</h2>
 
                        <p>{{ supermercadoComAvaliacao.supermercado.descricao }}</p>
 
                        <p-rating [ngModel]="supermercadoComAvaliacao.mediaDasAvaliacoes" readonly="true" stars="5" [cancel]="false"></p-rating>
 
                        <p>
                            <span *ngIf="supermercadoComAvaliacao.supermercado.tempoDeEntregaMinimoEmMinutos"> Entrega: {{supermercadoComAvaliacao.supermercado.tempoDeEntregaMinimoEmMinutos}} min</span>
 
                            <span *ngIf="supermercadoComAvaliacao.supermercado.tempoDeEntregaMaximoEmMinutos"> - {{supermercadoComAvaliacao.supermercado.tempoDeEntregaMaximoEmMinutos}} min</span>
                        </p>
                        <p>Taxa: {{ supermercadoComAvaliacao.supermercado.taxaDeEntregaEmReais | currency:'BRL' }}</p>
 
                    </div>
                </div>
 
                <p-tabView>
                    <p-tabPanel header="Produtos">
                        <div class="p-grid">
                            <div *ngFor="let produto of estoqueProdutos" >
                                <div class="p-col-3" > 
                                    <p-card header="{{produto.nome | uppercase }}"
                                            subheader="{{produto.descricao }}"
                                            [style]="{width: '260px'}" styleClass="ui-card-shadow">
                                        <p *ngIf="!produto.precoPromocional"> {{produto.preco | currency:'BRL'  }}</p>
                                        <p *ngIf="produto.precoPromocional"> De: <del> {{produto.preco | currency:'BRL'  }} </del></p>
                                        <p *ngIf="produto.precoPromocional"> Por: {{produto.precoPromocional | currency:'BRL'  }}</p>
 
                                        <p-footer>
                                            <button pButton
                                            (click)="escolheItem(produto)"
                                            type="button" label="Escolher" style="margin-right: .25em"></button>
                                        </p-footer>
                                    </p-card>
                                </div>
                            </div>
                        </div>
                    </p-tabPanel>
 
                    <p-tabPanel *ngIf="avaliacoes" header="Avaliações" rightIcon="pi pi-star">
                                <p-rating [ngModel]="supermercadoComAvaliacao.mediaDasAvaliacoes" readonly="true" stars="5" [cancel]="false"></p-rating>
                                <p>{{supermercadoComAvaliacao.mediaDasAvaliacoes | number:'1.1-1' }} de {{avaliacoes.length}} {{avaliacoes.length > 1 ? 'avaliações' : 'avaliação'}}</p>
                                <div *ngFor="let avaliacao of avaliacoes">
                                    <p>{{ avaliacao.nota }} • {{ avaliacao.comentario }}</p>
                                </div>
 
                    </p-tabPanel>
                </p-tabView>
            </div>
        </div>
 
    </div>
 
 </div>
 
 <div *ngIf="itemDoPedidoEscolhido">
 
    <p-dialog header="{{ itemDoPedidoEscolhido.itemEstoque.nome }}" [(visible)]="displayModalPedido" [modal]="true"
        [responsive]="true" [style]="{width: '350px', minWidth: '200px'}" [minY]="70">
 
            <form #itemDoPedidoEscolhidoForm="ngForm" (ngSubmit)="salvaItemNoPedido()">
 
                <div class="p-grid" style="margin: 5px">
 
                    <div class="p-col-12 p-field" style="margin: 5px">
                        <label for="quantidade">QUANTIDADE:</label>
                        <input type="number" id="quantidade" name="quantidade"
                                [(ngModel)]="itemDoPedidoEscolhido.quantidade" pInputText>
                    </div>
 
                    <div class="p-col-12 p-field" style="margin: 5px">
                        <label for="observacao" >OBSERVAÇÃO:</label>
                        <textarea id="observacao" name="observacao"
                            [(ngModel)]="itemDoPedidoEscolhido.observacao" pInputTextarea></textarea>
                    </div>
 
                    <div class="p-col-12" style="margin: 5px">
                        <button pButton type="submit" style="margin-right: 10px"
                            [disabled]="!itemDoPedidoEscolhidoForm.form.valid"
                            label="{{adicionandoItemAoPedido ? 'Adicionar':'Atualizar'}}"></button>
                        <button pButton type="button"
                        (click)="showHideDialogPedido()" label="Cancelar"></button>
                    </div>
 
                </div>
            </form>
 
    </p-dialog>
 </div>
 
 <p-dialog *ngIf="pedido" header="Dados da Entrega" [(visible)]="displayModalEntrega" [modal]="true"
    [responsive]="true">
 
        <form #entregaForm="ngForm" (ngSubmit)="registraEntrega()" style="width: 250px">
 
          <fieldset>
            <legend>Dados pessoais</legend>
 
            <div class="p-grid" style="margin: 5px">
                <div class="p-col-12 p-field" style="margin: 5px">
                    <label for="nome">Nome:</label>
                    <input required id="nome" name="nome" maxlength="100"
                            [(ngModel)]="pedido.entrega.cliente.nome" pInputText>
                </div>
 
                <div class="p-col-12 p-field" style="margin: 5px">
                    <label for="cpf">CPF:</label>
                    <input required id="cpf" name="cpf" maxlength="15"
                            [(ngModel)]="pedido.entrega.cliente.cpf" pInputText>
                </div>
 
                <div class="p-col-12 p-field" style="margin: 5px">
                    <label for="email">Email:</label>
                    <input required type="email" id="email" name="email" maxlength="100"
                            [(ngModel)]="pedido.entrega.cliente.email" pInputText>
                </div>
 
 
                <div class="p-col-12 p-field" style="margin: 5px">
                    <label for="telefone">Telefone:</label>
                    <input required id="telefone" name="telefone" maxlength="16"
                            [(ngModel)]="pedido.entrega.cliente.telefone" pInputText>
                </div>
            </div>
          </fieldset>
 
          <fieldset>
            <legend>Local de entrega</legend>
            <div class="p-grid" style="margin: 5px">
                <div class="p-col-12 p-field" style="margin: 5px">
                    <label for="cep">CEP:</label>
                    <input required id="cep" name="cep" maxlength="10"
                            [(ngModel)]="pedido.entrega.cep" pInputText>
                </div>
 
                <div class="p-col-12 p-field" style="margin: 5px">
                    <label for="endereco">Endereço:</label>
                    <textarea required id="endereco" name="endereco"
                            [(ngModel)]="pedido.entrega.endereco" pInputTextarea></textarea>
                </div>
 
                <div class="p-col-12 p-field" style="margin: 5px">
                        <label for="complemento">Complemento:</label>
                        <textarea id="complemento" name="complemento"
                            [(ngModel)]="pedido.entrega.complemento" pInputTextarea></textarea>
                </div>
            </div>
          </fieldset>
 
        
            <div class="p-grid" style="margin: 5px">
                <div class="p-col-12" style="margin: 5px">
                    <button pButton type="submit" label="Confirmar" style="margin-right: 10px"></button>
                    <button pButton type="button" (click)="showHideDialogEntrega()" label="Cancelar"></button>
                </div>
            </div>
 
        </form>
 </p-dialog>
```

Agora atualizamos **pedido-routing.module.ts** para constar o componente supermercado e o pedido.module.ts com o mesmo componente e os módulos necessários do primeng e o SharedModule que contém diretivas e pipes necessários.

`pedido-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { SupermercadoComponent } from './supermercado/supermercado.component';

const routes: Routes = [
{
  path: '',
  component: ListaSupermercadosComponent ,
},

{
 path: 'supermercado/:supermercadoId',
 component: SupermercadoComponent
},
];

@NgModule({
imports: [RouterModule.forChild(routes)],
exports: [RouterModule]
})
export class PedidoRoutingModule { }
```

`pedido.module.ts` após as atualizações do SupermercadoComponent deve ficar assim:

`pedido.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { PedidoRoutingModule } from './pedido-routing.module';
import { PedidoService } from './servicos/pedido.service';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { rxStompConfig } from '../rx-stomp.config';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { SupermercadoComponent } from './supermercado/supermercado.component';
import { SharedModule } from '../shared/shared.module';
import { TabViewModule } from 'primeng/tabview';
import { DialogModule } from 'primeng/dialog';


@NgModule({
 declarations: [
   ListaSupermercadosComponent,
   SupermercadoComponent
 ],

 imports: [
   CommonModule,
   PedidoRoutingModule,
   CardModule,
   ButtonModule,
   RatingModule,
   FormsModule,
   SharedModule,
   TabViewModule,
   DialogModule,
 ],

 providers: [
   PedidoService,
   {
     provide: InjectableRxStompConfig,
     useValue: rxStompConfig
   },
   {
     provide: RxStompService,
     useFactory: rxStompServiceFactory,
     deps: [InjectableRxStompConfig]
   }
 ]
})
export class PedidoModule { }
```

## Componente Resumo

```
$ ng g component pedido/resumo
```

`resumo.component.scss` permanece vazio.

`resumo.component.ts`
```typescript
import { Component, Input } from '@angular/core';
import { Pedido } from '../modelos/pedido';

@Component({
 selector: 'app-resumo',
 templateUrl: './resumo.component.html',
 styleUrls: ['./resumo.component.scss']
})
export class ResumoComponent {

 @Input() pedido: Pedido;

 constructor() { }

}
```

`resumo.component.html`
```html
<div *ngIf="pedido">
   <h3>Resumo do pedido</h3>
   <div *ngFor="let itemPedido of pedido.itens">
     <hr>
     <p *ngIf="itemPedido.itemEstoque.precoPromocional">{{ itemPedido.quantidade }}x {{ itemPedido.itemEstoque.nome }}
       <span>{{ itemPedido.quantidade * itemPedido.itemEstoque.precoPromocional | currency:'BRL' }}</span><br>
       <span><small>{{ itemPedido.observacao }}</small></span></p>
     <p *ngIf="!itemPedido.itemEstoque.precoPromocional">{{ itemPedido.quantidade }}x {{ itemPedido.itemEstoque.nome }}
         <span>{{ itemPedido.quantidade * itemPedido.itemEstoque.preco | currency:'BRL' }}</span><br>
         <span><small>{{ itemPedido.observacao }}</small></span></p>
   </div>
   <hr>
   <p>Taxa de entrega: {{ pedido.supermercado?.taxaDeEntregaEmReais ? (pedido.supermercado?.taxaDeEntregaEmReais | currency:'BRL') : 'Grátis' }}</p>
   <p class="font-weight-bold">Total: {{ pedido.total | currency:'BRL' }}</p>
</div>
```

## Componente Pagamento

```
$ ng g component pedido/pagamento
```

`pagamento.component.scss`
```css
input[type=text], input[type=month], select {
   width: 100%;
   padding: 12px 20px;
   margin: 8px 0;
   display: block;
   border: 1px solid #ccc;
   border-radius: 4px;
   box-sizing: border-box;
}
```

`pagamento.component.ts`
```typescript
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SelectItem } from 'primeng/api';
import { SupermercadoService } from 'src/app/admin/supermercado/servicos/supermercado.service';
import { PedidoService } from 'src/app/pedido/servicos/pedido.service';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { Pagamento } from '../modelos/pagamento';
import { Pedido } from '../modelos/pedido';
import { PagamentoService } from '../servicos/pagamento.service';


@Component({
 selector: 'app-pagamento',
 templateUrl: './pagamento.component.html',
 styleUrls: ['./pagamento.component.scss']
})
export class PagamentoComponent implements OnInit {

 pedido: Pedido;
 tiposPagamento: SelectItem[] = [];
 pagamento: Pagamento;

 constructor(
   private route: ActivatedRoute,
   private router: Router,
   private pagamentoService: PagamentoService,
   private pedidoService: PedidoService,
   private supermercadoService: SupermercadoService,
   private notificaoServico: NotificacaoService
 ) { }

 ngOnInit(): void {
   const pedidoId = this.route.snapshot.params.pedidoId;

   this.pedidoService.porId(pedidoId)
     .subscribe((pedido: Pedido) => {

       this.pedido = pedido;
       this.pagamento = new Pagamento();
       this.pagamento.pedido = pedido;
       this.pagamento.valor = pedido.total;

       this.supermercadoService.tiposPagamento(pedido.supermercado)
         .subscribe(tiposPagamento => {
           tiposPagamento.forEach(tipo => {
             this.tiposPagamento.push({label: tipo.nome, value: tipo});
           });
         }
           , error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
           detail: 'Erro ao carregar tipos de pagamento.'}));
     }
     , error => {
       this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
       detail: 'Erro ao inicializar pedido.'});
     }
     );
 }

 criaPagamento(): void {
   this.pagamentoService.cria(this.pagamento)
     .subscribe(pagamento => {
       this.pagamento = pagamento;
     }
     , error => {
       this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
       detail: 'Erro ao criar pagamento.'});
     }
     );
 }

 confirmaPagamento(): void {
   this.pagamentoService.confirma(this.pagamento)
     .subscribe(pagamento => this.router.navigateByUrl(`pedidos/${pagamento.pedido.id}/situacao`),
      error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Erro na confirmação de pagamento.'}));
 }

 cancelaPagamento(): void {
   this.pagamentoService.cancela(this.pagamento)
     .subscribe(() => this.router.navigateByUrl(``),
     error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Não foi possível cancelar o pagamento.'}));
 }

}
```

`pagamento.component.html`
```html
<div class="container">

 <div class="p-grid">

     <div class="p-col-12 p-lg-2">
         <app-resumo [pedido]="pedido"></app-resumo>
     </div>

     <div *ngIf="pagamento" class="p-col-12 p-lg-10">

       <form *ngIf="pagamento.situacao !== 'CRIADO'" #pagamentoForm="ngForm" (ngSubmit)="criaPagamento()">

         <h3>Dados do pagamento</h3>

         <div class="p-grid">


           <div class="p-col-11">
             <label for="tipoPagamento">Tipo de pagamento</label>
             <div class="p-col-4" style="margin: 0px -8px">
               <p-dropdown required id="tipoPagamento" name="tipoPagamento"
                   [options]="tiposPagamento" [(ngModel)]="pagamento.tipoPagamento"></p-dropdown>
             </div>
           </div>

           <div class="p-col-12 p-lg-12 p-md-12">
             <label for="nome">Nome no cartão</label>
             <input pInputText type="text" required id="nome" name="nome"
               [(ngModel)]="pagamento.nome" placeholder="Nome como impresso no cartão" size="10">
           </div>

           <div class="p-col-12 p-lg-5 p-md-5">
             <label for="numero">Número do cartão</label>
             <input pInputText type="text" required id="numero" name="numero"
               [(ngModel)]="pagamento.numero"
               placeholder="0000 0000 0000 0000" size="50">
           </div>

           <div class="p-col-12 p-lg-4 p-md-4">
             <label for="expiracao">Data de expiração</label>

             <input pInputText type="month" required id="expiracao" name="expiracao"
               [(ngModel)]="pagamento.expiracao" size="50">
           </div>

           <div class="p-col-12 p-lg-3 p-md-3">
             <label for="codigo">Código de Segurança</label>

             <input pInputText type="text" required id="codigo" name="codigo"
               [(ngModel)]="pagamento.codigo"
               placeholder="000" size="24">
           </div>

           <div class="p-col-12">
             <button pButton *ngIf="!pagamento.situacao" type="submit"
               label="Criar pagamento"></button>
           </div>


         </div>
       </form>

       <div *ngIf="pagamento.situacao === 'CRIADO'">
         <dl>
           <dt>Cartão</dt>
           <dd>{{ pagamento.numero.substring(0, 5) }} XXXX XXXX XXXX</dd>
           <dt>Valor</dt>
           <dd>{{ pedido.total | currency:'BRL' }}</dd>
         </dl>

         <div class="p-grid" style="margin: 5px">
           <div class="p-col-12" style="margin: 5px">
             <button pButton (click)="confirmaPagamento()" label="Confirmar pagamento"
                 style="margin-right: 10px"></button>
             <button pButton (click)="cancelaPagamento()" label="Cancelar pagamento"></button>
           </div>
         </div>
       </div>
     </div>

 </div>

</div>
```

Atualizamos novamente **pedido-routing.module.ts** para constar a rota de pagamento e **pedido.module.ts** conforme segue.

`pedido-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { SupermercadoComponent } from './supermercado/supermercado.component';
import { PagamentoComponent } from './pagamento/pagamento.component';


const routes: Routes = [
{
  path: '',
  component: ListaSupermercadosComponent ,
},

{
 path: 'supermercado/:supermercadoId',
 component: SupermercadoComponent
},

{
 path: ':pedidoId/pagamento',
 component: PagamentoComponent
},
];

@NgModule({
imports: [RouterModule.forChild(routes)],
exports: [RouterModule]
})
export class PedidoRoutingModule { }
```

`pedido.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { PedidoRoutingModule } from './pedido-routing.module';
import { PedidoService } from './servicos/pedido.service';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { rxStompConfig } from '../rx-stomp.config';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { SupermercadoComponent } from './supermercado/supermercado.component';
import { SharedModule } from '../shared/shared.module';
import { TabViewModule } from 'primeng/tabview';
import { DialogModule } from 'primeng/dialog';
import { ResumoComponent } from './resumo/resumo.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { DropdownModule } from 'primeng/dropdown';


@NgModule({
 declarations: [
   ListaSupermercadosComponent,
   SupermercadoComponent,
   ResumoComponent,
   PagamentoComponent
 ],

 imports: [
   CommonModule,
   PedidoRoutingModule,
   CardModule,
   ButtonModule,
   RatingModule,
   FormsModule,
   SharedModule,
   TabViewModule,
   DialogModule,
   DropdownModule
 ],

 providers: [
   PedidoService,
   {
     provide: InjectableRxStompConfig,
     useValue: rxStompConfig
   },

   {
     provide: RxStompService,
     useFactory: rxStompServiceFactory,
     deps: [InjectableRxStompConfig]
   }
 ]
})
export class PedidoModule { }
```

## Componente Situação


```
$ ng g component pedido/situacao
```

`situacao.component.scss` permanece vazio.

`situacao.component.ts`
```typescript
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RxStompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs';
import { Avaliacao } from 'src/app/pedido/modelos/avaliacao';
import { AvaliacoesService } from 'src/app/pedido/servicos/avaliacoes.service';
import { PedidoService } from 'src/app/pedido/servicos/pedido.service';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { Pedido } from '../modelos/pedido';


@Component({
 selector: 'app-situacao',
 templateUrl: './situacao.component.html',
 styleUrls: ['./situacao.component.scss']
})
export class SituacaoComponent implements OnInit, OnDestroy {

 private topicSubscription: Subscription;

 pedido: Pedido;
 avaliacao: Avaliacao;

 constructor(
   private route: ActivatedRoute,
   private rxStompService: RxStompService,
   private pedidoService: PedidoService,
   private avaliacoesService: AvaliacoesService,
   private notificaoServico: NotificacaoService
 ) { }

 ngOnInit(): void {
   const pedidoId = this.route.snapshot.params.pedidoId;
   this.pedidoService.porId(pedidoId)
     .subscribe(pedido => this.pedido = pedido,
       error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
       detail: 'Não foi possível carregar o resumo do pedido.'}));

   this.topicSubscription = this.rxStompService.watch(`/pedidos/${pedidoId}/situacao`).subscribe((message: Message) => {
     const pedido = JSON.parse(message.body);
     this.pedido.situacao = pedido.situacao;
   });
   this.avaliacao = new Avaliacao();
 }

 ngOnDestroy(): void {
   this.topicSubscription.unsubscribe();
 }

 salvaAvaliacao(): void {
   this.avaliacao.pedido = this.pedido;
   this.avaliacoesService.salva(this.avaliacao, this.pedido.supermercado.id)
     .subscribe(avaliacao => this.avaliacao = avaliacao, error => this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
     detail: 'Erro ao salvar a avaliação do pedido.'}));
 }

}
```

`situacao.component.html`
```html
<div class="container">
 
 <div class="p-grid">

   <div class="p-col-12 p-lg-2 p-md-2">
     <app-resumo [pedido]="pedido"></app-resumo>
   </div>

   <div *ngIf="pedido" class="p-col-12 p-lg-10 p-md-10">

     <h2>Acompanhe o Pedido</h2>
     <h1><span>{{ pedido.situacao | situacaoPedido }}</span></h1>

      <div *ngIf="pedido.situacao == 'ENTREGUE' && !avaliacao.id">
       <h3>Avalie o pedido</h3>
       <form #avaliacaoForm="ngForm" (ngSubmit)="salvaAvaliacao()">
         <div>
           <p-rating [(ngModel)]="avaliacao.nota" stars="5" [cancel]="false" id="nota" name="nota"></p-rating>
         </div>
         <div>
           <textarea [(ngModel)]="avaliacao.comentario" id="comentario" name="comentario"></textarea>
         </div>
         <button pButton type="submit" [disabled]="!avaliacaoForm.form.valid" label="Avaliar"></button>
       </form>
     </div>

      <div *ngIf="pedido.situacao == 'ENTREGUE' && avaliacao.id">
       <p>Avaliação devidamente registrada. Muito obrigado!</p>
     </div>

    </div>

 </div>

</div>
```

Atualizar `pedido-routing.module.ts` para constar SituacaoComponent.

`pedido-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { SupermercadoComponent } from './supermercado/supermercado.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { SituacaoComponent } from './situacao/situacao.component';
const routes: Routes = [

{
  path: '',
  component: ListaSupermercadosComponent ,
},

{
 path: 'supermercado/:supermercadoId',
 component: SupermercadoComponent
},

{
 path: ':pedidoId/pagamento',
 component: PagamentoComponent
},

{
 path: ':pedidoId/situacao',
 component: SituacaoComponent
}
];

@NgModule({
imports: [RouterModule.forChild(routes)],
exports: [RouterModule]
})
export class PedidoRoutingModule { }
```

`pedido.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { PedidoRoutingModule } from './pedido-routing.module';
import { PedidoService } from './servicos/pedido.service';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { rxStompConfig } from '../rx-stomp.config';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { SupermercadoComponent } from './supermercado/supermercado.component';
import { SharedModule } from '../shared/shared.module';
import { TabViewModule } from 'primeng/tabview';
import { DialogModule } from 'primeng/dialog';
import { ResumoComponent } from './resumo/resumo.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { DropdownModule } from 'primeng/dropdown';
import { SituacaoComponent } from './situacao/situacao.component';


@NgModule({
 declarations: [
   ListaSupermercadosComponent,
   SupermercadoComponent,
   ResumoComponent,
   PagamentoComponent,
   SituacaoComponent
 ],

 imports: [
   CommonModule,
   PedidoRoutingModule,
   CardModule,
   ButtonModule,
   RatingModule,
   FormsModule,
   SharedModule,
   TabViewModule,
   DialogModule,
   DropdownModule
 ],

 providers: [
   PedidoService,
   {
     provide: InjectableRxStompConfig,
     useValue: rxStompConfig
   },

   {
     provide: RxStompService,
     useFactory: rxStompServiceFactory,
     deps: [InjectableRxStompConfig]
   }
 ]
})
export class PedidoModule { }
```
