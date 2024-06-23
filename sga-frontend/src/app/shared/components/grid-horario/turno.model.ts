import { VagaAula } from "./vaga-aula.model";

export class TurnoModel {
    constructor(
        public nome: string,
        public vagasAula: VagaAula[]
    ) {}
}
