import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ConfirmationService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { TableModule } from 'primeng/table';
import { SharedModule } from 'src/app/shared/shared.module';
import { SupermercadoService } from './servicos/supermercado.service';
import { SupermercadoBuscaComponent } from './supermercado-busca/supermercado-busca.component';
import { SupermercadoFormularioComponent } from './supermercado-formulario/supermercado-formulario.component';
import { SupermercadoGradeComponent } from './supermercado-grade/supermercado-grade.component';
import { SupermercadoRoutingModule } from './supermercado-routing.module';


@NgModule({
  declarations: [
    SupermercadoFormularioComponent,
    SupermercadoGradeComponent,
    SupermercadoBuscaComponent
  ],
  imports: [
    CommonModule,
    SupermercadoRoutingModule,
    MessageModule,
    RouterModule,
    TableModule,
    ButtonModule,
    InputTextModule,
    ConfirmDialogModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  providers: [SupermercadoService, ConfirmationService]
})
export class SupermercadoModule { }
