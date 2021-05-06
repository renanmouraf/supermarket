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