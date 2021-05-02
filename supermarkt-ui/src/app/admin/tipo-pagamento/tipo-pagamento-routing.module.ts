import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutorizacaoGuard } from '../../shared/guards/autorizacao.guard';
import { TipoPagamentoBuscaComponent } from './tipo-pagamento-busca/tipo-pagamento-busca.component';
import { TipoPagamentoFormularioComponent } from './tipo-pagamento-formulario/tipo-pagamento-formulario.component';


const routes: Routes = [
  {
      path: '',
      component: TipoPagamentoBuscaComponent ,
      canActivate: [AutorizacaoGuard],
      data: { role: ['ADMIN']}
  },
  {
      path: 'novo',
      component: TipoPagamentoFormularioComponent,
      canActivate: [AutorizacaoGuard],
      data: { role: ['ADMIN']}
  },
  {
      path: ':idTipo',
      component: TipoPagamentoFormularioComponent,
      canActivate: [AutorizacaoGuard],
      data: { role: ['ADMIN']}
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TipoPagamentoRoutingModule { }
