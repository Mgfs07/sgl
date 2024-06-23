import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppLayoutComponent } from "./layout/app.layout.component";
import {LoginComponent} from "./pages/login/login/login.component";

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
