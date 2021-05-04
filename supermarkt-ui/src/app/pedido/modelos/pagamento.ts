import { Pedido } from './pedido';
import { TipoPagamento } from 'src/app/admin/supermercado/modelos/tipo-pagamento';


export class Pagamento {
  constructor(
      public id?: number,
      public valor?: number,
      public nome?: string,
      public numero?: string,
      public expiracao?: string,
      public codigo?: string,
      public situacao?: string,
      public tipoPagamento?: TipoPagamento,
      public pedido?: Pedido
  ) {}
}