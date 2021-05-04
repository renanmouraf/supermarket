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