

## Desafio

O supermercado cadastrado no módulo admin acessa o sistema e, caso você tenha feito o desafio do módulo admin, também tem tipos de pagamento associados.

Obs.: Caso não tenha feito o desafio do módulo admin, lembre que é possível fazer essa associação entre supermercado e tipo pagamento com o SQL abaixo:

```sql
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (4, 5);
```

Porém, ainda falta adicionar itens de estoque ao supermercado novo.

Fazer um CRUD correspondente à entidade `ItemEstoque`.

Os endpoints necessários estão em `ItemEstoqueAPI.java`;

Também é possível inserir um item de estoque diretamente pelo banco com o SQL abaixo, observando o devido `supermercado_id`: 

```sql
INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (90, 'Cerveja Heineken', 'Cerveja Heineken 600ml', 10, 3.5, 2.5, 4);
```
