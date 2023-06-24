create table produto(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    descricao varchar(255) not null,
    preco DOUBLE not null,
    quantidade_em_estoque INT not null,

    primary key(id)

);