import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorizacaoGuard } from '../../shared/guards/autorizacao.guard';
import { EstoqueBuscaComponent } from './estoque-busca/estoque-busca.component';
import { EstoqueFormularioComponent } from './estoque-formulario/estoque-formulario.component';


const routes: Routes = [
  {
      path: '',
      component: EstoqueBuscaComponent ,
      canActivate: [AutorizacaoGuard],
      data: { role: ['ADMIN', 'SUPERMERCADO']}
  },
  {
      path: 'novo',
      component: EstoqueFormularioComponent,
      canActivate: [AutorizacaoGuard],
      data: { role: ['ADMIN', 'SUPERMERCADO']}
  },
  {
      path: ':idItemEstoque',
      component: EstoqueFormularioComponent,
      canActivate: [AutorizacaoGuard],
      data: { role: ['ADMIN', 'SUPERMERCADO']}
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EstoqueRoutingModule { }
