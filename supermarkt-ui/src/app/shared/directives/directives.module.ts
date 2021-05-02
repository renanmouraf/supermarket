import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ExcetoDirective } from './exceto.directive';
import { FadeDirective } from './fade.directive';
import { TemAcessoDirective } from './tem-acesso.directive';


@NgModule({
  declarations: [
    ExcetoDirective,
    FadeDirective,
    TemAcessoDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ExcetoDirective,
    FadeDirective,
    TemAcessoDirective
  ]
})
export class DirectivesModule { }
