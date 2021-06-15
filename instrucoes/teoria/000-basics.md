# Fundamentos

## O que é Angular?

Angular é um framework que você pode usar para construir aplicativos usando HTML e JavaScript / TypeScript. O uso do Angular envolverá o acesso às várias bibliotecas que compõem o framework.

Como a documentação do angular.io indica:

"Você escreve aplicativos Angular compondo templates HTML com Angular markup, escrevendo classes de componentes para gerenciar esses templates, adicionando a lógica do app em serviços e empacotando componentes e serviços em módulos. Em seguida, você inicia o aplicativo inicializando o módulo raiz. O Angular assume o controle, apresentando o conteúdo do seu aplicativo em um navegador e respondendo às interações do usuário de acordo com as instruções que você forneceu. "

Se você fosse solicitado a explicar o Angular em poucas palavras, poderia dizer que é um framework que simplifica a criação de aplicativos da web interativos do lado do cliente, usando data binding. 

O Angular consiste em vários aspectos que são introduzidos aqui.

## Principais Componentes Angular

### Módulos

Todos os aplicativos Angular que você criar terão formato modular. 

Isso significa que você trabalhará com NgModules como parte de seu aplicativo.

Ao criar pequenos aplicativos da web, você só pode usar só um módulo. 

Um módulo, em Angular, é uma classe que ajuda você a organizar todas as diferentes “partes” de sua aplicação. ele permite que você organize essas “partes” em blocos.

Módulos Angular também permitem que você estenda os recursos de seu aplicativo por meio de bibliotecas externas. 

As instruções import core e common trazem dois módulos Angular. Seu aplicativo é estendido pelo uso desses dois módulos, o que significa que seu aplicativo ganha a funcionalidade encontrada nesses módulos, sem a necessidade de escrever código para implementar essa mesma funcionalidade.

Você também pode exportar seu módulo para uso em outros aplicativos.

Usando módulos Angular, você pode dividir seu aplicativo em vários componentes, tornando seu aplicativo mais fácil de atualizar e manter.

Cada aplicativo Angular deve ter pelo menos um NgModule. Este é o módulo raiz e é chamado de AppModule, você o encontrará no arquivo app.module.ts. 

Módulos Angular consistem em um conjunto de atributos que você usa para descrever o módulo. 

Eles estão listados aqui:

* declarations - são a lista de componentes, diretivas e pipes que fazem parte do módulo específico que você está criando. Tudo em declarations é visível para o aplicativo que usa este módulo, sem exigir nenhuma exportação explícita.
* imports - usando instruções de importação, você pode tornar outros módulos visíveis dentro deste módulo.
* exports - quaisquer declarations que você deseja que sejam disponibilizadas (visíveis) para outros módulos que podem importar este módulo.
* providers - usado para listar providers para configuração do injetor (Injeção de dependência), quando este módulo é importado por outros módulos. Na maioria das vezes, essas dependências são serviços que você cria e fornece.

### Bibliotecas

O Angular inclui uma coleção de módulos e esses módulos formam bibliotecas dentro do Angular e fornecem funcionalidades para uso em seus aplicativos. 

Bibliotecas Angular são indicadas pelo prefixo @angular.

### Componentes

O Angular usa o conceito de um componente para controlar aspectos de seu aplicativo. 

Por exemplo, um componente é uma classe que contém a lógica necessária para lidar com um aspecto de uma página, chamado de viewss.

Angular lida com a criação e destruição desses componentes durante as interações do usuário com seu aplicativo da web.

### Templates

A view do seu componente é definida em um Template.

Os Templates são simplesmente HTML trabalhando com o Angular na renderização adequada do componente no aplicativo. 

Os Templates usam tags HTML padrão, mas o Angular também tem tags e atributos próprios disponíveis para as implementações funcionais no template. 

### Data Binding

Aplicativos da web interativos e dinâmicos dependem de dados. 

Você pode estar criando um e-commerce ou talvez refletindo estatísticas de voto em seu aplicativo da web. 

Atualizar a interface do usuário quando os dados são alterados é uma tarefa demorada com o potencial de erros nos dados ou na sincronização dos dados e dos elementos da UI.

Angular se destaca no data binding. 

Você simplesmente adiciona binding à sua marcação nos templates que você cria e, em seguida, informa ao Angular como os dados e a UI estão conectados. 

A vinculação de dados pode ser unilateral ou bidirecional.

### Diretivas

As diretivas, em essência, são comandos que você dá ao Angular. 

O Angular aplicará as instruções especificadas pela diretiva, ao renderizar o template, para transformar o DOM em sua página.

Você também pode ter diretivas estruturais e de atributo. 

Eles são encontrados em tags de elemento semelhantes a atributos com a diretiva estrutural sendo responsável por alterar layouts por meio de elementos DOM.

### Injeção de dependência

A documentação sobre Angular define injeção de dependência (DI) como “uma maneira de fornecer uma nova instância de uma classe com as dependências que ela requer. A maioria das dependências são serviços. O Angular usa injeção de dependência para prover componentes com os serviços de que precisam ”.

Essencialmente, é um mecanismo no qual os componentes de seu aplicativo Angular resolvem quaisquer dependências relacionadas a serviços ou componentes requeridos por seu componente.

### Serviços

Os serviços são um tipo de componente no Angular que são projetados especificamente para criar lógica reutilizável que pode ser injetada em vários componentes.

Isso os torna idealmente adequados para a tarefa de compartimentar as interações da API e, portanto, esse é seu caso de uso mais comum.

Ao usar um serviço para manter suas interações de API, você pode facilmente adicionar recursos adicionais aos métodos de API, como armazenamento em cache, e permitir que seus componentes se inscrevam em uma única instância de uma chamada de API, evitando idas desnecessárias ao servidor.

Um cenário de uso comum para um serviço no Angular é buscar dados de uma fonte de dados. 

Pode ser um banco de dados em execução em um servidor em algum lugar ou pode ser qualquer outra forma de dados de que seu aplicativo Angular precise. 

### Routing

Para sites de várias páginas, o roteamento é um conceito bastante simples: cada endereço que você digita em uma URL representa um arquivo diferente no sistema de arquivos ou um controlador diferente em seu MVC.

Para o Angular e outros SPA (single-page applications), o roteamento funciona de maneira um pouco diferente. 

Como seu aplicativo Angular é essencialmente um grande arquivo JavaScript incorporado em uma única página, o Angular precisa empregar um código de roteamento especial que converte a URL digitada na barra de endereço do seu navegador nos componentes renderizados pelo engine do Angular.
