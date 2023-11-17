
set foreign_key_checks = 0;

delete from usuario;
delete from produto;
delete from permissao;
delete from pedido;
delete from grupo;
delete from usuario_grupos;
delete from item_pedido;
delete from grupo_permissoes;
delete from forma_pagamento;
delete from foto_produto;

set foreign_key_checks = 1;

alter table usuario auto_increment = 1;
alter table produto auto_increment = 1;
alter table permissao auto_increment = 1;
alter table pedido auto_increment = 1;
alter table grupo auto_increment = 1;
alter table item_pedido auto_increment = 1;
alter table foto_produto auto_increment = 1;
alter table forma_pagamento auto_increment = 1;



insert into permissao(id,nome, descricao) values (1, "geral", "Pode atuar em todo sistema");

insert into permissao(id,nome, descricao) values (2, "finanças", "Pode atuar na parte financeira");


insert into usuario(id, celular, email, nome, senha, data_cadastro, telefone) values (1, "1232342345", "barao@gmail", "Bertucci","123", utc_timestamp(), "34534234234");

insert into grupo(id, nome) values (1, "Administrador");

insert into grupo_permissoes(grupo_id, permissoes_id) values (1, 1);

insert into grupo_permissoes(grupo_id, permissoes_id) values (1, 2);

#Produtos

insert into produto(ativo, valor, id, categoria, descricao, nome) values (false, 95.99, 1, "Pulseira", "Pulseira de ouro", "Pulseira_001");
insert into produto(ativo, valor, id, categoria, descricao, nome) values (true, 50.99, 2, "Cordão", "Cordâo de prata", "Cordao_002");
insert into produto(ativo, valor, id, categoria, descricao, nome) values (true, 30.99, 3, "Anel", "Anel de ouro", "Anel_003");
insert into produto(ativo, valor, id, categoria, descricao, nome) values (false, 150.99, 4, "Brincos", "brincos de ouro", "Brinco_004");

#Forma Pagamento

insert into forma_pagamento(id, descricao) values (1, "dinheiro");
insert into forma_pagamento(id, descricao) values (2, "cartao");

#Pedido

insert into pedido (sub_total, valor_frete, valor_total, data_cancelamento, data_confirmacao, data_criacao, data_entrega, 
data_enviado, data_preparacao, forma_pagamento_id, id, celular, codigo, email, endereco_bairro, endereco_cep, endereco_cidade, 
endereco_complemento, endereco_estado, endereco_logradouro, endereco_numero, endereco_rua, nome, status_pedido, telefone) values 
(49.99, 10, 49.99, null, null, utc_timestamp(), null, null, null, 1, 1, "21 34234234", 
"fc91f248-1de5-41cb-b383-18cf4565953c", "rene@gmail.com", "Chatuba", "26585-061", 
"Mesquita", "casa 1", "Rio de Janeiro", "casa", "716", "rua Dr.Godoy", "Renê Antunes","CONFIRMADO", "21 34095307");

insert into pedido (sub_total, valor_frete, valor_total, data_cancelamento, data_confirmacao, data_criacao, data_entrega, 
data_enviado, data_preparacao, forma_pagamento_id, id, celular, codigo, email, endereco_bairro, endereco_cep, endereco_cidade, 
endereco_complemento, endereco_estado, endereco_logradouro, endereco_numero, endereco_rua, nome, status_pedido, telefone) values 
(100.00, 10, 100.00, null, null,"2023-10-03T08:39:36.229432", null, null, null, 1, 2, "21 34234234", 
"fc91f248-1de5-41cb-b383-18cf4565953c", "rene@gmail.com", "Chatuba", "26585-061", 
"Mesquita", "casa 1", "Rio de Janeiro", "casa", "716", "rua Dr.Godoy", "Renê Antunes","CONFIRMADO", "21 34095307");


insert into pedido (sub_total, valor_frete, valor_total, data_cancelamento, data_confirmacao, data_criacao, data_entrega, 
data_enviado, data_preparacao, forma_pagamento_id, id, celular, codigo, email, endereco_bairro, endereco_cep, endereco_cidade, 
endereco_complemento, endereco_estado, endereco_logradouro, endereco_numero, endereco_rua, nome, status_pedido, telefone) values 
(190.00, 10, 200.00, null,null ,"2023-08-03T08:39:36.229432", null, null, null, 2, 3, "21 34234234", 
"fc91f248-1de5-41cb-b383-18cf4565953c", "rene@gmail.com", "Chatuba", "26585-061", 
"Mesquita", "casa 1", "Rio de Janeiro", "casa", "716", "rua Dr.Godoy", "Renê Antunes","CONFIRMADO", "21 34095307");

#Item Pedido

insert into item_pedido(preco_total, preco_unitario, quantidade, id, pedido_id, produto_id, observacao) values (100, 50, 2, 1, 1, 1, "qualquer uma");

#usuarios_grupos
insert into usuario_grupos (grupos_id, usuario_id) values (1, 1);

