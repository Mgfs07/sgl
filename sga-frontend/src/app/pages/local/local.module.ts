import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LocalRoutingModule} from './local-routing.module';
import {EquipamentoComponent} from './equipamento/equipamento.component';
import {EquipamentoFormComponent} from './equipamento/equipamento-form/equipamento-form.component';
import {ButtonModule} from "primeng/button";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {ReactiveFormsModule} from "@angular/forms";
import {DisciplinaComponent} from './disciplina/disciplina.component';
import {DisciplinaFormComponent} from './disciplina/disciplina-form/disciplina-form.component';
import {CoordenadoriaComponent} from './coordenadoria/coordenadoria.component';
import {CoordenadoriaFormComponent} from './coordenadoria/coordenadoria-form/coordenadoria-form.component';
import {AlunoComponent} from './aluno/aluno.component';
import {AlunoFormComponent} from './aluno/aluno-form/aluno-form.component';
import {PeriodoComponent} from './periodo/periodo.component';
import {PeriodoFormComponent} from './periodo/periodo-form/periodo-form.component';
import {SharedModule} from "../../shared/shared.module";
import {ProfessorComponent} from "./professor/professor.component";
import {ProfessorFormComponent} from "./professor/professor-form/professor-form.component";
import {LocalComponent} from './local/local.component';
import {LocalFormComponent} from './local/local-form/local-form.component';
import {CoordenadorComponent} from "../coordenador/coordenador.component";
import {CoordenadorFormComponent} from "../coordenador/coordenador-form/coordenador-form.component";
import { EventoComponent } from './evento/evento.component';
import { EventoFormComponent } from './evento/evento-form/evento-form.component';
import { HorariosAlunoComponent } from './horarios-aluno/horarios-aluno.component';
import { AuditoriaEventoComponent } from './auditoria-evento/auditoria-evento.component';
import { AuditoriaEventoModalComponent } from './auditoria-evento/auditoria-evento-modal/auditoria-evento-modal.component';


@NgModule({
    declarations: [
        EquipamentoComponent,
        EquipamentoFormComponent,
        DisciplinaComponent,
        DisciplinaFormComponent,
        CoordenadoriaComponent,
        CoordenadoriaFormComponent,
        AlunoComponent,
        AlunoFormComponent,
        PeriodoComponent,
        PeriodoFormComponent,
        LocalComponent,
        LocalFormComponent,
        PeriodoFormComponent,
        ProfessorComponent,
        ProfessorFormComponent,
        CoordenadorComponent,
        CoordenadorFormComponent,
        EventoComponent,
        EventoFormComponent,
        HorariosAlunoComponent,
        AuditoriaEventoComponent,
        AuditoriaEventoModalComponent
    ],
    imports: [
        CommonModule,
        LocalRoutingModule,
        ButtonModule,
        ConfirmDialogModule,
        SharedModule,
        TableModule,
        ToastModule,
        InputTextModule,
        PaginatorModule,
        ReactiveFormsModule
    ]
})
export class LocalModule {
}
