import {Component, OnInit} from '@angular/core';
import {Column} from "../../../shared/models/colum.model";
import {AuditoriaEventoModel} from "../../../shared/models/auditoria-evento.model";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {AuditoriaService} from "../../../shared/services/auditoria.service";
import {EventoFormComponent} from "../evento/evento-form/evento-form.component";
import {AuditoriaEventoModalComponent} from "./auditoria-evento-modal/auditoria-evento-modal.component";

@Component({
  selector: 'app-auditoria-evento',
  templateUrl: './auditoria-evento.component.html',
  styleUrls: ['./auditoria-evento.component.scss']
})
export class AuditoriaEventoComponent implements OnInit {

    cols!: Column[];
    auditorias: AuditoriaEventoModel[];
    ref: DynamicDialogRef | undefined;

    constructor(
        private service: AuditoriaService,
        public dialogService: DialogService
    ) {
    }

    ngOnInit() {
        this.buscarTodasAuditoriasEvento();
        this.construirColunasListagem();
    }

    buscarTodasAuditoriasEvento() {
         this.service.buscarTodasAuditoriaEvento().subscribe((res) => {
             this.auditorias = res;
        })
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nomeUsuarioAlteracao', header: 'Usuário que realizou a ação', text: true},
            {field: 'acao', header: 'Ação'},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    handleAcao(rowData: any, acao: string) {
        this.service.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(AuditoriaEventoModalComponent,
                {
                    header: 'Visualizar Auditoria',
                    width: '35%',
                    data: {auditoria: value}
                });
        })
    }

}
