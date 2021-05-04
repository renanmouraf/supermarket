# Módulo Supermercados

```
$ ng g module supermercados
```

## Componente Pedido Pendente

```
$ ng g component supermercados/pedido-pendente
```

`pedido-pendente.component.scss`
```css
h2 {
   margin-top: 10;
   margin-bottom: 10px;
}
```

`pedido-pendente.component.ts`
```typescript
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RxStompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs';
import { Pedido } from 'src/app/pedido/modelos/pedido';
import { NotificacaoService } from 'src/app/shared/services/notificacao.service';
import { PedidoService } from '../../pedido/servicos/pedido.service';


@Component({
 selector: 'app-pedido-pendente',
 templateUrl: './pedido-pendente.component.html',
 styleUrls: ['./pedido-pendente.component.scss']
})
export class PedidoPendenteComponent implements OnInit, OnDestroy {

 private topicSubscription: Subscription;

 pendentes: Pedido[];

 constructor(private route: ActivatedRoute,
             private rxStompService: RxStompService,
             private pedidosService: PedidoService,
             private notificaoServico: NotificacaoService
             ) {
 }

 ngOnInit(): void {
   const supermercadoId = this.route.snapshot.params.supermercadoId;
   this.pedidosService.pendentes(supermercadoId)
     .subscribe(pedidosPendentes => this.pendentes = pedidosPendentes);
   this.topicSubscription = this.rxStompService.watch(`/parceiros/supermercados/${supermercadoId}/pedidos/pendentes`)
     .subscribe((message: Message) => {
       const pedido = JSON.parse(message.body);
       this.pendentes.push(pedido);
     });
 }

 ngOnDestroy(): void {
   this.topicSubscription.unsubscribe();
 }

 confirma(pedido: Pedido): void {
   pedido.situacao = 'CONFIRMADO';

   this.pedidosService.atualizaSituacao(pedido)
   .subscribe(
     () => {}
     , error => {
       this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
       detail: 'Erro ao notificar de pedido.'});
     }
   );
 }

 avisaPronto(pedido: Pedido): void {
   pedido.situacao = 'PRONTO';

   this.pedidosService.atualizaSituacao(pedido)
     .subscribe(
       () => {}
       , error => {
         this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
         detail: 'Erro ao notificar de pedido.'});
       }
     );
 }

 avisaSaiu(pedido: Pedido): void {
   pedido.situacao = 'SAIU_PARA_ENTREGA';
   this.pedidosService.atualizaSituacao(pedido)
   .subscribe(
     () => {}
     , error => {
       this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
       detail: 'Erro ao notificar de pedido.'});
     }
   );
 }

 avisaEntregue(pedido: Pedido): void {
   pedido.situacao = 'ENTREGUE';

   this.pedidosService.atualizaSituacao(pedido)
   .subscribe(
     () => {}
     , error => {
       this.notificaoServico.notificar({severity: 'error', summary: 'Erro',
       detail: 'Erro ao notificar de pedido.'});
     }
   );
 }

}
```

`pedido-pendente.component.html`
```html
<div class="container p-grid p-col-12 p-lg-9 p-md-9" *ngIf="pendentes?.length">

   <div class="p-col-12 p-lg-9 p-md-9">
       <h2>Pedidos Pendentes</h2>
   </div>

   <div *ngFor="let pedido of pendentes">

       <div class="p-col-12 p-lg-9 p-md-9">

           <h1>{{ pedido.situacao | situacaoPedido }}</h1>

           <p><strong>Cliente:</strong> {{ pedido.entrega.cliente.nome }}</p>

           <p><strong>Tel:</strong> <a href="tel:{{ pedido.entrega.cliente.telefone }}">{{ pedido.entrega.cliente.telefone }}</a></p>

           <p><strong>Endereço:</strong> {{ pedido.entrega.endereco }} {{ pedido.entrega.complemento }}</p>

           <div *ngFor="let itemPedido of pedido.itens">

           <p>{{ itemPedido.quantidade }}x {{ itemPedido.itemEstoque.nome }}
               <p><small>{{ itemPedido.observacao }}</small></p>
           </div>

           <button pButton *ngIf="pedido.situacao == 'PAGO'" (click)="confirma(pedido)" label="Confirmar"></button>
           <button pButton *ngIf="pedido.situacao == 'CONFIRMADO'" (click)="avisaPronto(pedido)" label="Pronto!"></button>
           <button pButton *ngIf="pedido.situacao == 'PRONTO'" (click)="avisaSaiu(pedido)" label="Em rota"></button>
           <button pButton *ngIf="pedido.situacao == 'SAIU_PARA_ENTREGA'" (click)="avisaEntregue(pedido)" label="Entregue"></button>

       </div>
   </div>
</div>

<div *ngIf="!pendentes?.length">
   <h2>Não há pedidos pendentes</h2>
</div>
```

## Supermercados Routing e Module

```
$ ng g module supermercados/supermercados-routing
```

Mover **supermercados-routing.module.ts** para fora da pasta _supermercados-routing_ no mesmo nível de **supermercados.module.ts** e depois apagar a pasta _supermercados-routing_.

`supermercados-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorizacaoGuard } from '../shared/guards/autorizacao.guard';
import { PedidoPendenteComponent } from './pedido-pendente/pedido-pendente.component';


const routes: Routes = [
 {
   path: ':supermercadoId/pedidos/pendentes',
   component: PedidoPendenteComponent,
   canActivate: [AutorizacaoGuard],
   data: { role: ['SUPERMERCADO', 'ADMIN']}
 },
];

@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})
export class SupermercadosRoutingModule { }
```

Por fim, **supermercados.module.ts** deve ficar assim:

`supermercados.module.ts`
```typescript
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { ButtonModule } from 'primeng/button';
import { rxStompConfig } from 'src/app/rx-stomp.config';
import { SharedModule } from '../shared/shared.module';
import { PedidoPendenteComponent } from './pedido-pendente/pedido-pendente.component';
import { SupermercadosRoutingModule } from './supermercados-routing.module';


@NgModule({
 declarations: [
   PedidoPendenteComponent,
   ],
 imports: [
   CommonModule,
   SupermercadosRoutingModule,
   ButtonModule,
   SharedModule
 ],

 providers: [
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
export class SupermercadosModule { }
```

Finalmente, atualizar **app-routing.module.ts** com o SupermercadosModule no caminho "/supermercados":

`app-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrosComponent } from './erros/erros/erros.component';

const routes: Routes = [
{ path: '', redirectTo: 'pedidos', pathMatch: 'full' },
{ path: 'supermercados', loadChildren: () => import(`./supermercados/supermercados.module`).then(m => m.SupermercadosModule) },
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

## Desafio

O supermercado cadastrado no módulo admin acessa o sistema e, caso você tenha feito o desafio do módulo admin, também tem tipos de pagamento associados.

Obs.: Caso não tenha feito o desafio do módulo admin, lembre que é possível fazer essa associação entre supermercado e tipo pagamento com o SQL abaixo:

```sql
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (4, 5);
```

Porém, ainda falta adicionar itens de estoque ao supermercado novo.

Fazer um CRUD correspondente à entidade `ItemEstoque`.

Os endpoints necessários estão em `ItemEstoqueAPI.java`;

Também é possível inserir um item de estoque diretamente pelo banco com o SQL abaixo, observando o devido `supermercado_id`: 

```sql
INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (90, 'Cerveja Heineken', 'Cerveja Heineken 600ml', 10, 3.5, 2.5, 4);
```
