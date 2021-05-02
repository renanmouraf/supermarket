import { TipoPagamento } from 'src/app/admin/tipo-pagamento/modelos/tipo-pagamento';
import { Pedido } from './pedido';

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
