CREATE TABLE public.Cidade(
id_cidade serial,
id_estado bigint not null,
nome varchar(255) not null,

constraint PK_Cidade primary key (id_cidade),
constraint FK_IdEstado foreign key (id_estado)
references estado (id_estado)
);
