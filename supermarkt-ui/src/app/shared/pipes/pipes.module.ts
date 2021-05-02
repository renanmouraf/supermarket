import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SituacaoPedidoPipe } from './situacao-pedido.pipe';
import { TextoFavoritoPipe } from './texto-favorito.pipe';

@NgModule({
 declarations: [
   SituacaoPedidoPipe,
   TextoFavoritoPipe
 ],
 imports: [
   CommonModule
 ],
 exports: [
   SituacaoPedidoPipe,
   TextoFavoritoPipe
 ]
})
export class PipesModule { }