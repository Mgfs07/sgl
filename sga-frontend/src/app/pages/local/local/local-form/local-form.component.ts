import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {LocalService} from "../../../../shared/services/local.service";
import {LocalModel} from "../../../../shared/models/local.model";
import {EquipamentoService} from "../../../../shared/services/equipamento.service";
import {LocalEquipamentoModel} from "../../../../shared/models/local-equipamento.model";
import {LocalEquipamentoService} from "../../../../shared/services/local-equipamento.service";
import {LocalEquipamentoListModel} from "../../../../shared/models/local-equipamento-list.model";

@Component({
    selector: 'app-local-form',
    templateUrl: './local-form.component.html',
    styleUrls: ['./local-form.component.scss']
})
export class LocalFormComponent implements OnInit {

    form: FormGroup;
    formEquipamento: FormGroup;
    local: LocalModel;
    isEdit: boolean;
    isVisualizar: boolean;
    items: any;
    equipamentoOptions: any[] | undefined;
    localSalvo: boolean = false;
    equipamentosList: any[];
    colsEquipamento: any[] | undefined;

    constructor(
        private fb: FormBuilder,
        private localService: LocalService,
        private messageService: MessageService,
        public dialogService: DialogService,
        private dialogConfig: DynamicDialogConfig,
        private equipamentoService: EquipamentoService,
        private localEquipamentoService: LocalEquipamentoService,
        public ref: DynamicDialogRef) {
        this.definirFormulario();
        this.definirFormularioEquipamento();
        this.construirColunasEquipamentos();
    }

    ngOnInit() {
        this.verificarAcao();
        this.buscarEquipamentos();

    }

    fecharDialog(localSalvo?: LocalModel) {
        localSalvo = this.form.getRawValue();
        this.ref.close(localSalvo);
    }

    salvarLocal() {
        const local = this.form.getRawValue();
        this.localService.insert(local).subscribe(value => {
            this.localSalvo = true;
            this.form.get('id').setValue(value.id);
            this.messageService.add({
                severity: 'success',
                summary: 'Success',
                detail: 'O local ' + local.nome + ' foi salvo com sucesso!'
            })
        })
    }

    private verificarAcao() {
        if (this.dialogConfig.data.acao == 'visualizar') {
            this.form.disable();
            this.formEquipamento.disable();
            this.localEquipamentoService.buscarEquipamentosListagemByIdLocal(this.dialogConfig.data.local.id)
                .subscribe(value => {
                this.equipamentosList = value;
            })
            this.isVisualizar = true;
        }
        if (this.dialogConfig.data.acao == 'editar') {
            this.form.enable();
            this.localEquipamentoService.buscarEquipamentosListagemByIdLocal(this.dialogConfig.data.local.id)
                .subscribe(value => {
                    this.equipamentosList = value;
                })
            this.isEdit = true;
        }
        this.renderizarDadosLocal();
    }

    private definirFormulario() {
        this.form = this.fb.group({
            id: [null],
            nome: [null, [Validators.required]],
            capacidade: [null, [Validators.required]],
        });
    }

    private definirFormularioEquipamento() {
        this.formEquipamento = this.fb.group({
            idLocal: [null, [Validators.required]],
            idEquipamento: [null, [Validators.required]],
            quantidade: [null, [Validators.required]],
        });
    }


    private renderizarDadosLocal() {
        const localEncontrado: LocalModel = this.dialogConfig.data.local;
        if (!localEncontrado) {
            return;
        }
        this.form.patchValue(localEncontrado);
    }


    excluirEquipamento(rowData: LocalEquipamentoListModel) {
        this.localEquipamentoService.excluirEquipamentoListagem(rowData.idLocal, rowData.idEquipamento).subscribe(value => {
            this.localEquipamentoService.buscarEquipamentosListagemByIdLocal(rowData.idLocal).subscribe(value1 => {
                this.equipamentosList = value1;
            });
        })
    }



    private construirColunasEquipamentos() {
        this.colsEquipamento = [
            {field: 'nomeEquipamento', header: 'Nome', text: true},
            {field: 'quantidade', header: 'Quantidade', text: true},
            {field: 'acoes', header: 'Ações'}
        ];
    }

    private buscarEquipamentos() {
        this.equipamentoService.buscarEquipamentosDropdown().subscribe(value => {
            this.equipamentoOptions = value
        })
    }

    adicionarEquipamento() {
        const localEquipamento: LocalEquipamentoModel = this.formEquipamento.getRawValue();
        localEquipamento.idLocal = this.form.get('id').value;
        this.localEquipamentoService.insert(localEquipamento).subscribe(value => {
            this.localEquipamentoService.buscarEquipamentosListagemByIdLocal(localEquipamento.idLocal).subscribe(value1 => {
                this.equipamentosList = value1;
            })
        })
    }
}
