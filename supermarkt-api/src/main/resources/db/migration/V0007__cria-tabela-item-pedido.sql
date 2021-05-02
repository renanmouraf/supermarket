CREATE TABLE item_do_pedido (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  observacao varchar(255) DEFAULT NULL,
  quantidade int(11) NOT NULL,
  item_estoque_id bigint(20) NOT NULL,
  pedido_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (item_estoque_id) REFERENCES item_estoque(id),
  FOREIGN KEY (pedido_id) REFERENCES pedido(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
