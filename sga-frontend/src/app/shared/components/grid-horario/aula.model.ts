export class AulaModel {
    constructor(
        public id: number,
        public nomeAula: string,
        public nomeCompletoDisciplina: string,
        public professor: string,
        public horaInicio: Date,
        public horaFim: Date,
        public diaSemana: number,
        public local:string
    ) {}
}