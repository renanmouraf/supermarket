CREATE TABLE pagamento (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  valor decimal(19,2) NOT NULL,
  nome varchar(100) DEFAULT NULL,
  numero varchar(19) DEFAULT NULL,
  expiracao varchar(7) NOT NULL,
  codigo varchar(3) DEFAULT NULL,
  situacao varchar(255) NOT NULL,
  tipo_pagamento_id bigint(20) NOT NULL,
  pedido_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (tipo_pagamento_id) REFERENCES tipo_pagamento(id),
  FOREIGN KEY (pedido_id) REFERENCES pedido(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;