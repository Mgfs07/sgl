import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EquipamentoComponent} from "./equipamento/equipamento.component";
import {DisciplinaComponent} from "./disciplina/disciplina.component";
import {CoordenadoriaComponent} from "./coordenadoria/coordenadoria.component";
import {AlunoComponent} from "./aluno/aluno.component";
import {PeriodoComponent} from "./periodo/periodo.component";

const routes: Routes = [
    {path: 'equipamentos', component: EquipamentoComponent},
    {path: 'disciplinas', component: DisciplinaComponent},
    {path: 'coordenadorias', component: CoordenadoriaComponent},
    {path: 'alunos', component: AlunoComponent},
    {path: 'periodos', component: PeriodoComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalRoutingModule { }
