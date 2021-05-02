import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { DirectivesModule } from './directives/directives.module';
import { ErroServidorInterceptor } from './interceptors/erro-servidor-interceptor';
import { JwtInterceptor } from './interceptors/jwt-interceptor';
import { PipesModule } from './pipes/pipes.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    PipesModule,
    DirectivesModule
  ],
  exports: [
    PipesModule,
    DirectivesModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor , multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: ErroServidorInterceptor , multi: true},
  ]
})
export class SharedModule { }
