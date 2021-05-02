import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';
import { ButtonModule } from 'primeng/button';
import { rxStompConfig } from 'src/app/rx-stomp.config';
import { SharedModule } from '../shared/shared.module';
import { PedidoPendenteComponent } from './pedido-pendente/pedido-pendente.component';
import { SupermercadosRoutingModule } from './supermercados-routing.module';


@NgModule({
  declarations: [
    PedidoPendenteComponent,
    ],
  imports: [
    CommonModule,
    SupermercadosRoutingModule,
    ButtonModule,
    SharedModule
  ],
  providers: [
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
export class SupermercadosModule { }
