# Configuração do WebSocket e atualização do app.module.ts

Criar pelo próprio VSCode um arquivo **src/app/rx-stomp.config.ts**:

`src/app/rx-stomp.config.ts`
```typescript
import { InjectableRxStompConfig } from '@stomp/ng2-stompjs';
import SockJS from 'sockjs-client';
import { environment } from '../environments/environment';


export const rxStompConfig: InjectableRxStompConfig = {
 webSocketFactory: () => {
   return new SockJS(environment.baseUrl + '/socket');
 }
};
```

Por fim, vamos atualizar **app.module.ts** para refletir o que foi feito até aqui e adicionar a configuração de locale para português:

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
   ErrosModule
 ],
 providers: [
   { provide: LOCALE_ID, useValue: 'pt' },
 ],
 bootstrap: [AppComponent]
})

export class AppModule { }
```

Com isso finalizamos a configuração básica de infra da nossa aplicação front-end.