import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfessorComponent} from "./professor/professor.component";

const routes: Routes = [
    {path: 'professores', component: ProfessorComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfessorRoutingModule { }
