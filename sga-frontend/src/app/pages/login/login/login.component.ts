import { Component } from '@angular/core';
import {UsuarioAutenticacaoModel} from "../../../shared/models/usuario-autenticacao.model";
import {LoginService} from "../login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

    username: string;
    password: string;

    usuario: UsuarioAutenticacaoModel = new UsuarioAutenticacaoModel();
    private usuarioAutenticado: boolean;
    constructor(
        private authService: LoginService
    ) {
    }

    ngOnInit(): void {
        this.username = 'teste';
        this.password ='teste';
    }

    fazerLogin( ): void{
        this.authService.login(this.usuario);
    }

}
