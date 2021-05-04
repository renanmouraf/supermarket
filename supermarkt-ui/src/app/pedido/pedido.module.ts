import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaSupermercadosComponent } from './lista-supermercados/lista-supermercados.component';
import { PedidoRoutingModule } from './pedido-routing.module';
import { PedidoService } from './servicos/pedido.service';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { rxStompConfig } from '../rx-stomp.config';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { SupermercadoComponent } from './supermercado/supermercado.component';
import { SharedModule } from '../shared/shared.module';
import { TabViewModule } from 'primeng/tabview';
import { DialogModule } from 'primeng/dialog';
import { ResumoComponent } from './resumo/resumo.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { DropdownModule } from 'primeng/dropdown';
import { SituacaoComponent } from './situacao/situacao.component';


@NgModule({
 declarations: [
   ListaSupermercadosComponent,
   SupermercadoComponent,
   ResumoComponent,
   PagamentoComponent,
   SituacaoComponent
 ],

 imports: [
   CommonModule,
   PedidoRoutingModule,
   CardModule,
   ButtonModule,
   RatingModule,
   FormsModule,
   SharedModule,
   TabViewModule,
   DialogModule,
   DropdownModule
 ],

 providers: [
   PedidoService,
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
export class PedidoModule { }