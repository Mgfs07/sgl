import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HorariosAlunoComponent} from "./horarios-aluno/horarios-aluno.component";

const routes: Routes = [
    {path: 'horarios-aluno', component: HorariosAlunoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HorariosAlunoRoutingModule { }
