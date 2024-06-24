import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EventoRoutingModule} from './evento-routing.module';
import {SharedModule} from "../../shared/shared.module";
import {EventoFormComponent} from "./evento/evento-form/evento-form.component";
import {EventoComponent} from "./evento/evento.component";


@NgModule({
    declarations: [EventoFormComponent, EventoComponent],
    imports: [
        CommonModule,
        EventoRoutingModule,
        SharedModule
    ]
})
export class EventoModule {
}
