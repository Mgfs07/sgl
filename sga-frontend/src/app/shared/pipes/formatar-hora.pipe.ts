import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'formatarHora'
})
export class FormatarHoraPipe implements PipeTransform {

    transform(value: Date): string {
        if (!value) return '';

        const hora = value.getHours().toString().padStart(2, '0');
        const minutos = value.getMinutes().toString().padStart(2, '0');

        return `${hora}:${minutos}`;
    }
}
