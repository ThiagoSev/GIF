--Dados base do sistema

BEGIN TRANSACTION;

--limpa os dados antigos
TRUNCATE TABLE usuario RESTART IDENTITY CASCADE;
TRUNCATE TABLE distribuidor RESTART IDENTITY CASCADE;
TRUNCATE TABLE genero RESTART IDENTITY CASCADE;
TRUNCATE TABLE jogo RESTART IDENTITY CASCADE;
TRUNCATE TABLE generoJogo RESTART IDENTITY CASCADE;
TRUNCATE TABLE bibliotecajogos RESTART IDENTITY CASCADE;
TRUNCATE TABLE carrinho RESTART IDENTITY CASCADE;
TRUNCATE TABLE carrinhoitens RESTART IDENTITY CASCADE;

--usuário padrão para o desenvolvimento
insert into usuario(nome, apelido, senha, datanascimento, administrador) 
VALUES ('a','a','a',null, true);

--distribuidor padrão
insert into distribuidor(nome, cnpj, senha) 
VALUES ('Distribuidor Base','90.064.414/0001-77','1234');

--jogos de exemplo
INSERT INTO jogo
(titulo, subtitulo, descricao, precopadrao, precopromocao, estaempromocao, datalancamento, iddistribuidor, idcriador, imagem)
VALUES
(
    'Inside', 'A dark puzzle platformer',
    'Explore a mysterious world filled with danger and suspense.',
    49.90, 19.90,true,
    '2016-06-29',
    1, 1,
    'imagens/jogos/inside.jpg'
),
(
    'Hollow Knight - Silksong',
    'Venture into a kingdom ruled by silk and songs!',
    'An epic action-adventure through a vast ruined kingdom.',
    59.90,
    NULL,
    false,
    '2017-02-24',
    1,
    1,
    'imagens/jogos/silksong.jpeg'
),
(
    'Celeste',
    'Climb the mountain',
    'A challenging platformer about overcoming personal struggles.',
    36.99,
    18.49,
    true,
    '2018-01-25',
    1,
    1,
    'imagens/jogos/celeste.jpg'
),
(
    'Stardew Valley',
    'Build your dream farm',
    'A farming simulation game with RPG elements.',
    24.99,
    NULL,
    false,
    '2016-02-26',
    1,
    1,
    'imagens/jogos/stardewValley.jpg'
),
(
    'Dead Cells',
    'Roguelike metroidvania',
    'Fight your way through an ever-changing castle.',
    47.99,
    23.99,
    true,
    '2018-08-07',
    1,
    1,
    'imagens/jogos/DeadCells.jpg'
),
(
    'Cuphead',
    'Run and gun action',
    'Classic cartoon-inspired action game with difficult bosses.',
    54.90,
    NULL,
    false,
    '2017-09-29',
    1,
    1,
    'imagens/jogos/cuphead.jpg'
);

COMMIT TRANSACTION;