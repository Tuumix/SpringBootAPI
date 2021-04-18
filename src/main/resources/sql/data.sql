create table Usuario (
	id integer not null primary key,
	dataCadastro Date not null,
	nome varchar(30) not null,
	login varchar(15) not null unique,
	senha varchar(10) not null,
	telefone varchar(10),
	email varchar(10),
	perfil char(1) not null,
	status char(1) not null
);

create table Clientes (
	id integer not null primary key,
	dataCadastro Date not null,
	nome varchar(30) not null,
	cpfCnpj varchar(14) not null,
	logradouro varchar(50) not null,
	cidade varchar(40) not null,
	uf char(2) not null,
	cep varchar(11) not null,
	telefone varchar(11),
	email varchar(100)
)

create table Livro_Caixa(
	id integer not null primary key,
	id_cliente integer not null,
	dataLancemento Date not null,
	descricao varchar(50) not null,
	tipo char(1) not null,
	valor numeric(12,2) not null,
	constraint fk_cliente
		foreign key(id)
			references clientes(id)
)