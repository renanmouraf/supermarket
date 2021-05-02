CREATE TABLE pedido (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  data_hora datetime NOT NULL,
  situacao varchar(255) NOT NULL,
  supermercado_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (supermercado_id) REFERENCES supermercado(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
