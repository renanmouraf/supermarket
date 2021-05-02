import * as StackTraceParser from 'error-stack-parser';

export class Erro {
    constructor(
        public name: string,
        public appId: string,
        public user: string,
        public time: string,
        public id: string,
        public url: string,
        public status: string,
        public message: string,
        public stack: StackTraceParser.StackFrame[]
    ) {}
}
