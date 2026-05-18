CREATE DATABASE gif;

-- usuario do sistema
CREATE ROLE gif
LOGIN
PASSWORD 'gif';

GRANT ALL PRIVILEGES
ON ALL TABLES IN SCHEMA public
TO gif;

GRANT ALL PRIVILEGES
ON ALL SEQUENCES IN SCHEMA public
TO gif;

-- remover tabelas
DROP TABLE IF EXISTS carrinhoitens;
DROP TABLE IF EXISTS carrinho;
DROP TABLE IF EXISTS bibliotecajogos;
DROP TABLE IF EXISTS generoJogo;
DROP TABLE IF EXISTS jogo;
DROP TABLE IF EXISTS genero;
DROP TABLE IF EXISTS distribuidor;
DROP TABLE IF EXISTS usuario;

-- tabelas

CREATE TABLE usuario
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    apelido VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    datanascimento TIMESTAMP
);

CREATE TABLE distribuidor
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL
);

CREATE TABLE genero
(
    id SERIAL PRIMARY KEY,
    descricao TEXT DEFAULT ''
);

CREATE TABLE jogo
(
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(30) NOT NULL,
    subtitulo VARCHAR(50) NOT NULL,
    descricao TEXT DEFAULT '',
    precopadrao NUMERIC(6,2) NOT NULL,
    precopromocao NUMERIC(6,2),
    estaempromocao BOOLEAN DEFAULT false,
    datalancamento TIMESTAMP,
    iddistribuidor INT,
    idcriador INT,

    FOREIGN KEY (iddistribuidor)
    REFERENCES distribuidor(id)
    ON DELETE CASCADE,

    FOREIGN KEY (idcriador)
    REFERENCES usuario(id)
    ON DELETE CASCADE
);

CREATE TABLE generoJogo
(
    idgenero INT,
    idjogo INT,

    PRIMARY KEY(idgenero, idjogo),

    FOREIGN KEY (idgenero)
    REFERENCES genero(id)
    ON DELETE CASCADE,

    FOREIGN KEY (idjogo)
    REFERENCES jogo(id)
    ON DELETE CASCADE
);

CREATE TABLE bibliotecajogos
(
    iddono INT,
    idjogo INT,
    dataaquisicao TIMESTAMP,
    tempodejogo TIMESTAMP,
    percentualconquistas NUMERIC(3,2) DEFAULT 0
);

CREATE TABLE carrinho
(
    id SERIAL PRIMARY KEY,
    idusuario INT,
    datacriacao TIMESTAMP,
    ultimaatualizacao TIMESTAMP,

    FOREIGN KEY (idusuario)
    REFERENCES usuario(id)
    ON DELETE CASCADE
);

CREATE TABLE carrinhoitens
(
    idjogo INT,
    idcarrinho INT,
    quantidade INT,

    PRIMARY KEY(idjogo, idcarrinho),

    FOREIGN KEY (idjogo)
    REFERENCES jogo(id)
    ON DELETE CASCADE,

    FOREIGN KEY (idcarrinho)
    REFERENCES carrinho(id)
    ON DELETE CASCADE
);