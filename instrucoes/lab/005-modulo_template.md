# Módulo Template

```
$ ng g module template
```

## Componente Navbar

```
$ ng g component template/navbar
```

`navbar.component.scss`
```css
.navbar {
    background-color: black
 }
 
.boasvindas {
    color: white
}
```

`navbar.component.ts`
```typescript
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Autenticacao } from 'src/app/shared/modelos/autenticacao';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent {

 @Output() logout = new EventEmitter();

 @Input() user: Autenticacao;

 constructor() {}

 handleLogout(): void {
   this.logout.emit();
 }

}
```

`navbar.component.html`
```html
<nav class="navbar container">
    <div class="p-grid p-justify-center">
        
        <div class="p-col-4 p-lg-4 p-md-4 p-sm-4 p-mt-3 p-pl-3">
            <app-sidebar *ngIf="user" ></app-sidebar>    
        </div>

        <div class="p-col-4 p-lg-4 p-md-4 p-sm-4 p-mt-3 p-text-center">
            <p *appTemAcesso="['ADMIN']" class="boasvindas">ADMIN!</p>
            <p *appTemAcesso="['SUPERMERCADO']" class="boasvindas">SUPERMERCADO!</p>
        </div>

        <div class="p-col-4 p-lg-4 p-md-4 p-sm-4 p-mt-3 p-pr-3 p-text-right">
            <p-button *ngIf="!user" type="button" label="Login" styleClass="ui-button-primary" routerLink="/login"></p-button>
            <p-button *ngIf="user" type="button" styleClass="ui-button-danger" label="Logout" (click)="handleLogout()"></p-button>
        </div>
        
    </div>

</nav>
```

## Componente Sidebar

```
$ ng g component template/sidebar
```

O **sidebar.component.scss** fica vazio.

`sidebar.component.ts`
```typescript
import { Component, OnInit, ElementRef } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Autenticacao } from '../../shared/modelos/autenticacao';
import { AutenticacaoService } from '../../shared/services/autenticacao.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})

export class SidebarComponent implements OnInit {

  itens: MenuItem[];
  user: Autenticacao;

  constructor(private autenticacaoService: AutenticacaoService,
            private element: ElementRef) {}

  ngOnInit(): void {
      this.autenticacaoService.currentUser.subscribe(user => {

              this.user = user;

              if (this.user && this.autenticacaoService.hasRole(['ADMIN'])) {
                  this.itens = [{
                      label: 'Administrador',
                      items: [
                          {label: 'Supermercados', icon: '',  routerLink: '/admin/supermercados'}
                      ]
                  }];
                } else if (this.user && this.autenticacaoService.hasRole(['SUPERMERCADO'])) {
                  this.itens = [{
                      label: 'Supermercado',
                      items: [
                          {label: 'Pedidos', icon: '',  routerLink: '/supermercados/' +  this.user.targetId + '/pedidos/pendentes'},
                      ]
                  }];
              }
          });
  }

}
```

`sidebar.component.html`
```typescript
<button type="button" pButton icon="pi pi-bars" label="" (click)="menu.toggle($event)"></button>

<p-menu #menu [popup]="true" [model]="itens"></p-menu>
```

Por fim, o arquivo template.module.ts:

`template.module.ts`
```typescript
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { SharedModule } from '../shared/shared.module';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';


@NgModule({
 declarations: [
   NavbarComponent,
   SidebarComponent,
 ],

imports: [
   CommonModule,
   MenuModule,
   ButtonModule,
   SharedModule
 ],

 exports: [
   NavbarComponent,
   SidebarComponent,
 ]
})

export class TemplateModule { }
```

Ao final, o **app.module.ts** deve incluir o TemplateModule recém criado:

`app.module.ts`
```typescript
import { registerLocaleData } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import localePt from '@angular/common/locales/pt';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { ErrosModule } from './erros/erros.module';
import { TemplateModule } from './template/template.module';

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
   TemplateModule
 ],

 providers: [
   { provide: LOCALE_ID, useValue: 'pt' },
 ],
 bootstrap: [AppComponent]
})

export class AppModule { }
```