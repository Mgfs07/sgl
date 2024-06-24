import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppLayoutComponent } from "./layout/app.layout.component";
import {LoginComponent} from "./pages/login/login/login.component";
import {AlocacaoComponent} from "./pages/alocacao/alocacao/alocacao.component";
import {AlunoComponent} from "./pages/aluno/aluno/aluno.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: 'local',
                component: AppLayoutComponent,
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
                path: '',
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
