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
