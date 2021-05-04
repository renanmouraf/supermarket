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