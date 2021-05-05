# WebSockets

Websocket é a tecnologia para o desenvolvimento de soluções em tempo real que conecta-se a um canal full-duplex sobre um único socket, permitindo a comunicação bidirecional. De forma simplificada, WebSocket é a implementação de sockets para Web, projetado para ser executado através de browsers e servidores Web que suportam HTML5.

## Quando HTTP é melhor

-Recuperar um recurso: um cliente quer o estado atual do recurso e não precisa de atualizações constantes. Ex.: resultado de um jogo passado, caso fosse um jogo ao vivo, WebSocket seria melhor


-Recurso altamente cacheavel: caching é um grande benefício quando o recurso muda raramente ou se tem muitos clientes para aquele recurso. Ex.: mesmo do item anterior, jogo de futebol.


-Idempotência e segurança: uma requisição é idempotente quando ela sempre retorna o mesmo resultado, independente da quantidade de chamadas. Ela é segura quando não modifica o recurso sendo acionado. Isso é a chave para cache e o HTTP tem padrões largamente utilizados na indústria, o WebSocket não tem tais padrões.


-Cenários de Erros: HTTP é projetado para descrever erros com a requisição. WebSocket oferece suporte somente para cenários de erros no estabelecimento da conexão.


-Eventos Sincronizados: o padrão request-response é bom para operação que requerem sincronização ou que requerem uma ação serializada, o HTTP response representa uma conclusão para uma requisição específica, permitindo ações subsequentes em cima dessa conclusão. O protocolo WebSocket não oferece garantia que a mensagem foi recebida.

## Quando WebSocket é melhor

-Tempo de reação rápido: cliente precisa reagir rapidamente. Um exemplo é um chat com múltiplos usuários, onde o HTTP request/response adicionaria um overhead para cada mensagem enviada e recebida.


-Atualizações constantes: quando o cliente quer atualizações em tempo real sobre o estado do recurso.


-Mensagem Ad-hoc: mensagens podem ser enviadas de qualquer ponto da conexão a qualquer momento, bom para mensagens "envie e esqueça" e ruim para quando se tem requerimentos transacionais.


-Mensagens com alta frequência com payloads pequenos: WebSocket oferece uma conexão persistente para trocar mensagens. Isso significa que mensagens individuais não geram uma taxa extra para estabelecer o transporte.

## Resumo

HTTP pode estar sendo usado incorretamente se:

* o design da aplicação depende do cliente ficar fazendo polling com frequência, sem o usuário fazer nenhuma ação.
* Seu design requer chamadas frequentes de serviço para enviar pequenas mensagens.
* O cliente precisa agir rapidamente a uma mudança no recurso e não pode prever quando isso vai ocorrer.

WebSocket pode estar sendo usado incorretamente se:

* A conexão é usada apenas para um número pequeno de eventos, ou por um período de tempo muito pequeno, e o cliente não precisa reagir rapidamente.
* Sua funcionalidade requer múltiplos WebSockets abertos ao mesmo tempo de uma vez.
* A funcionalidade abre o WebSocket, envia mensagens, depois fecha - e repete o ciclo.
