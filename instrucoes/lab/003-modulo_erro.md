# Módulo Erro

```
$ ng g module erros
```

## Modelos

```
$ ng g class erros/modelos/erro
```

`erro.ts`
```typescript
import * as StackTraceParser from 'error-stack-parser';

export class Erro {
   constructor(
       public name: string,
       public appId: string,
       public user: string,
       public time: string,
       public id: string,
       public url: string,
       public status: string,
       public message: string,
       public stack: StackTraceParser.StackFrame[]
   ) {}
}
```

## Serviços

```
$ ng g service erros/servicos/erros
```

`erros.service.ts`
```typescript
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
    const location = this.injector.get(LocationStrategy);
    const url = location instanceof PathLocationStrategy ? location.path() : '';
    const status = error.status || undefined;
    const message = error.message || error.toString();
    const stack = error instanceof HttpErrorResponse ? undefined : StackTraceParser.parse(error);
    const errorWithContext = new Erro(name, appId, user, time, id, url, status, message, stack);

    return errorWithContext;

  }

  enviarParaServidor(error: Erro): Observable<Erro> {
      console.log('Enviado para o servidor: ', error);
      return of(error);
  }

}
```

## Error Handler

```
$ ng g class erros/erros-handler
```

`erros-handler.ts`
```typescript
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler, Injectable, Injector, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { NotificacaoService } from '../shared/services/notificacao.service';
import { ErrosService } from './servicos/erros.service';

@Injectable()
export class ErrosHandler implements ErrorHandler {

   constructor(
       private injector: Injector,
       private ngZone: NgZone
   ) {}

   handleError(error: Error | HttpErrorResponse): void {

       const notificaoServico = this.injector.get(NotificacaoService);
       const errosService = this.injector.get(ErrosService);
       const router = this.injector.get(Router);

       if (error instanceof HttpErrorResponse) {
           // Erro servidor
           if (!navigator.onLine) {
               notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: 'Sem conexão.'});
               return;
           }

           notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: `${error.status} - ${error.message}`});

       } else {
           notificaoServico.notificar({severity: 'error', summary: 'Erro', detail: ` ${error.message}`});
       }

       errosService
       .log(error)
       .subscribe(errorWithContextInfo => {
           this.ngZone.run(() => router.navigate(['/error'], { queryParams: errorWithContextInfo })).then();
       });
   }

}
```

## Componente de erro

```
$ ng g component erros/erros
```

Em *src/app/erros/erros/erros.component.scss*:

`src/app/erros/erros/erros.component.scss`
```css
h1, h3, h4, h5 {
    margin-bottom: 0;
    margin-top: 10px;
  }
 
 .error-container {
    max-width: 1170px;
    margin: 0 auto;
    text-align: center;
    overflow-wrap: break-word;
 }
 
 .pre-container {
    margin-top: 3%;
    margin-left: 30%;
    margin-right: 23%;
    text-align: left;
    background-color: lightgrey;
    box-shadow: 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 2px 0 rgba(0,0,0,.14), 0 1px 5px 0 rgba(0,0,0,.12);
 }
 
 pre {
    overflow: scroll;
 }
```

Em **erros.component.ts**

`erros.component.ts`
```typescript
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
 selector: 'app-erros',
 templateUrl: './erros.component.html',
 styleUrls: ['./erros.component.scss']
})

export class ErrosComponent implements OnInit {
 routeParams;
 data;

 constructor(
   private activatedRoute: ActivatedRoute,
 ) { }


 ngOnInit(): void {
   this.routeParams = this.activatedRoute.snapshot.queryParams;
   this.data = this.activatedRoute.snapshot.data;
 }

}
```

Em **erros.component.html**

`erros.component.html`
```html
<div class="error-container">

 <div *ngIf="data?.error">
   <h1>ERRO {{ data?.error}}</h1>
   <h5 *ngIf="data?.error === 404">Página não encontrada :(</h5>
   <a [routerLink]="'/'">
     <h5>Ir para página inicial</h5>
   </a>
 </div>


 <div *ngIf="routeParams.message">
   <h1 *ngIf="routeParams?.status"> ERRO {{ routeParams?.status }}</h1><br/>

   <h1 *ngIf="routeParams?.url && routeParams?.url !== '/'">Erro em {{ routeParams?.url | uppercase }}, desculpe {{ routeParams?.user }} :(</h1>

   <h4 *ngIf="routeParams?.id">Envie o erro abaixo ao Administrador com o ID:<br> {{ routeParams?.id}}</h4>

   <a
     [routerLink]="routeParams?.url"
     *ngIf="routeParams?.url && routeParams.status !== '404'">
       <h5>Voltar para {{routeParams?.url}}</h5>
   </a>

   <a routerLink="/"
       *ngIf="!routeParams.url || routeParams.status === '404'">
       <h5>Voltar para página inicial</h5>
   </a>
 </div>


 <div *ngIf="!routeParams && !data">
     <h1>Erro desconhecido, desculpe :(</h1>
     <a [routerLink]="'/login'">
       <h5>Vá para página inicial</h5>
     </a>
 </div>

 <div class="pre-container" *ngIf="routeParams?.message">
   <pre>
     {{ this.routeParams | json }}
   </pre>
 </div>

</div>
```

O **erros.module.ts** deve ficar assim:

`erros.module.ts`
```typescript
import { CommonModule } from '@angular/common';
import { ErrorHandler, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NotificacaoService } from '../shared/services/notificacao.service';
import { ErrosComponent } from './erros/erros.component';
import { ErrosHandler } from './erros-handler';
import { ErrosService } from './servicos/erros.service';

@NgModule({
imports: [
  CommonModule,
  RouterModule,
],
declarations: [
  ErrosComponent
],
providers: [
  NotificacaoService,
  ErrosService,
  {
    provide: ErrorHandler,
    useClass: ErrosHandler,
  },
]
})

export class ErrosModule { }
```
