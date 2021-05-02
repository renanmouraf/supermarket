import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Message, MessageService } from 'primeng/api';
import { Autenticacao } from './shared/modelos/autenticacao';
import { AutenticacaoService } from './shared/services/autenticacao.service';
import { NotificacaoService } from './shared/services/notificacao.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [MessageService]
})
export class AppComponent implements OnInit {
  title = 'supermarkt-ui';
  user: Autenticacao;
  showMenu = false;

  notification: Message;
  showNotification: boolean;

  constructor(private router: Router,
              private messageService: MessageService,
              private autenticacaoService: AutenticacaoService,
              private notificacaoService: NotificacaoService,
              ) {}

  ngOnInit(): void {
    this.autenticacaoService.currentUser.subscribe(user => this.user = user);
    this.notificacaoService
            .notificacoes
            .subscribe(message => {
              this.messageService.add(message);
              this.notification = message;
              this.showNotification = true;
            });
  }

  logout(): void {
    this.autenticacaoService.logout();
    this.router.navigate(['']);
  }

}
