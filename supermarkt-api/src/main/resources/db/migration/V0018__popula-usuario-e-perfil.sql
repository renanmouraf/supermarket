insert into role(authority) values ('ROLE_ADMIN');
insert into role(authority) values ('ROLE_SUPERMERCADO');

-- senha: 123456
insert into usuario (id, name, password) values (1, 'admin', '$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');
insert into usuario (id, name, password) values (2, 'lider', '$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');
insert into usuario (id, name, password) values (3, 'formosa', '$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');
insert into usuario (id, name, password) values (4, 'nazare', '$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');

insert into usuario_authorities (usuario_id, authorities_authority) values (1, 'ROLE_ADMIN');
insert into usuario_authorities (usuario_id, authorities_authority) values (2, 'ROLE_SUPERMERCADO');
insert into usuario_authorities (usuario_id, authorities_authority) values (3, 'ROLE_SUPERMERCADO');
insert into usuario_authorities (usuario_id, authorities_authority) values (4, 'ROLE_SUPERMERCADO');

update supermercado s set s.usuario_id = 2 where s.id = 1;
update supermercado s set s.usuario_id = 3 where s.id = 2;
update supermercado s set s.usuario_id = 4 where s.id = 3;
