CREATE TABLE supermercado (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cnpj varchar(18) NOT NULL,
  descricao varchar(1000) DEFAULT NULL,
  cep varchar(9) NOT NULL,
  endereco varchar(300) NOT NULL,
  taxa_de_entrega_em_reais decimal(19,2) DEFAULT NULL,
  tempo_de_entrega_maximo_em_minutos int(11) DEFAULT NULL,
  tempo_de_entrega_minimo_em_minutos int(11) DEFAULT NULL,
  favorito bit(1) DEFAULT false,
  usuario_id  bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
