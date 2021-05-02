export class Autenticacao {
    constructor(
        public username: string,
        public roles: string [],
        public token: string,
        public targetId: number
    ) {}
 }