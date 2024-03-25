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
            // {
            //     label: 'CLIENTES',
            //     items: [
            //         { label: 'Clientes', icon: 'pi pi-fw pi-id-card', routerLink: ['/cliente'] },
            //         // { label: 'Input', icon: 'pi pi-fw pi-check-square', routerLink: ['/uikit/input'] },
            //         // { label: 'Float Label', icon: 'pi pi-fw pi-bookmark', routerLink: ['/uikit/floatlabel'] },
            //         // { label: 'Invalid State', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['/uikit/invalidstate'] },
            //         // { label: 'Button', icon: 'pi pi-fw pi-box', routerLink: ['/uikit/button'] },
            //         // { label: 'Table', icon: 'pi pi-fw pi-table', routerLink: ['/uikit/table'] },
            //         // { label: 'List', icon: 'pi pi-fw pi-list', routerLink: ['/uikit/list'] },
            //         // { label: 'Tree', icon: 'pi pi-fw pi-share-alt', routerLink: ['/uikit/tree'] },
            //         // { label: 'Panel', icon: 'pi pi-fw pi-tablet', routerLink: ['/uikit/panel'] },
            //         // { label: 'Overlay', icon: 'pi pi-fw pi-clone', routerLink: ['/uikit/overlay'] },
            //         // { label: 'Media', icon: 'pi pi-fw pi-image', routerLink: ['/uikit/media'] },
            //         // { label: 'Menu', icon: 'pi pi-fw pi-bars', routerLink: ['/uikit/menu'], routerLinkActiveOptions: { paths: 'subset', queryParams: 'ignored', matrixParams: 'ignored', fragment: 'ignored' } },
            //         // { label: 'Message', icon: 'pi pi-fw pi-comment', routerLink: ['/uikit/message'] },
            //         // { label: 'File', icon: 'pi pi-fw pi-file', routerLink: ['/uikit/file'] },
            //         // { label: 'Chart', icon: 'pi pi-fw pi-chart-bar', routerLink: ['/uikit/charts'] },
            //         // { label: 'Misc', icon: 'pi pi-fw pi-circle', routerLink: ['/uikit/misc'] }
            //     ]
            // },
            // {
            //     label: 'Acervo',
            //     icon: 'pi pi-fw pi-briefcase',
            //     items: [
            //         {
            //             label: 'Registros',
            //             icon: 'pi pi-fw pi-server',
            //             items: [
            //                 {
            //                     label: 'Títulos',
            //                     icon: 'pi pi-fw pi-video',
            //                     routerLink: ['/acervo/titulo']
            //                 },
            //                 {
            //                     label: 'Atores',
            //                     icon: 'pi pi-fw pi-user',
            //                     routerLink: ['/acervo/ator']
            //                 },
            //                 {
            //                     label: 'Diretores',
            //                     icon: 'pi pi-fw pi-user',
            //                     routerLink: ['/acervo/diretor']
            //                 },
            //                 {
            //                     label: 'Classes',
            //                     icon: 'pi pi-fw pi-list',
            //                     routerLink: ['/acervo/classe']
            //                 },
            //                 {
            //                     label: 'Itens',
            //                     icon: 'pi pi-fw pi-list',
            //                     routerLink: ['/acervo/item']
            //                 }
            //             ]
            //         }
            //     ]
            // },
            // {
            //     label: 'Atendimento ao Cliente',
            //     icon: 'pi pi-fw pi-briefcase',
            //     items: [
            //         {
            //             label: 'Cadastrar Clientes',
            //             icon: 'pi pi-fw pi-video',
            //             routerLink: ['/cliente']
            //         },
            //         {
            //             label: 'Locação / Devolução',
            //             icon: 'pi pi-fw pi-video',
            //             routerLink: ['/locacao']
            //         }
            //     ]
            // },
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
                        label: 'Alunos',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/alunos']
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
                        label: 'Professores',
                        icon: 'pi pi-fw pi-caret-right',
                        routerLink: ['/local/professores']
                    }
                ]
            }

        ];
    }
}
