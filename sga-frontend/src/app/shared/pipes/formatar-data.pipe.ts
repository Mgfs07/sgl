import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatarData'
})
export class FormatarDataPipe implements PipeTransform {

  transform(value: string): string {
      if (!value) return '';

      // Assume que o valor é uma string no formato '2020-06-24'
      const parts = value.split('-');
      if (parts.length !== 3) return value;

      const day = parts[2];
      const month = parts[1];
      const year = parts[0];

      return `${day}/${month}/${year}`;
  }


}
