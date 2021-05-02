import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FadeDirective } from './fade.directive';
import { TemAcessoDirective } from './tem-acesso.directive';

@NgModule({
 declarations: [
   FadeDirective,
   TemAcessoDirective
 ],

 imports: [
   CommonModule
 ],

 exports: [
   FadeDirective,
   TemAcessoDirective
 ]
})
export class DirectivesModule { }