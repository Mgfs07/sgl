import {Component, OnInit} from '@angular/core';
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {Column} from "../../../shared/models/colum.model";
import {LocalListModel} from "../../../shared/models/local-list.model";
import {LocalService} from "../../../shared/services/local.service";
import {LocalFormComponent} from "./local-form/local-form.component";

@Component({
    selector: 'app-local',
    templateUrl: './local.component.html',
    styleUrls: ['./local.component.scss'],
    providers: [DialogService, ConfirmationService, MessageService]
})
export class LocalComponent implements OnInit {

    cols!: Column[];
    locais: LocalListModel[];
    ref: DynamicDialogRef | undefined;
    display: boolean = false;


    constructor(public dialogService: DialogService,
                private messageService: MessageService,
                private service: LocalService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.construirColunasListagem();
        this.buscarLocais()
    }

    private construirColunasListagem() {
        this.cols = [
            {field: 'nome', header: 'Nome', text: true},
            {field: 'capacidade', header: 'Capacidade', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarLocais() {
        this.service.findAll().subscribe((value: LocalListModel[]) => {
            this.locais = value;
        })
    }

    novoLocal() {
        this.ref = this.dialogService.open(LocalFormComponent,
            {
                header: 'Novo Local',
                width: '55%',
                data: {acao: ''}
            });
        this.ref.onClose.subscribe((local) => {
            if (local) {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'O local ' + local.nome + ' foi cadastrado com sucesso!'
                })
                this.buscarLocais();
            }
        });
    }

    handleAcao(rowData: any, acao: string) {
        this.service.findById(rowData.id).subscribe((value) => {
            this.ref = this.dialogService.open(LocalFormComponent,
                {
                    header: 'Formulário Local',
                    width: '40%',
                    data: {local: value, acao: acao}
                });
            this.ref.onClose.subscribe((local) => {
                if (local) {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'O local ' + local.nome + ' foi editado com sucesso!'
                    })
                    this.buscarLocais()
                }
            });
        })
    }

    excluirLocal(id) {
        this.confirmationService.confirm({
            message: 'Tem certeza que deseja excluir o registro?',
            header: 'Confirmação de Exclusão',
            icon: 'pi pi-info-circle',
            acceptLabel: 'Sim',
            rejectLabel: 'Cancelar',
            accept: () => {
                this.service.delete(id).subscribe(() => {
                    this.buscarLocais();
                })
                this.messageService.add({severity: 'info', summary: 'Confirmação', detail: 'Local inativado!'});
            }
        });
    }

}
