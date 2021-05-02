CREATE TABLE tipo_pagamento (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  forma varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
