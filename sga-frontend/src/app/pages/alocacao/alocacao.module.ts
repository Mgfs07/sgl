import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AlocacaoRoutingModule} from './alocacao-routing.module';
import {AlocacaoComponent} from "./alocacao/alocacao.component";
import {AlocacaoFormComponent} from "./alocacao/alocacao-form/alocacao-form.component";
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [AlocacaoComponent, AlocacaoFormComponent],
    imports: [
        CommonModule,
        AlocacaoRoutingModule,
        SharedModule
    ]
})
export class AlocacaoModule {
}
