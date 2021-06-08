insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Japonesa');

insert into restaurante (nome, taxa_frete, cozinha_codigo) values ('Thai Food', 12.90, 1);
insert into restaurante (nome, taxa_frete, cozinha_codigo) values ('San Tao', 9.90, 2);

insert into estado(nome) values ('Rio Grande do Sul');
insert into estado(nome) values ('Rio de Janeiro');

insert into cidade(nome, estado_id) values ('Canoas', 1);
insert into cidade(nome, estado_id) values ('Rio de Janeiro', 2);

insert into forma_pagamento(descricao) values ('Crédito');
insert into forma_pagamento(descricao) values ('Débito');

insert into permissao(nome, descricao) values ('admin', 'Todas as permissões');
insert into permissao(nome, descricao) values ('usuario_comum', 'Permite realizar compra');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values(1, 1), (1, 2), (2,1), (2,2);