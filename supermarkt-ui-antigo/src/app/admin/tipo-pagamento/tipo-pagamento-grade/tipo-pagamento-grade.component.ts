import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TipoPagamento } from '../modelos/tipo-pagamento';

@Component({
  selector: 'app-tipo-pagamento-grade',
  templateUrl: './tipo-pagamento-grade.component.html',
  styleUrls: ['./tipo-pagamento-grade.component.scss']
})
export class TipoPagamentoGradeComponent {

  @Input() tipos = [];

  @Output() delete = new EventEmitter();

  constructor() { }

  handleDelete(tipo: TipoPagamento): void {
    this.delete.emit(tipo);
  }
}
