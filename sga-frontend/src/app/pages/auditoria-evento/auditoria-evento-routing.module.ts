import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuditoriaEventoComponent} from "./auditoria-evento/auditoria-evento.component";

const routes: Routes = [
    {path: 'auditoria-evento', component: AuditoriaEventoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuditoriaEventoRoutingModule { }
