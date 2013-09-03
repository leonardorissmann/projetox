insert into revisaoauditoria values (1, current_date, '0:0:0:0:0', 'admin');
insert into usuario_aud values (1, 1, 0, true, 'admin', 'administrador', 'ROLE_ADMIN', md5('admin'));
insert into usuario values (1, true, 'admin', 'Administrador', 'ROLE_ADMIN', md5('admin'));