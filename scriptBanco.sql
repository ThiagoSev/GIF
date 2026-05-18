CREATE DATABASE gif

--usuario

CREATE ROLE gif
LOGIN
PASSWORD 'gif';

GRANT ALL PRIVILEGES
ON ALL TABLES IN SCHEMA public
TO sistema;

GRANT ALL PRIVILEGES
ON ALL SEQUENCES IN SCHEMA public
TO sistema;


--tabelas

CREATE TABLE IF NOT EXISTS usuario
(
    id SERIAL PRIMARY KEY,
    nome character varchar(20) NOT NULL,
    senha character varchar(20) NOT NULL
)