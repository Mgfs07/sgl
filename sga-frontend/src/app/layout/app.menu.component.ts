import {OnInit} from '@angular/core';
import {Component} from '@angular/core';
import {LayoutService} from './service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) {
    }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items: [
                    {label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/']}
                ]
            },
            {
                label: 'Salas/Labs',
                icon: 'pi pi-fw pi-briefcase',
                items: [
                    {
                        label: 'Equipamentos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/equipamentos']
                    },
                    {
                        label: 'Disciplinas',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/disciplinas']
                    },
                    {
                        label: 'Coordenadorias',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/coordenadorias']
                    },
                    {
                        label: 'Coordenadores',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/coordenadores']
                    },
                    {
                        label: 'Alunos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/alunos']
                    },
                    {
                        label: 'Períodos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/periodos']
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
                        routerLink: ['/local/professores']
                    },
                    {
                        label: 'Eventos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/eventos']
                    },
                    {
                        label: 'Horários Aluno',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/horarios-aluno']
                    },
                    {
                        label: 'Auditoria de Eventos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/auditoria-evento']
                    }
                ]
            }

        ];
    }
}
