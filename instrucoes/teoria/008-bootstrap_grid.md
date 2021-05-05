# Bootstrap 4 Grid system

O Grid do Bootstrap permite até 12 colunas.


Se você não quiser usar as 12 com um componente só, é possível agrupar outros até o limite de 12 colunas.


O sistema de grid é responsivo e, se for usado corretamente, as colunas se rearranjam sozinhas dependendo do tamanho da tela: com muito espaço, as coisas podem ficar lado a lado, com pouco espaço podemos precisar empilhar elas.

## Classes

O Bootstrap 4 tem cinco classes:


* .col- (dispositivos muito pequenos, menos que 576px)
* .col-sm- (dispositivos pequenos, maior que 576px)
* .col-md- (dispositivos médios, maior que 768 px)
* .col-lg- (dispositivos grandes - maior que 992px)
* .col-xl- (dispositivos extra grandes - maior que 1200px)

As classes podem ser combinadas para layouts mais flexíveis.

## Regras de Utilização

* As linhas devem estar dentro de um .container (fixo) ou .container-fluid (largura total)
* Use linhas para criar grupos horizontais de colunas
* Conteúdo deve ser colocado dentro das colunas
* Colunas no Grid são criadas ao especificar o número proporcional a 12. Por exemplo, se queremos 3 colunas de tamanhos iguais nós usamos .col-sm-4

Vamos testar alguns layouts com um editor online como o https://html5-editor.net/

## Estrutura Básica

```html
<!DOCTYPE html>
<html lang="pt">
<head>
 <title>Bootstrap</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
 <h1>Grid Básico</h1>
  <div class="container-fluid">


   <div class="row">
     <div class="col-sm-6" style="background-color:red;">50%</div>
     <div class="col-sm-6" style="background-color:grey;">50%</div>
   </div>
   <br>

 
   <div class="row">
     <div class="col-sm-4" style="background-color:red;">33.33%</div>
     <div class="col-sm-4" style="background-color:grey;">33.33%</div>
     <div class="col-sm-4" style="background-color:red;">33.33%</div>
   </div>
   <br>

   <div class="row">
     <div class="col-sm" style="background-color:red;">25%</div>
     <div class="col-sm" style="background-color:grey;">25%</div>
     <div class="col-sm" style="background-color:red;">25%</div>
     <div class="col-sm" style="background-color:grey;">25%</div>
   </div>
   <br>
 

   <div class="row">
     <div class="col" style="background-color:red;">25%</div>
     <div class="col" style="background-color:grey;">25%</div>
     <div class="col" style="background-color:red;">25%</div>
     <div class="col" style="background-color:grey;">25%</div>
   </div>
 </div>
</div>

</body>
</html>
```

## Três colunas Iguais

```html
<!DOCTYPE html>
<html lang="pt">

<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container-fluid">

 <h2>Três colunas iguais</h2>

 <p>Use a classe .col em um número específico de elementos e o Bootstrap vai reconhecer quantos elementos são e criar colunas de largura igual. Abaixo cada uma tem 33,33% de largura.</p>
 <div class="row">
   <div class="col" style="background-color:grey;">.col</div>
   <div class="col" style="background-color:red;">.col</div>
   <div class="col" style="background-color:grey;">.col</div>
 </div>
</div>

</body>
</html>
```

## Três Colunas Iguais usando Números

```html
<!DOCTYPE html>

<html lang="pt">

<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container-fluid">

 <h2>Três colunas iguais</h2>

 <p>Também é possíve usar números para controlar a largura da coluna. Apenas garanta que elas somem 12</p>
 <div class="row">
   <div class="col-4" style="background-color:grey;">.col-4</div>
   <div class="col-4" style="background-color:red;">.col-4</div>
   <div class="col-4" style="background-color:grey;">.col-4</div>
 </div>
</div>

</body>
</html>
```

## Três Colunas Desiguais

```html
<!DOCTYPE html>

<html lang="pt">

<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>


<div class="container-fluid">

 <h2>Três Colunas Desiguais</h2>

 <p>Para criar colunas desiguais, você tem que usar números. O exemplo abaixo faz uma proporção 25%/50%/25%:</p>
 <div class="row">
   <div class="col-3" style="background-color:grey;">.col-3</div>
   <div class="col-6" style="background-color:red;">.col-6</div>
   <div class="col-3" style="background-color:grey;">.col-3</div>
 </div>
</div>

</body>

</html>
```

## Horizontal para Empilhado

```html
<!DOCTYPE html>

<html lang="pt">
<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>


<div class="container-fluid">

 <h1>Horizontal para Empilhado</h1>

 <p>Proporção de 75%/25% em dispositivos pequenos, médios, grandes e estra grandes. Em dispositivos extra pequenos, eles vão empilhar.</p>    

 <div class="container-fluid">    
   <div class="row">
     <div class="col-sm-9" style="background-color:red;">col-sm-9</div>
     <div class="col-sm-3" style="background-color:grey;">col-sm-3</div>
   </div>
 </div>

 <br>

 <p>Proporção de 33% em dispositivos pequenos, médios, grandes e estra grandes. Em dispositivos extra pequenos, eles vão empilhar.</p>        

 <div class="container-fluid">    
   <div class="row">
     <div class="col-sm" style="background-color:grey;">col-sm</div>
     <div class="col-sm" style="background-color:red;">col-sm</div>
     <div class="col-sm" style="background-color:grey;">col-sm</div>
   </div>
 </div>
</div>
 

</body>
</html>
```

## Mix

```html
<!DOCTYPE html>

<html lang="pt">

<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>


<div class="container-fluid">

 <h1>Mix</h1>

 <p>Proporção de 50%/50% em dispositivos extra pequenos e 75%/25% em dispositivos grandes.</p>    

 <div class="container-fluid">    
   <div class="row">
     <div class="col-6 col-sm-9" style="background-color:red;">col-6 col-sm-9</div>
     <div class="col-6 col-sm-3" style="background-color:grey;">col-6 col-sm-3</div>
   </div>
 </div>
 <br>

 <p>Proporção de 58%/42% em dispositivos extra pequeno, pequeno e médio e 66,3%/33,3% em dispositivos grandes e extra grandes.</p>    

 <div class="container-fluid">    
   <div class="row">
     <div class="col-7 col-lg-8" style="background-color:red;">col-7 col-lg-8</div>
     <div class="col-5 col-lg-4" style="background-color:grey;">col-5 col-lg-4</div>
   </div>
 </div>
 <br>

 <p>Proporção de 25%/75% em dispositivos pequenos, 50%/50% nos médios, e 33%/66% grandes e extra grandes. Nos extra pequenos, vai empilhar.</p>    

 <div class="container-fluid">    
   <div class="row">
     <div class="col-sm-3 col-md-6 col-lg-4" style="background-color:grey;">col-sm-3 col-md-6 col-lg-4</div>
     <div class="col-sm-9 col-md-6 col-lg-8" style="background-color:red;">col-sm-9 col-md-6 col-lg-8</div>
   </div>
 </div>
</div>

</body>

</html>
```

## Exemplo de Layout Completo

```html
<!DOCTYPE html>

<html lang="pt">

<head>
 <title>Layout de Exemplo</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

 <style>
 .imgfalsa {
   height: 200px;
   background: #aaa;
 }
 </style>
</head>
<body>

<div class="jumbotron text-center" style="margin-bottom:0">
 <h1>Página usando Bootstrap 4</h1>
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

 <a class="navbar-brand" href="#">Menu</a>

 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
   <span class="navbar-toggler-icon"></span>
 </button>

 <div class="collapse navbar-collapse" id="collapsibleNavbar">
   <ul class="navbar-nav">
     <li class="nav-item">
       <a class="nav-link" href="#">Link</a>
     </li>
     <li class="nav-item">
       <a class="nav-link" href="#">Link</a>
     </li>
     <li class="nav-item">
       <a class="nav-link" href="#">Link</a>
     </li>  
   </ul>
 </div>
</nav>


<div class="container" style="margin-top:30px">
 <div class="row">

   <div class="col-sm-4">
     <h2>Sobre mim</h2>
     <h5>Foto:</h5>
     <div class="imgfalsa">Image Falsa</div>
     <p>culpa qui officia deserunt mollit anim..</p>

     <h3>Alguns Links</h3>

     <p>Lorem ipsum dolor sit ame.</p>

     <ul class="nav nav-pills flex-column">
       <li class="nav-item">
         <a class="nav-link active" href="#">Ativo</a>
       </li>

       <li class="nav-item">
         <a class="nav-link" href="#">Link</a>
       </li>

       <li class="nav-item">
         <a class="nav-link" href="#">Link</a>
       </li>

       <li class="nav-item">
         <a class="nav-link disabled" href="#">Desabilitado</a>
       </li>
     </ul>
     <hr class="d-sm-none">
   </div>

   <div class="col-sm-8">
     <h2>TÍTULO</h2>

     <h5>Descrição, 7 Set, 2019</h5>

     <div class="imgfalsa">Imagem Falsa</div>

     <p>Algum texto...</p>

     <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>

     <br>

     <h2>TÍTULO</h2>
     <h5>Descrição, 2 Set, 2019</h5>
     <div class="imgfalsa">Imagem Falsa</div>
     <p>Algum texto...</p>
     <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
   </div>

 </div>
</div>

<div class="jumbotron text-center" style="margin-bottom:0">
 <p>Footer</p>
</div>

</body>

</html>
```

