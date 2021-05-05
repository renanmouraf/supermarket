# Bibliotecas de Componentes

Vamos falar de algumas das bibliotecas mais comuns de componentes para o Angular e como cada uma pode ser encaixar no seu projeto.

## Ngx-bootstrap

É a melhor forma de integrar o Bootstrap com o Angular.


Contém todos os componentes principais do Bootstrap e é possível usar todas as classes já muito conhecidas que facilitam a construção de interfaces responsivas.


É open source com licença do MIT e totalmente independente do time do Angular.


Para adicionar:

```
$ ng add ngx-bootstrap
```

Exemplo de uso:

```html
<button type="button" class="btn btn-primary"
       tooltip="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
  botão
</button>
```

## Angular Material Design

Baseado no Material Design do Google, ele é ambos um framework de componentes e também uma implementação de referência da especificação. Ele provê um conjunto de componentes reutilizáveis, bem testados e acessíveis.


Componentes disponíveis:

* AutoComplete, Checkbox, Datepicker, Form field, Input, Radio button, Select, Slider and Slide Toggle.
* Navigation Menu, Side Navigation, ToolBar
* Card, Divider, Expansion Panel, Grid List, List, Stepper, Tabs
* Buttons, Chips, Icon, Progress Bar, Progress Spinner.
* Dialog, Snackbar, Tooltip
* Data table


## PrimeNG

Feita pela equipe responsável pelo Primefaces do JSF e tem versões semelhantes para React e Vue.js com as mesmas interfaces e API, o que facilita caso haja uma transição entre frameworks JavaScript.


Todos os componentes estão sob licença MIT (uso pessoal e comercial).


Enquanto o Material tem 32 componentes, o PrimeNG tem mais de 80 componentes prontos e reutilizáveis.

* AutoComplete, Calendar, ColorPicker, Editor, KeyFilter.
* Buttons and SplitButton.
* DataGrid, DataList, DataTable, Tree Table and more.
* Accordion, Card, TabView, Toolbar, ScrollPanel.
* Dialog, Lightbox, Overlay Panel.
* File Upload Component.
* MenuBar, MegaMenu, Breadcrumb, TabMenu.
* Charts Like Bar, Radar, Pie, Line, Doughnut.
* Messages, Growl.
* Galleria, Drag and Drop, Progress Bar, Captcha.

## Menções rápidas

Ng-Bootstrap: muito parecido com o ngx-bootstrap, porém menos maduro e estável.


Onsen UI: componentes híbridos para mobile que funcionam em iOS e Android


MDB Bootstrap: integra Angular, Bootstrap e TypeScript com um CLI disponível sem uso de jQuery. Bastante usado e com muitas opções também.


Outros: Teradata Covalent, Kendo UI, Vaadin, Ignite UI, Clarity, ngSemantic, ng-lightning.


É possível também combinar as bibliotecas de componentes, mas se deve tomar cuidado para não haver incompatibilidades.


Uma das formas mais eficientes de se usar uma biblioteca é através de templates que já combinam os componentes em um layout com algum padrão de cores já padronizado e agradável.


Exemplo: https://www.primefaces.org/store/

