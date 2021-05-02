export class ItemEstoque {
    constructor(
        public id: number,
        public nome: string,
        public descricao: string,
        public quantidade: number,
        public preco: number,
        public precoPromocional: number,
        public supermercadoId: number
    ) {}
}
