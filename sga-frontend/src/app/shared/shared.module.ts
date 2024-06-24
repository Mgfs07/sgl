import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PRIMENG_IMPORTS} from "./primeng-imports";
import { StatusPipe } from './pipes/status.pipe';
import {CurrencyPipe, DatePipe, NgForOf, NgIf} from "@angular/common";
import { GridHorarioComponent } from './components/grid-horario/grid-horario.component';
import {AulaNoGridComponent} from "./components/grid-horario/aula-no-grid.component";
import { ImpressaoRelatorioHorariosComponent } from './components/impressao-relatorio-horarios/impressao-relatorio-horarios.component';
import { FormatarHoraPipe } from './pipes/formatar-hora.pipe';


@NgModule({
    declarations: [
        StatusPipe,
        GridHorarioComponent,
        AulaNoGridComponent,
        ImpressaoRelatorioHorariosComponent,
        FormatarHoraPipe,
    ],
    imports: [
        PRIMENG_IMPORTS,
        ReactiveFormsModule,
        FormsModule,
        DatePipe,
        CurrencyPipe,
        NgForOf,
        NgIf
    ],
    exports: [
        PRIMENG_IMPORTS,
        FormsModule,
        StatusPipe,
        ReactiveFormsModule,
        GridHorarioComponent,
        ImpressaoRelatorioHorariosComponent
    ]
})
export class SharedModule { }
