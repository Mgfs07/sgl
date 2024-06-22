import { Component, Input } from "@angular/core";
import { AulaModel } from "./aula.model";

@Component({
    selector: 'app-aula-no-grid',
    template: `
    <ng-container *ngIf="aula">
        <span>{{aula.professor}}</span><br />
        <span>{{aula.nomeAula}}</span><br />
        <span>{{aula.local}}</span>
    </ng-container>
    `,
})
export class AulaNoGridComponent {
    @Input() public aula?: AulaModel;
}