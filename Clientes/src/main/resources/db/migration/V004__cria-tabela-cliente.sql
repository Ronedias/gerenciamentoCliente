

CREATE TABLE public.Cliente(
id_cliente serial ,
id_endereco bigint not null,
nome varchar(80) not null,
email varchar(150) not null,
doc_identificacao varchar (14) not null,
tipo_cliente int not null,
dt_nascimento DATE,


constraint PK_Cliente primary key (id_cliente),
constraint FK_IdEndereco foreign key (id_endereco)
references Endereco (id_endereco)
);