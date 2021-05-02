CREATE TABLE supermercado_tipo_pagamento (
  supermercado_id bigint(20) NOT NULL,
  tipo_pagamento_id bigint(20) NOT NULL,
  FOREIGN KEY (supermercado_id) REFERENCES supermercado(id),
  FOREIGN KEY (tipo_pagamento_id) REFERENCES tipo_pagamento(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
