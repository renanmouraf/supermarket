import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
    { path: 'tipos-pagamentos', loadChildren: () => import(`./tipo-pagamento/tipo-pagamento.module`).then(m => m.TipoPagamentoModule) },
    { path: 'supermercados', loadChildren: () => import(`./supermercado/supermercado.module`).then(m => m.SupermercadoModule) },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
