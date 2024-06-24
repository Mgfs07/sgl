import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {LoginComponent} from "./pages/login/login/login.component";
import {AlocacaoComponent} from "./pages/alocacao/alocacao/alocacao.component";
import {AlunoComponent} from "./pages/aluno/aluno/aluno.component";
import {AuditoriaEventoComponent} from "./pages/auditoria-evento/auditoria-evento/auditoria-evento.component";
import {CoordenadorComponent} from "./pages/coordenador/coordenador/coordenador.component";
import {CoordenadoriaComponent} from "./pages/coordenadoria/coordenadoria/coordenadoria.component";
import {DisciplinaComponent} from "./pages/disciplina/disciplina/disciplina.component";
import {EquipamentoComponent} from "./pages/equipamento/equipamento/equipamento.component";
import {EventoComponent} from "./pages/evento/evento/evento.component";
import {HorariosAlunoComponent} from "./pages/horarios-aluno/horarios-aluno/horarios-aluno.component";
import {PeriodoComponent} from "./pages/periodo/periodo/periodo.component";
import {ProfessorComponent} from "./pages/professor/professor/professor.component";
import {LocalComponent} from "./pages/local/local/local.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: 'local',
                component: LocalComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/local/local.module').then(m => m.LocalModule)},
                ]
            },
            {
                path: 'alocacao',
                component: AlocacaoComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/alocacao/alocacao.module').then(m => m.AlocacaoModule)},
                ]
            },
            {
                path: 'alunos',
                component: AlunoComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/aluno/aluno.module').then(m => m.AlunoModule)},
                ]
            },
            {
                path: 'coordenadorias',
                component: CoordenadoriaComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/coordenadoria/coordenadoria.module').then(m => m.CoordenadoriaModule)},
                ]
            },
            {
                path: 'auditoria-evento',
                component: AuditoriaEventoComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/auditoria-evento/auditoria-evento.module').then(m => m.AuditoriaEventoModule)},
                ]
            },
            {
                path: 'coordenadores',
                component: CoordenadorComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/coordenador/coordenador.module').then(m => m.CoordenadorModule)},
                ]
            },
            {
                path: 'coordenadorias',
                component: CoordenadoriaComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/coordenadoria/coordenadoria.module').then(m => m.CoordenadoriaModule)},
                ]
            },
            {
                path: 'disciplinas',
                component: DisciplinaComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/disciplina/disciplina.module').then(m => m.DisciplinaModule)},
                ]
            },
            {
                path: 'equipamentos',
                component: EquipamentoComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/equipamento/equipamento.module').then(m => m.EquipamentoModule)},
                ]
            },
            {
                path: 'eventos',
                component: EventoComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/evento/evento.module').then(m => m.EventoModule)},
                ]
            },
            {
                path: '',
                component: HorariosAlunoComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/horarios-aluno/horarios-aluno.module').then(m => m.HorariosAlunoModule)},
                ]
            },
            {
                path: 'periodos',
                component: PeriodoComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/periodo/periodo.module').then(m => m.PeriodoModule)},
                ]
            },
            {
                path: 'professores',
                component: ProfessorComponent,
                children: [
                    {path: '', loadChildren: () => import('./pages/professor/professor.module').then(m => m.ProfessorModule)},
                ]
            },
            {
                path: 'login',
                component: LoginComponent,
                children: [
                    {path: 'login', loadChildren: () => import('./pages/login/login.module').then(m => m.LoginModule)},
                ],
            },
        ], { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
