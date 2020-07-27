
CREATE TABLE public.Endereco(
id_endereco serial ,
id_cidade bigint not null,
logradouro varchar(255) not null,
complemento varchar(255) not null,
numero varchar (255) not null,
bairro varchar(255) not null,
cep    varchar (50) not null,

constraint PK_EnderecoCliente primary key (id_endereco),
constraint FK_IdCidadeCliente foreign key (id_cidade)
references Cidade (id_cidade)
);
