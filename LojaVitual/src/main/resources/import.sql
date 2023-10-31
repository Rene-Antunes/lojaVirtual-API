
insert into permissao(id,nome, descricao) values (1, "geral", "Pode atuar em todo sistema")

insert into permissao(id,nome, descricao) values (2, "finanças", "Pode atuar na parte financeira")


insert into usuario(id, celular, email, nome, telefone) values (1, "1232342345", "barao@gmail", "Bertucci", "34534234234")

insert into grupo(id, nome) values (1, "Administrador")

insert into grupo_permissoes(grupo_id, permissoes_id) values (1, 1)

insert into grupo_permissoes(grupo_id, permissoes_id) values (1, 2)

#Produtos

insert into produto(ativo, valor, id, categoria, descricao, nome) values (true, 95.99, 1, "Pulseira", "Pulseira de ouro", "Pulseira_001")
insert into produto(ativo, valor, id, categoria, descricao, nome) values (true, 50.99, 2, "Cordão", "Cordâo de prata", "Cordao_002")
insert into produto(ativo, valor, id, categoria, descricao, nome) values (true, 30.99, 3, "Anel", "Anel de ouro", "Anel_003")
insert into produto(ativo, valor, id, categoria, descricao, nome) values (true, 150.99, 4, "Brincos", "brincos de ouro", "Brinco_004")

#Forma Pagamento

insert into forma_pagamento(id, descricao) values (1, "dinheiro")
insert into forma_pagamento(id, descricao) values (2, "cartao")

#Pedido

insert into pedido (sub_total, valor_frete, valor_total, data_cancelamento, data_confirmacao, data_cricao, data_entrega, data_enviado, data_preparacao, forma_pagamento_id, id, celular, codigo, email, endereco_bairro, endereco_cep, endereco_cidade, endereco_complemento, endereco_estado, endereco_logradouro, endereco_numero, endereco_rua, nome, status_pedido, telefone) values (49.99, 10, 49.99, null, null, utc_timestamp(), null, null, null, 1, 1, "21 34234234", "fc91f248-1de5-41cb-b383-18cf4565953c", "rene@gmail.com", "Chatuba", "26585-061", "Mesquita", "casa 1", "Rio de Janeiro", "casa", "716", "rua Dr.Godoy", "Renê Antunes","CRIADO", "21 34095307")

#Item Pedido

insert into item_pedido(preco_total, preco_unitario, quantidade, id, pedido_id, produto_id, observacao) values (100, 50, 2, 1, 1, 1, "qualquer uma")

#usuarios_grupos
insert into usuario_grupos (grupos_id, usuario_id) values (1, 1)