# Build

## AOT vs JIT

Ao desenvolver aplicações web modernas, há algumas etapas a serem feitas quando a aplicação está pronta para ser utilizada pelo usuário final que vão fazer a aplicação ficar mais rápida, mais eficiente, e mais difícil de aplicar engenharia reversa.


O conceito é comumente conhecido como “criar um build de produção”.


Usar o Angular CLI para criar builds de produção é fácil e direto, e pode ser feito de algumas formas diferentes, incluindo assets pré-compilados para um desempenho mais rápido e usando Angular Universal, um plugin projetado para permitir renderização isomórfica da aplicação.


O Angular oferece compilação Ahead-of-Time (AOT)  para as nossas aplicações. Basicamente, AOT vai converter as aplicações Angular (HTML e TypeScript) para uma forma eficiente de JavaScript. Isso quer dizer que o código é convertido para JavaScript antes do browser renderizar as páginas.


A alternativa é conhecida como compilação Just-in-Time (JIT). Isso significa que durante o JIT, o seu HTML e TypeScript será compilado em run-time, enquanto o browser renderiza a página.

Por que você pode preferir compilar ahead-of-time (AOT) ? Seguem algumas vantagens:

* Renderização mais rápida: porque os assets são compilados ahead-of-time (previamente), o browser pode carregar o código executável e renderizar os assets imediatamente ao invés de aguardar pela compilação JIT ser completada.
* Menos requisições Async (Assíncronas): todos os seus assets externos como templates HTML e CSS podem ser colocados “em linha” (minify) dentro do JavaScript, eliminando a necessidade requisições ajax separadas para cada asset.
* Tamanho do download menor: ao usar AOT, o compilador do Angular não é mais requerido como parte do download
* Detecção de erro o mais rápido possível: O compilador AOT pode ajudar a identificar erros de binding de template durante o processo de build, o que quer dizer que você pode ver esses erros e consertá-los antes dos usuários acharem esses erros.
* Melhor segurança: trecho traduzido literalmente da documentação oficial em angular.io, “AOT compila templates HTML e componentes em arquivos JavaScript bem antes deles serem disponibilizados para o cliente/usuário. Sem templates para ler em sem avaliações arriscadas de HTML ou JavaScript no lado do cliente, há menos oportunidades para ataques de injeção”.

## Opções de Compilação

O cenário padrão para fazer build de aplicações Angular é JIT. Isso é feito se você fizer uso de um desses comandos:

```
$ ng build
```

```
$ ng serve
```

O primeiro comando é apenas de build e simplesmente faz sua aplicação ficar pronta para compilação JIT. O segundo comando é conhecido como o comando build-and-serve-locally. Isso vai construir sua aplicação para compilação JIT  e então rodar a aplicação no seu computador.


Se você quiser usar AOT, você pode simplesmente colocar --aot depois dos comandos acima:

```
$ ng build --aot=true
```

```
$ ng serve --aot=true
```

Há também um número considerável de opções disponíveis no compilador Angular. Essas opções podem ser configuradas no arquivo tsconfig.json na sua aplicação Angular.


Verificar mais opções direto na documentação em: https://angular.io/guide/aot-compiler

## Builds de Desenvolvimento e Produção

O ng serve é usado para criar uma instância local da sua aplicação que você pode acessar; no entanto, há muitas situações em que você pode querer criar um build de desenvolvimento, para servir um servidor desenvolvimento separado para testes e outro para sua aplicação de homologação, ou o build de produção para release final.


O Angular CLI tem suporte para criar builds de nível de desenvolvimento assim como de nível de produção, e tem suporte para criar muitos ambientes diferentes também.


O arquivo environment.ts é o arquivo de ambiente padrão, o qual é usado para builds de desenvolvimento. Desenvolvimento é o ambiente padrão quando o Angular CLI, produção tem que ser declarado explicitamente.


Ao usar o CLI, você especifica ambos o build target e o build environment. O CLI usa o --target e --environment como segue:


Produção:
```
$ ng build --target=production --environment=prod
```

Desenvolvimento:
```
$ ng build --target=development --environment=dev
```

O uso principal de cada um desses builds envolve decisões em torno de querer depurar/ testar sua aplicação vs desempenho melhorado e tamanhos menores de download.

