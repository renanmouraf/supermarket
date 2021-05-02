import { ItemEstoque } from '../../supermercados/modelos/item-estoque';

export class ItemPedido {
    constructor(
        public quantidade?: number,
        public observacao?: string,
        public itemEstoque?: ItemEstoque,
        public id?: number
    ) {}
}
