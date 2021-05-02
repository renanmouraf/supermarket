import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'textoFavorito'
})
export class TextoFavoritoPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (args[0]) {
      return value + ' \u2665';
    } else {
      return value;
    }
  }

}
