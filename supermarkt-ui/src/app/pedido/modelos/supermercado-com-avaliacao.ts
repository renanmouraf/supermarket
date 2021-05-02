import { Supermercado } from '../../admin/supermercado/modelos/supermercado';

export class SupermercadoComAvaliacao {
    constructor(
        public supermercado?: Supermercado,
        public mediaDasAvaliacoes?: number
    ) {}
}
