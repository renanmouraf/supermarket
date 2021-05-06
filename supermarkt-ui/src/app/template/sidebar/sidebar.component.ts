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