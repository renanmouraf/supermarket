import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'situacaoPedido'
})
export class SituacaoPedidoPipe implements PipeTransform {

  descricaoSituacaoDoPedido = {
    REALIZADO: 'Realizado',
    PAGO: 'Pago',
    CONFIRMADO: 'Confirmado',
    PRONTO: 'Pronto',
    SAIU_PARA_ENTREGA: 'Saiu para entrega',
    ENTREGUE: 'Entregue'
  };

  transform(value: string): string {
    return this.descricaoSituacaoDoPedido[value] || value;
  }

}
