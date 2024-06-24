import {OnInit} from '@angular/core';
import {Component} from '@angular/core';
import {LayoutService} from './service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    modelUsuarioLogado: any[] = [];
    modelUsuarioNaoLogado: any[] = [];

    constructor(public layoutService: LayoutService) {
    }

    ngOnInit() {
        this.modelUsuarioLogado = [
            {
                label: 'Home',
                items: [
                    // {label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/']},
                    {
                        label: 'Horários de Aula',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/horarios-aluno']
                    },
                ]
            },
            {
                label: 'Opções',
                icon: 'pi pi-fw pi-briefcase',
                items: [
                    {
                        label: 'Equipamentos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/equipamentos']
                    },
                    {
                        label: 'Disciplinas',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/disciplinas']
                    },
                    {
                        label: 'Coordenadorias',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/coordenadorias']
                    },
                    {
                        label: 'Coordenadores',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/coordenadores']
                    },
                    {
                        label: 'Alunos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/alunos']
                    },
                    {
                        label: 'Períodos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/periodos']
                    },
                    {
                        label: 'Locais',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local']
                    },
                    {
                        label: 'Alocacao',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/alocacao']
                    },
                    {
                        label: 'Professores',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/professores']
                    },
                    {
                        label: 'Eventos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/eventos']
                    },
                    {
                        label: 'Auditoria de Eventos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/auditoria-evento']
                    }
                ]
            }

        ];

        this.modelUsuarioNaoLogado = [
            {
                label: 'Home',
                items: [
                    // {label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/']},
                    {
                        label: 'Horários de Aula',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/horarios-aluno']
                    },
                ]
            },
        ];


        if(localStorage.getItem("token")){
            this.model = this.modelUsuarioLogado
        }else {
            this.model = this.modelUsuarioNaoLogado
        }

    }
}
