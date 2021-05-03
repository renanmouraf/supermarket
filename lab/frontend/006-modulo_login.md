# Módulo Login

```
$ ng g module login
```

## Componente Login

```
$ ng g component login/login
```

`login.component.scss`
```css
h2 {
   margin-top: 10;
   margin-bottom: 10px;
}

input[type=text], input[type=password] {
   width: 20%;
   margin: 8px 0;
   display: block;
   border: 1px solid #ccc;
   border-radius: 4px;
   box-sizing: border-box;
}
```

`login.component.ts`
```typescript
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../../shared/modelos/usuario';
import { AutenticacaoService } from '../../shared/services/autenticacao.service';

@Component({
 selector: 'app-login',
 templateUrl: './login.component.html',
 styleUrls: ['./login.component.scss']
})
export class LoginComponent {

 loginInfo: Usuario = new Usuario('', '');

 constructor(private router: Router,
             private autenticacaoService: AutenticacaoService) { }


 efetuaLogin(): void {
   this.autenticacaoService.login(this.loginInfo)
     .subscribe(() => this.router.navigate(['']));
 }

}
```

`login.component.html`
```html
<div class="container">
   <h2>Login</h2>
   <form #loginForm="ngForm" (ngSubmit)="efetuaLogin()">
       <div>   
         <label for="username">Usuário:</label>
         <input pInputText type="text" size="30" [(ngModel)]="loginInfo.username" required id="username" name="username">

       </div>

       <div>   
         <label for="password">Senha:</label>
         <input pInputText [(ngModel)]="loginInfo.password" size="30" type="password" required id="password" name="password">
       </div>

       <button pButton type="submit" class="btn btn-primary mr-1" [disabled]="!loginForm.form.valid" label="Entrar"></button>
   </form>

</div>
```

## Login Routing e Module

```
$ ng g module login/login-routing
```

Mova o arquivo **login-routing.module.ts** para fora da pasta login-routing para ficar no mesmo nível que o **login.module.ts** e em seguida apague a pasta login-routing.

`login-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';


const routes: Routes = [
 { path: '', component: LoginComponent },
];

@NgModule({
 imports: [RouterModule.forChild(routes)],
 exports: [RouterModule]
})

export class LoginRoutingModule { }
```

Em **login.module.ts**, fazer:

```typescript
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { LoginComponent } from './login/login.component';
import { LoginRoutingModule } from './login-routing.module';

@NgModule({
declarations: [LoginComponent],
imports: [
  CommonModule,
  LoginRoutingModule,
  FormsModule,
  InputTextModule,
  ButtonModule
]
})
export class LoginModule { }
```

## Ajustando o app routing e component

Alterar o **app-routing.module.ts** para constar o login e o erro.

`app-routing.module.ts`
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrosComponent } from './erros/erros/erros.component';

const routes: Routes = [
 { path: 'login', loadChildren: () => import(`./login/login.module`).then(m => m.LoginModule) },
 { path: 'error', component: ErrosComponent },
 { path: '**', component: ErrosComponent, data: { error: 404 } },
];

@NgModule({
 imports: [RouterModule.forRoot(routes)],
 exports: [RouterModule]
})
export class AppRoutingModule { }
```

`app.component.scss`
```css
#container {
   width: 100px;
   height: 100px;
   position: relative;
}

#abaixo,
#sobre {
   position: absolute;
   top: 0;
   left: 0;
}

#sobre {
   z-index: 10;
}
```

`app.component.ts`
```typescript
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

 logout(event: Event): void {
   this.autenticacaoService.logout();
   this.router.navigate(['']);
 }

}
```

`app.component.html`
```html
<header>
   <p-toast [style]="{marginTop: '50px'}"></p-toast>
</header>

 <div class="ui-g ui-fluid">
   <app-navbar [user]="user" (logout)="logout($event)" (showMenu)="showMenu = !showMenu" class="ui-g-12" style="padding: 0"></app-navbar>
</div>

<div id="container" style="display: inline-block; width: 100vw;">
   <div id="sobre" >
       <app-sidebar [hidden]="!showMenu"></app-sidebar>
   </div>
   <div id="abaixo" style="width: 100%;">
       <router-outlet></router-outlet>
   </div>
</div>
 
<footer>
</footer>
```

Novamente, vamos ajustar o **app.module.ts** com o que foi modificado até aqui, adicionando o TemplateModule, ToastModule e BrowserAnimationsModule.

`app.module.ts`
```typescript
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
```

Vá para `http://localhost:4200/`  e você verá uma barra de navegação e uma mensagem de Erro 404.

Observe que em **app-routing.module.ts** não temos nenhuma rota mapeada para a raíz, por isso o erro.

Teste o login com o usuário "lider" e senha "123456".

Vai aparecer um menu no canto esquerdo e o botão de login agora é de logout, teste o logout e note que o menu extra some.
