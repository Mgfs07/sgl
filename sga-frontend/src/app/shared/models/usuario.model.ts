import {RolesModel} from "./roles.model";

export class UsuarioModel {
    id: number;
    matricula: string;
    nome: string;
    senha: string;
    token: string;
    expirtesIN: string;
    roles: RolesModel[];
}
