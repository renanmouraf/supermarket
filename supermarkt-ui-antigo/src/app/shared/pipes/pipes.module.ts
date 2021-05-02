import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormaPagamentoPipe } from './forma-pagamento.pipe';
import { SituacaoPedidoPipe } from './situacao-pedido.pipe';
import { TextoFavoritoPipe } from './texto-favorito.pipe';

@NgModule({
  declarations: [
    FormaPagamentoPipe,
    SituacaoPedidoPipe,
    TextoFavoritoPipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    FormaPagamentoPipe,
    SituacaoPedidoPipe,
    TextoFavoritoPipe
  ]
})
export class PipesModule { }
