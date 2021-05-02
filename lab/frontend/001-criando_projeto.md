# Criando um novo projeto
No terminal, inicializar novo projeto usando **Angular CLI 11.2.11**:

```
$ ng new supermarkt-ui
```

Selecionar as seguintes opções quando for perguntado pelo Angular CLI:

* Angular routing? Yes
* SCSS

## Bibliotecas

```
$ npm i @stomp/ng2-stompjs@8.0.0
$ npm i error-stack-parser@2.0.6
$ npm i primeng@11.4.0
$ npm i primeicons@4.1.0
$ npm i primeflex@2.0.0
$ npm i @angular/cdk@11.2.11
```

Observe se o arquivo package.json está com todas as dependências listadas no
bloco "dependencies".

## Configurações Iniciais

Vamos configurar o arquivo ​**angular.json​** para que referencie os estilos do primeng.

Procure o bloco _styles_ dentro de **build** e não dentro de _test_, ele deve ficar como abaixo.

Cuidado para alterar somente o bloco indicado e não mexer no resto do arquivo.

`angular.json​`
```json
"styles"​: [
​"./node_modules/primeflex/primeflex.css"​,
​"./node_modules/primeicons/primeicons.css"​,
​"./node_modules/primeng/resources/themes/nova-light/theme.css"​,
​"./node_modules/primeng/resources/primeng.min.css"​,
​"src/styles.scss"
],
```

Aplicar um CSS geral para nossa aplicação para que certos componentes fiquem com uma
aparência mais agradável e padronizado. 

Faça isso em ​ **styles.scss​**.

`styles.scss`
```css
/* You can add global styles to this file, and also import other style files */
body {
   font-family: Arial, Helvetica, sans-serif;
   color: #404C51;
   margin: 0px;
}
.container {
   max-width: 1200px;
   margin: 0 auto;
}
label {
   font-weight: bold
}
.displayBlock {
   display: block
}
.displayInline {
   display: inline
}
 
body .ui-menu {
   padding: 0;
   background-color: #f4f4f4 !important;
   border: none !important;
}
 
body .ui-menu .ui-submenu-header {
   font-weight: 600 !important;
}
 
body .ui-tabview .ui-tabview-panels {
   border: 0px ;
}
 
body .ui-tabview .ui-tabview-panels .ui-tabview-panel {
   padding: 10px;
}
```

O arquivo **polyfills.ts** deve incluir a definição de uma variável _global_, uma configuração necessária para uso da biblioteca de WebSocket.

`polyfills.ts`
```typescript
/***************************************************************************************************
* Zone JS is required by default for Angular itself.
*/
import 'zone.js/dist/zone';  // Included with Angular CLI.
 
 
/***************************************************************************************************
* APPLICATION IMPORTS
*/
(window as any).global = window;
```

## Environment

Alterar **src/environments/environment.ts** para constar o _baseUrl_ da API do backend:

`environment.ts`
```typescript
export const environment = {
 production: false,
 baseUrl: '//localhost:8080'
};
```

