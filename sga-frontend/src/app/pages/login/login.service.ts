import {EventEmitter, Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {UsuarioAutenticacaoModel} from "../../shared/models/usuario-autenticacao.model";
import {Observable} from "rxjs";
import {UsuarioModel} from "../../shared/models/usuario.model";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

    resourceUrl = environment.apiUrl + '/auth';
    username: string
    private usuarioAutenticado: boolean;
    mostrarMenuEmitter = new EventEmitter<boolean>();
    constructor(private router: Router,
                private http: HttpClient) {
    }

    authentication(entity: UsuarioAutenticacaoModel): Observable<UsuarioModel> {
        return this.http.post<UsuarioModel>(`${this.resourceUrl}/login`, entity);
    }

    public nomeUsuario(){
        return this.username
    }

    login(usuario: UsuarioAutenticacaoModel): void{
        this.authentication(usuario)
            .subscribe({
                    next: (response) => {
                        console.log(response)
                        this.usuarioAutenticado = true;
                        localStorage.setItem('token', response.token);
                        localStorage.setItem('matricula', JSON.stringify(response.matricula));
                        localStorage.setItem('nome', JSON.stringify(response.nome));
                        localStorage.setItem('roles', JSON.stringify(response.roles));
                        this.mostrarMenuEmitter.emit(true);
                        this.router.navigate(['/'])
                    },
                    error: () => {
                        this.usuarioAutenticado = false;
                        this.mostrarMenuEmitter.emit(false);
                        console.log('ERRO AO LOGAR')
                        console.log("ERROR AO AUTENTICAR")
                    }
                }
            )
    }

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('nome');
        localStorage.removeItem('matricula');
        localStorage.removeItem('roles');
        this.router.navigate(['/login']);
    }

    getToken() {
        return localStorage.getItem('token');
    }
}
