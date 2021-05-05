# Rotas e Navegação

Por definição, Guards são interfaces que podem ser implementadas de vários jeitos, mas elas têm que retornar um boolean, `Promise<boolean>` ou `Observable<boolean>`.


Existem 4 tipos de Guards:

*CanActivate: decide se uma rota pode ser ativada
*CanActivateChild: decide se uma rota filha em uma rota pode ser ativada
*CanDeactivate: decide se uma rota pode ser desativada
*CanLoad: decide se uma rota filha pode ser carregada

## CanActivate: autenticação

Usado para quando se quer liberar alguma funcionalidade baseado em quem o usuário é. Você pode permitir acesso apenas a usuários autenticado ou usuários com um papel específico.


Para gerar:

```
$ ng g guard autenticacao
```

A interface geral do CanActivate:

```typescript
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';


@Injectable({
 providedIn: 'root',
})
export class AutenticacaoGuard implements CanActivate {
 canActivate(
   next: ActivatedRouteSnapshot,
   state: RouterStateSnapshot): boolean {
   console.log('canActivate chamado');
   return true;
 }
}
```

Para aplicar o guard em uma rota, basta usar o campo correspondente canActivate:

```typescript
import { AutenticacaoGuard }  from '../autentiacao.guard';

const adminRoutes: Routes = [
 {
   path: 'admin',
   component: AdminComponent,
   canActivate: [AutenticacaoGuard],
   ]
 }
];

@NgModule({
 imports: [
   RouterModule.forChild(adminRoutes)
 ],
 exports: [
   RouterModule
 ]
})
export class AdminRoutingModule {}
```

Para fazermos uma regra de verdade, mais restrita, caso o usuário esteja logado.

```typescript
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AutenticacaoService } from './autenticacao.service';


@Injectable({

 providedIn: 'root',

})

export class AutenticacaoGuard implements CanActivate {

 constructor(private autService: AutenticacaoService, private router: Router) {}

 canActivate(
   next: ActivatedRouteSnapshot,
   state: RouterStateSnapshot): boolean {
   let url: string = state.url;
   return this.checkLogin(url);
 }

 checkLogin(url: string): boolean {
   if (this.autService.isLoggedIn) { return true; }

   this.autService.redirectUrl = url;
   
   this.router.navigate(['/login']);
   return false;
 }
}
```

Dessa forma, somente os usuários logados conseguem acesso à rota de admin, caso negativo, são redirecionados para a tela de login.

## CanActivateChild: segurança de rotas filhas

Também é possível proteger rotas filhas com o guard CanActivateChild. Ele é muito similar ao guard CanActivate. A principal diferença é que ele é ativado antes de qualquer rota filha ser ativada.


Se a proteção do módulo é feita contra acesso não autorizada, você deve também proteger rotas dentro do módulo também.


Vamos usar a mesma regra de CanActivate para CanActivateChild com os mesmos argumentos.

```typescript
import { Injectable }  from '@angular/core';
import {
 CanActivate, Router,
 ActivatedRouteSnapshot,
 RouterStateSnapshot,
 CanActivateChild
}  from '@angular/router';
import { AutenticacaoService }  from './autenticacao.service';


@Injectable({
 providedIn: 'root',
})
export class AutenticacaoGuard implements CanActivate, CanActivateChild {

 constructor(private autService: AutenticacaoService, private router: Router) {}

 canActivate(
   route: ActivatedRouteSnapshot,
   state: RouterStateSnapshot): boolean {
   let url: string = state.url;

   return this.checkLogin(url);
 }

 canActivateChild(
   route: ActivatedRouteSnapshot,
   state: RouterStateSnapshot): boolean {
   return this.canActivate(route, state);
 }

/* . . . */
}
```

Agora vamos alterar o routing.module.ts para constar rotas filhas e protegê-las com nosso guard.

```typescript
const adminRoutes: Routes = [
   {
     path: 'admin',
     component: AdminComponent,
     canActivate: [AutenticacaoGuard],
     children: [
       {
         path: '',
         canActivateChild: [AutenticacaoGuard],
         children: [
           { path: 'supermercados', component: SupermercadosComponent },
         ]
       }
     ]
   }
 ];
  @NgModule({
   imports: [
     RouterModule.forChild(adminRoutes)
   ],
   exports: [
     RouterModule
   ]
 })
 export class AdminRoutingModule {}
```

## CanDeactivate: lidando com mudanças não salvas

Pensando em um fluxo geral de uma aplicação, quando o usuário lança uma ação qualquer, a mudança é feita imediatamente sem hesitação.

Mas há aquele cenário em que uma mudança deve ser acumulada até que outras estejam ok para só então termos alterações de registros. Pode querer validar dados que se cruzam em campos diferentes. Fazer uma validação no servidor. Por fim, pode querer segurar todas as alterações em um estado de aguardando, até o usuário confirmar tudo como um grupo ou cancelar e reverter todas as alterações, uma operação atômica.

O que fazemos sobre alterações não salvas e não aprovadas, quando o usuário sai da tela? Não podemos simplesmente deixar ele sair com o risco de perder as alterações feitas pelo usuário.

A alternativa é pausar e deixar o usuário decidir o que fazer. Se o usuário cancela a operação, ficamos onde estamos e permitimos mais alterações. Se o usuário aprovar, a aplicação pode salvar.

Se o processamento for demorado, pode ser necessário atrasar a navegação, especialmente em caso de erro para não perder o contexto do erro, não é possível bloquear enquanto se espera pelo servidor, isso não é possível em um browser. Nesse caso o jeito é parar a navegação enquanto aguardamos, assincronamente, pelo servidor retornar com uma resposta.

É aí que entra o guard CanDeactivate.

Imaginemos um componente que tem dois botões, cancelar e salvar. Ao invés de atualizar o registro imediatamente, o Salvar apenas atualiza o objeto e o Cancelar ignora as alterações.

```typescript
cancelar() {
   this.navegaPaginaPrincipal();
}

salvar() {
   this.objeto.nome = this.nomeEditado;
   this.navegaPaginaPrincipal();
}
```

Mas e se o usuário apertar o botão de voltar, o que fazer? Salvar ou descartar as alterações?


Uma opção recomendada é perguntar para o usuário o que fazer em uma caixa de diálogo e aguardar pela resposta conforme abaixo:

```typescript
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';


@Injectable({
 providedIn: 'root',
})
export class DialogService {
 confirm(message?: string): Observable<boolean> {
   const confirmation = window.confirm(message || 'OK?');

   return of(confirmation);
 };
}
```

Agora vamos gerar o nosso guard CanDeactivate

```
$ ng g guard can-deactivate
```

```typescript
import { Injectable }    from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { Observable }    from 'rxjs';


export interface CanComponentDeactivate {
canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

@Injectable({
 providedIn: 'root',
})
export class CanDeactivateGuard implements CanDeactivate<CanComponentDeactivate> {
 canDeactivate(component: CanComponentDeactivate) {
   return component.canDeactivate ? component.canDeactivate() : true;
 }
}
```

No nosso GenericoComponent teríamos algo assim:

```typescript
canDeactivate(): Observable<boolean> | boolean {
   if (!this.objeto || this.objeto.nome === this.nomeEditado) {
     return true;
   }
   return this.dialogService.confirm('Descartar mudanças?');
 }
```

Por fim, nosso roteador ficaria dessa forma:

```typescript
const genericoCenterRoutes: Routes = [
 {
   path: 'rota1',
   component: GenUmComponent,
   children: [
     {
       path: '',
       component: GenDoisComponent,
       children: [
         {
           path: ':id',
           component: GenericoComponent,
           canDeactivate: [CanDeactivateGuard]
         },
       ]
     }
   ]
 }
];

@NgModule({
 imports: [
   RouterModule.forChild(genericoCenterRoutes)
 ],
 exports: [
   RouterModule
 ]
})
export class GenericoRoutingModule { }
```

Agora temos uma proteção contra mudanças não salvas.

## CanLoad Guard: impedindo o carregamento de módulos não autorizados

Mesmo protegendo rotas com CanActivate para acesso não autorizado, o roteador ainda assim carrega o módulo mesmo que o usuário não possa acessar nenhum dos seus componentes, idealmente, carregaríamos um AdminModule apenas se o usuário estiver logado e com a devida permissão.


Para isso podemos adicionar um guard CanLoad que carrega o AdminModule apenas se o usuário estiver logado e tentar acessar a funcionalidade de admin.


O AutenticacaoGuard que mostramos anteriormente já possui a lógica essencial dentro do checkLogin() e pode ser reutilizado para o Canload que ficaria assim dentro de autenticacao.guard.ts:

```typescript
canLoad(route: Route): boolean {
    let url = `/${route.path}`;
    return this.checkLogin(url);
}
```

E no nosso **routing.module.ts**, teremos algo assim:

```typescript
{
   path: 'admin',
   loadChildren: () => import('./admin/admin.module').then(mod => mod.AdminModule),
   canLoad: [AutenticacaoGuard]
},
```

Desta forma, o carregamento do AdminModule apenas ocorre quando o checkLogin verificar a URL passada e retornar true para o usuário.

