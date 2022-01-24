create table tb_empresa (
                        id  SERIAL not null PRIMARY KEY,
                        razao_social varchar (120) not null,
                        fantasia varchar (120),
                        CNPJ varchar (20) not null,
                        IE  varchar (20) not null,
                        email varchar (255),
                        telefone varchar (20),
                        logradouro varchar (200) not null,
                        cidade varchar (100) not null,
                        estado varchar (2) not null,
                        bairro varchar (100) not null,
                        numero int,
                        cep int not null,
                        ativo boolean not null,
                        data_cadastro date not null
);