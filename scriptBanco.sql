--CREATE DATABASE gif;

--usuario
CREATE ROLE gif
LOGIN
PASSWORD 'gif';

GRANT ALL PRIVILEGES
ON ALL TABLES IN SCHEMA public
TO gif;

GRANT ALL PRIVILEGES
ON ALL SEQUENCES IN SCHEMA public
TO gif;


--tabelas

DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS distribuidor;
DROP TABLE IF EXISTS genero;
DROP TABLE IF EXISTS jogo;
DROP TABLE IF EXISTS generoJogo;
DROP TABLE IF EXISTS bibliotecajogos;
DROP TABLE IF EXISTS carrinho;
DROP TABLE IF EXISTS carrinhoitens;

CREATE TABLE usuario
(
    id SERIAL PRIMARY KEY,
    nome varchar(20) NOT NULL,
    apelido varchar(20) NOT NULL,
    senha varchar(20) NOT NULL,
    administrador boolean DEFAULT false,
    datanascimento timestamp without time zone
);

CREATE TABLE distribuidor
(
    id SERIAL PRIMARY KEY,
    nome varchar(20) NOT NULL,
    cnpj varchar(20) NOT NULL,
    senha varchar(20) NOT NULL
);

CREATE TABLE genero(
    id SERIAL PRIMARY KEY,
    descricao text DEFAULT ''
);

CREATE TABLE jogo
(
    id SERIAL PRIMARY KEY,
    titulo varchar(30) NOT NULL,
    subtitulo varchar(50) NOT NULL,
    descricao text DEFAULT '',
    precopadrao NUMERIC(6,2) NOT NULL,
    preçopromocao NUMERIC(6,2),
    estaempromocao BOOL DEFAULT false,
    datalançamento timestamp without time zone,
    iddistribuidor int,
    idcriador int,
    FOREIGN KEY (iddistribuidor) REFERENCES distribuidor ON DELETE CASCADE,
    FOREIGN KEY (idcriador) REFERENCES usuario ON DELETE CASCADE
);

CREATE TABLE generoJogo(
    idgenero int,
    idjogo int,
    PRIMARY KEY(idgenero, idjogo),
    FOREIGN KEY (idgenero) REFERENCES genero ON DELETE CASCADE,
    FOREIGN KEY (idjogo) REFERENCES jogo ON DELETE CASCADE
);


CREATE TABLE bibliotecajogos(
    iddono int,
    idjogo int,
    dataaquisicao timestamp without time zone,
    tempodejogo timestamp without time zone,
    percentualconquistas numeric(3,2) DEFAULT 0
);

CREATE TABLE carrinho(
    id SERIAL PRIMARY KEY,
    idusuario int,
    valorTotal numeric(4,2),
    datacriacao timestamp without time zone,
    ultimaatualizacao timestamp without time zone,
    FOREIGN KEY (idusuario) REFERENCES usuario ON DELETE CASCADE
    
);

CREATE TABLE carrinhoitens(
    idjogo int,
    idcarrinho int,
    PRIMARY KEY(idjogo, idcarrinho),
    FOREIGN KEY (idjogo) REFERENCES jogo ON DELETE CASCADE,
    FOREIGN KEY (idcarrinho) REFERENCES carrinho ON DELETE CASCADE

);

insert into usuario(nome, apelido, senha, datanascimento) 
VALUES ('a','a','a',null)