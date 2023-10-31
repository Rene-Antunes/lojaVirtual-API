create table forma_pagamento (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	primary key (id)
) engine=InnoDB;



create table foto_produto (
	produto_id bigint not null,
	tamanho bigint,
	content_type varchar(255), 
	descricao varchar(255), nome_arquivo varchar(255), 
	primary key (produto_id)
) engine=InnoDB;


create table grupo (
	id bigint not null auto_increment, 
	nome varchar(60) not null, 
	primary key (id)
) engine=InnoDB;

create table grupo_permissoes (
	grupo_id bigint not null, 
	permissoes_id bigint not null, 
	primary key (grupo_id, permissoes_id)
) engine=InnoDB;


create table item_pedido (
	preco_total decimal(38,2), 
	preco_unitario decimal(38,2), 
	quantidade integer, id bigint not null auto_increment, 
	pedido_id bigint not null, produto_id bigint not null, 
	observacao varchar(255), 
	primary key (id)
) engine=InnoDB;

create table pedido (
	sub_total decimal(38,2),
	valor_frete decimal(38,2), 
	valor_total decimal(38,2), 
	data_cancelamento datetime(6), 
	data_confirmacao datetime(6), 
	data_cricao datetime(6), 
	data_entrega datetime(6), 
	data_enviado datetime(6), 
	data_preparacao datetime(6), 
	forma_pagamento_id bigint not null, 
	id bigint not null auto_increment, 
	celular varchar(255) not null, 
	codigo varchar(255), 
	email varchar(255) not null, 
	endereco_bairro varchar(255), 
	endereco_cep varchar(255), 
	endereco_cidade varchar(255), 
	endereco_complemento varchar(255), 
	endereco_estado varchar(255), 
	endereco_logradouro varchar(255), 
	endereco_numero varchar(255), 
	endereco_rua varchar(255), 
	nome varchar(255) not null, 
	status_pedido enum ('CANCELADO','CONFIRMADO','CRIADO','ENTREGUE','ENVIADO_PARA_TRANSPORTADORA','PEDIDO_SENDO_PREPARADO'), 
	telefone varchar(255) not null, primary key (id)
) engine=InnoDB;

create table permissao (
	id bigint not null auto_increment, 
	descricao varchar(255) not null,
	nome varchar(100) not null, 
	primary key (id)
) engine=InnoDB;

create table produto (
	ativo bit not null, 
	valor decimal(38,2) not null, 
	id bigint not null auto_increment, 
	categoria varchar(255) not null, 
	descricao varchar(255) not null, 
	nome varchar(255) not null, 
	primary key (id)
) engine=InnoDB;

create table usuario (
	id bigint not null auto_increment, 
	celular varchar(255) not null, 
	email varchar(255) not null, 
	nome varchar(255) not null, 
	telefone varchar(255) not null, 
	primary key (id)
) engine=InnoDB;

create table usuario_grupos (
	grupos_id bigint not null, 
	usuario_id bigint not null, 
	primary key (grupos_id, usuario_id)
) engine=InnoDB;


alter table foto_produto add constraint fk_foto_produto
foreign key (produto_id) references produto (id);

alter table grupo_permissoes add constraint fk_grupo_permissao_permissao 
foreign key (permissoes_id) references permissao (id);

alter table grupo_permissoes add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table item_pedido add constraint fk_item_pedido_pedido
foreign key (pedido_id) references pedido (id);

alter table item_pedido add constraint fk_item_produto_produto 
foreign key (produto_id) references produto (id);

alter table pedido add constraint fk_pedido_forma_pagamento 
foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table usuario_grupos add constraint fk_usuario_grupos_grupo 
foreign key (grupos_id) references grupo (id);

alter table usuario_grupos add constraint fk_usuario_grupo_usuario 
foreign key (usuario_id) references usuario (id);





