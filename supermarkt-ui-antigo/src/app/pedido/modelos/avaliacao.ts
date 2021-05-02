import { Pedido } from './pedido';

export class Avaliacao {
    constructor(
        public id?: number,
        public nota?: number,
        public comentario?: string,
        public pedido?: Pedido
    ) {}
}
