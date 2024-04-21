import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EquipamentoComponent} from "./equipamento/equipamento.component";
import {DisciplinaComponent} from "./disciplina/disciplina.component";
import {CoordenadoriaComponent} from "./coordenadoria/coordenadoria.component";
import {AlunoComponent} from "./aluno/aluno.component";
import {PeriodoComponent} from "./periodo/periodo.component";
import {LocalComponent} from "./local/local.component";
import {ProfessorComponent} from "./professor/professor.component";
import {CoordenadorComponent} from "../coordenador/coordenador.component";
import {EventoComponent} from "./evento/evento.component";

const routes: Routes = [
    {path: '', component: LocalComponent},
    {path: 'equipamentos', component: EquipamentoComponent},
    {path: 'disciplinas', component: DisciplinaComponent},
    {path: 'coordenadorias', component: CoordenadoriaComponent},
    {path: 'coordenadores', component: CoordenadorComponent},
    {path: 'alunos', component: AlunoComponent},
    {path: 'periodos', component: PeriodoComponent},
    {path: 'professores', component: ProfessorComponent},
    {path: 'eventos', component: EventoComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalRoutingModule { }
