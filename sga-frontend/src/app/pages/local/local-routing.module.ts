import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EquipamentoComponent} from "./equipamento/equipamento.component";
import {DisciplinaComponent} from "./disciplina/disciplina.component";
import {CoordenadoriaComponent} from "../coordenadoria/coordenadoria/coordenadoria.component";
import {AlunoComponent} from "../aluno/aluno/aluno.component";
import {PeriodoComponent} from "./periodo/periodo.component";
import {LocalComponent} from "./local/local.component";
import {ProfessorComponent} from "./professor/professor.component";
import {CoordenadorComponent} from "../coordenador/coordenador.component";
import {EventoComponent} from "./evento/evento.component";
import {HorariosAlunoComponent} from "./horarios-aluno/horarios-aluno.component";
import {AlocacaoComponent} from "../alocacao/alocacao/alocacao.component";
import {AuditoriaEventoComponent} from "./auditoria-evento/auditoria-evento.component";
import {LoginComponent} from "../login/login/login.component";

const routes: Routes = [
    {path: '', component: LocalComponent},
    {path: 'equipamentos', component: EquipamentoComponent},
    {path: 'disciplinas', component: DisciplinaComponent},
    {path: 'coordenadorias', component: CoordenadoriaComponent},
    {path: 'coordenadores', component: CoordenadorComponent},
    {path: 'periodos', component: PeriodoComponent},
    {path: 'professores', component: ProfessorComponent},
    {path: 'eventos', component: EventoComponent},
    {path: 'horarios-aluno', component: HorariosAlunoComponent},
    {path: 'horarios-aluno', component: HorariosAlunoComponent},
    {path: 'auditoria-evento', component: AuditoriaEventoComponent}]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalRoutingModule { }
