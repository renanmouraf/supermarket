import { Cliente } from './cliente';

export class Entrega {
    constructor(
        public id?: number,
        public cliente?: Cliente,
        public cep?: string,
        public endereco?: string,
        public complemento?: string
    ) {}
}
