import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formaPagamento'
})
export class FormaPagamentoPipe implements PipeTransform {

  descricaoDasFormasDePagamento = {
    CARTAO_CREDITO: 'Cartão de Crédito',
    CARTAO_DEBITO: 'Cartão de Débito',
    VALE_ALIMENTACAO: 'Vale Alimentação'
  };

  transform(value: string): string {
    return this.descricaoDasFormasDePagamento[value] || value;
  }

}
