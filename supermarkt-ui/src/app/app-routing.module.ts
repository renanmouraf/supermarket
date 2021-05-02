import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrosComponent } from './erros/erros-component/erros.component';


const routes: Routes = [
  { path: 'login', loadChildren: () => import(`./login/login.module`).then(m => m.LoginModule) },
  { path: 'admin', loadChildren: () => import(`./admin/admin.module`).then(m => m.AdminModule) },
  { path: 'pedidos', loadChildren: () => import(`./pedido/pedido.module`).then(m => m.PedidoModule) },
  { path: 'supermercados', loadChildren: () => import(`./supermercados/supermercados.module`).then(m => m.SupermercadosModule) },
  { path: '', redirectTo: 'pedidos', pathMatch: 'full' },
  { path: 'error', component: ErrosComponent },
  { path: '**', component: ErrosComponent, data: { error: 404 } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
