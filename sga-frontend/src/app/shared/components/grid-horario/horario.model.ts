import { AulaModel } from "./aula.model";

export class HorarioModel {
    constructor(
        public ator: string,
        public curso: string,
        public periodo: string,
        public aulas: AulaModel[]
    ) {}
}
