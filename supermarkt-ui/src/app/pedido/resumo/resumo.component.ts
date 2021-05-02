import { Component, Input } from '@angular/core';
import { Pedido } from '../modelos/pedido';

@Component({
  selector: 'app-resumo',
  templateUrl: './resumo.component.html',
  styleUrls: ['./resumo.component.scss']
})
export class ResumoComponent {

  @Input() pedido: Pedido;

  constructor() { }

}
