import { CommonModule } from '@angular/common';
import { ErrorHandler, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NotificacaoService } from '../shared/services/notificacao.service';
import { ErrosComponent } from './erros-component/erros.component';
import { ErrosHandler } from './erros-handler';
import { ErrosService } from './servicos/erros.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
  ],
  declarations: [
    ErrosComponent
  ],
  providers: [
    NotificacaoService,
    ErrosService,
    {
      provide: ErrorHandler,
      useClass: ErrosHandler,
    },
  ]
})
export class ErrosModule { }
