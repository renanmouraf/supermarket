import { LocationStrategy, PathLocationStrategy } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Injectable, Injector, Type } from '@angular/core';
import { Event, NavigationError, Router } from '@angular/router';
import * as StackTraceParser from 'error-stack-parser';
import { Observable, of } from 'rxjs';
import { Autenticacao } from 'src/app/shared/modelos/autenticacao';
import { AutenticacaoService } from 'src/app/shared/services/autenticacao.service';
import { Erro } from '../modelos/erro';

@Injectable()
export class ErrosService {

  usuario: Autenticacao;

  constructor(
    private injector: Injector,
    private router: Router,
    private autenticacaoService: AutenticacaoService,
  ) {
    this.router
          .events
          .subscribe((event: Event) => {
            if (event instanceof NavigationError) {
                this.log(event.error)
                        .subscribe((errorWithContext) => {
                          this.router.navigate(['/error'], { queryParams: errorWithContext });
                        });
            }
          });
  }

  log(error: Error): Observable<Erro> {
    const errorToSend = this.addContextInfo(error);
    return this.enviarParaServidor(errorToSend);
  }

  addContextInfo(error): Erro {
    this.autenticacaoService.currentUser.subscribe(usuario => this.usuario = usuario);
    const name = error.name || undefined;
    const appId = 'supermarkt-ui';
    let user = '';
    if (this.usuario) {
      user = this.usuario.username;
    }
    const date = new Date(Date.now());
    let time = `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
    time += `-${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
    const id = `${appId}-${user}-${time}`;
    const location = this.injector.get<LocationStrategy>(LocationStrategy as Type<LocationStrategy>);
    const url = location instanceof PathLocationStrategy ? location.path() : '';
    const status = error.status || undefined;
    const message = error.message || error.toString();
    const stack = error instanceof HttpErrorResponse ? undefined : StackTraceParser.parse(error);

    const errorWithContext = new Erro(name, appId, user, time, id, url, status, message, stack);
    return errorWithContext;
  }

  enviarParaServidor(error: Erro): Observable<Erro> {
      //console.log('Enviado para o servidor: ', error);
      return of(error);
  }

}

