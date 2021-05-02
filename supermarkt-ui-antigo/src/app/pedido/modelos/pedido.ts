import { Supermercado } from '../../admin/supermercado/modelos/supermercado';
import { Entrega } from './entrega';
import { ItemPedido } from './item-pedido';

export class Pedido {
    constructor(
        public id?: number,
        public dataHora?: string,
        public situacao?: string,
        public supermercado?: Supermercado,
        public entrega?: Entrega,
        public itens?: ItemPedido[],
        public total?: number
    ) {}
}

