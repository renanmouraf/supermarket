import { FormaPagamento } from './forma-pagamento';

export class TipoPagamentoForm {
    constructor(
        public id: number,
        public nome: string,
        public forma: FormaPagamento
    ) {}
}
