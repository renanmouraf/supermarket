CREATE TABLE item_estoque (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(150) DEFAULT NULL,
  descricao varchar(255) DEFAULT NULL,
  quantidade int(11) NOT NULL,
  preco decimal(19,2) NOT NULL,
  preco_promocional decimal(19,2) DEFAULT NULL,
  supermercado_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (supermercado_id) REFERENCES supermercado(id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
