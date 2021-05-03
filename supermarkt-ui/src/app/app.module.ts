import { registerLocaleData } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LOCALE_ID, NgModule } from '@angular/core';
import localePt from '@angular/common/locales/pt';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { ErrosModule } from './erros/erros.module';
import { TemplateModule } from './template/template.module';
import { ToastModule } from 'primeng/toast';

registerLocaleData(localePt, 'pt');

@NgModule({
 declarations: [
   AppComponent
 ],
 imports: [
   BrowserModule,
   HttpClientModule,
   AppRoutingModule,
   SharedModule,
   ErrosModule,
   TemplateModule,
   ToastModule,
   BrowserAnimationsModule
 ],
 providers: [
   { provide: LOCALE_ID, useValue: 'pt' },
 ],
 bootstrap: [AppComponent]
})
export class AppModule { }