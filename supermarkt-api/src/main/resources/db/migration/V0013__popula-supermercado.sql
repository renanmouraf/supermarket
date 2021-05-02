-- Líder
INSERT INTO supermercado (id, favorito, cnpj, nome, descricao, cep, endereco, taxa_de_entrega_em_reais, tempo_de_entrega_minimo_em_minutos, tempo_de_entrega_maximo_em_minutos)
values (1, true, '26685813000156', 'Líder Praça Brasil', 'Líder em preços baixos', '66050100', 'R. Ferreira Pena, 1083 - Umarizal' , 9, 25, 40);

INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (1, 1);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (1, 2);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (1, 3);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (1, 4);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (1, 5);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (1, 'Cerveja Heineken', 'Cerveja Heineken 600ml', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (2, 'Cerveja Bohemia', 'Cerveja Bohemia 350ml', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (3, 'Iogurte de Morango', 'Iogurte 500ml', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (4, 'Chocolate Nestlé', 'Chocolate Nestlé 400g', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (5, 'Coxa e Sobrecoxa de Frango', 'Coxa e Sobrecoxa de Frango 1Kg', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (6, 'Refrigerante Coca-Cola', 'Coca-Cola 600ml', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (7, 'Manteiga do Campo', 'Manteiga 200g', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (8, 'Queijo Minas Tirolez', 'Queijo Minas 650g', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (9, 'Sabão em Pó Omo', 'Sabão em Pó Omo', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (10, 'Detergente Ypê', 'Detergente Ypê 300ml', 10, 3.5, 2.5, 1);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (11, 'Açaí', 'Açaí raíz, nada de granola, leite em pó e banana', 10, 3.5, 2.5, 1);


-- Formosa
INSERT INTO supermercado (id, favorito, cnpj, nome, descricao, cep, endereco, taxa_de_entrega_em_reais, tempo_de_entrega_minimo_em_minutos, tempo_de_entrega_maximo_em_minutos)
values (2, true, '47446715000136', 'Formosa Umarizal', 'Todo dia as melhores ofertas', '66050080', 'R. Curuçá, 580 - Telégrafo' , 7, 30, 50);

INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (2, 1);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (2, 2);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (2, 3);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (2, 4);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (2, 5);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (12, 'Nutella', 'Nutella 200g', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (13, 'Sucrilhos Kellogs', 'Sucrilhos Kellogs 1Kg', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (14, 'Arroz Tio Urbano', 'Arroz Tipo 1 5Kg', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (15, 'Cartela de Ovo', 'Cartela de Ovo com 24un', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (16, 'Caneta Bic', 'Caneta Bic Azul', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (17, 'Lápis Faber-Castell', 'Caixa de lápis de cor com 12 cores', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (18, 'Caderno Tilibra', 'Caderno de 10 matérias', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (19, 'Borracha Mercur', 'Borracha escolar', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (20, 'Café Maranatá', 'Café em Pó 250g', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (21, 'Leite Ninho', 'Lei em Pó 500g', 10, 3.5, 2.5, 2);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (22, 'Biscoito Passatempo', 'Biscoito 200g', 10, 3.5, 2.5, 2);


-- Nazaré
INSERT INTO supermercado (id, favorito, cnpj, nome, descricao, cep, endereco, taxa_de_entrega_em_reais, tempo_de_entrega_minimo_em_minutos, tempo_de_entrega_maximo_em_minutos)
values (3, true, '85714361000152', 'Nazaré da Duque', 'Lugar de comprar barato', '66093029', 'Av. Duque de Caxias, 1101 - Marco' , 12, 20, 60);

INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (3, 1);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (3, 2);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (3, 3);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (3, 4);
INSERT INTO supermercado_tipo_pagamento (supermercado_id, tipo_pagamento_id) values (3, 5);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (23, 'Redbull', 'Bebida energética', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (24, 'Barbeador Gillette', 'Pacote com 4 Barbeadores', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (25, 'Lã de aço Bombril', 'Lã de aço', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (26, 'Toddy em Pó', 'Toddy em Pó 500g', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (27, 'Biscoito Negresco', 'Biscoito Negresco 150g', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (28, 'Geléia de Morango', 'Geléia QueensBerry', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (29, 'Shampoo Protex', 'Shampoo anticaspa', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (30, 'Molho de Tomate Heinz', 'Molho de Tomate 340g', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (31, 'Maionese Hellmanns', 'Maionese 500g', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (32, 'Óleo de Soja Liza', 'Óleo de Soja Liza 900ml', 10, 3.5, 2.5, 3);

INSERT INTO item_estoque (
  id,
  nome,
  descricao,
  quantidade,
  preco,
  preco_promocional,
  supermercado_id
) values (33, 'Pasta de Dente Colgate', 'Pasta de Dente 100g', 10, 3.5, 2.5, 3);

