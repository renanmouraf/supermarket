import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ConfirmationService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DropdownModule } from 'primeng/dropdown';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import { RadioButtonModule } from 'primeng/radiobutton';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';
import { EstoqueService } from '../servicos/estoque.service';
import { EstoqueBuscaComponent } from './estoque-busca/estoque-busca.component';
import { EstoqueFormularioComponent } from './estoque-formulario/estoque-formulario.component';
import { EstoqueGradeComponent } from './estoque-grade/estoque-grade.component';
import { EstoqueRoutingModule } from './estoque-routing.module';


@NgModule({
  declarations: [EstoqueBuscaComponent, EstoqueFormularioComponent, EstoqueGradeComponent],
  imports: [
    MessagesModule,
    EstoqueRoutingModule,
    MessageModule,
    RadioButtonModule,
    RouterModule,
    TableModule,
    TooltipModule,
    ButtonModule,
    CalendarModule,
    DropdownModule,
    InputMaskModule,
    InputTextModule,
    CommonModule,
    ConfirmDialogModule,
    FormsModule,
    ReactiveFormsModule,
    ToastModule,
  ],
  providers: [EstoqueService, ConfirmationService]
})
export class EstoqueModule { }
