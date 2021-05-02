export class Supermercado {
    constructor(
        public id: number,
        public cnpj: string,
        public nome: string,
        public descricao: string,
        public cep: string,
        public endereco: string,
        public taxaDeEntregaEmReais: number,
        public tempoDeEntregaMinimoEmMinutos: number,
        public tempoDeEntregaMaximoEmMinutos: number,
        public favorito: boolean
    ) {}
}
