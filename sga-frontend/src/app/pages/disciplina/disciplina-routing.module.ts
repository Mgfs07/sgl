import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DisciplinaComponent} from "./disciplina/disciplina.component";

const routes: Routes = [
    {path: 'disciplinas', component: DisciplinaComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DisciplinaRoutingModule { }
