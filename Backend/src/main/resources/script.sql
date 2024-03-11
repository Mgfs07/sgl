CREATE TABLE disciplina
(
    id   serial PRIMARY KEY,
    nome varchar(255) NOT NULL
);

CREATE TABLE dia_semana
(
    id   serial PRIMARY KEY,
    nome varchar(255) NOT NULL
);

INSERT INTO dia_semana (nome)
VALUES ('Domingo'),
       ('Segunda-Feira'),
       ('Terça-Feira'),
       ('Quarta-Feira'),
       ('Quinta-Feira'),
       ('Sexta-Feira'),
       ('Sábado');

CREATE TABLE aluno
(
    matricula varchar(255) PRIMARY KEY,
    nome      varchar(255) NOT NULL,
    cod_barra varchar(255) NOT NULL
);

CREATE TABLE coordenadoria
(
    id   serial PRIMARY KEY,
    nome varchar(255) NOT NULL
);

CREATE TABLE coordenador
(
    id   serial PRIMARY KEY,
    nome varchar(255) NOT NULL
);

CREATE TABLE professor
(
    id               serial PRIMARY KEY,
    nome             varchar(255) NOT NULL,
    id_coordenadoria int REFERENCES coordenadoria (id)
);

CREATE TABLE periodo
(
    id          serial PRIMARY KEY,
    descricao   varchar(255) NOT NULL,
    data_inicio date,
    data_fim    date CHECK (data_fim > data_inicio)
);

CREATE TABLE aula
(
    id            serial PRIMARY KEY,
    nome          varchar(255)                  NOT NULL,
    id_periodo    int REFERENCES periodo (id)   NOT null,
    hora_inicio   time                          NOT NULL,
    hora_fim      time                          NOT NULL,
    id_professor  int REFERENCES professor (id) NOT NULL,
    turma         varchar(455),
    id_dia_semana int REFERENCES dia_semana (id),
    id_disciplina int REFERENCES disciplina (id)
);

CREATE TABLE aluno_aula
(
    id_aluno varchar(255) REFERENCES aluno (matricula),
    id_aula  int REFERENCES aula (id),
    PRIMARY KEY (id_aluno, id_aula)

);

CREATE TABLE local
(
    id         serial PRIMARY KEY,
    nome       varchar(255) NOT NULL,
    capacidade int          NOT null
);

CREATE TABLE equipamento
(
    id   serial PRIMARY KEY,
    nome varchar(255) NOT NULL
);

CREATE TABLE local_equipamento
(
    id_local       int REFERENCES local (id),
    id_equipamento int REFERENCES equipamento (id),
    quantidade     int NOT NULL,
    PRIMARY KEY (id_local, id_equipamento)
);

CREATE TABLE evento
(
    id          serial PRIMARY KEY,
    descricao   varchar(255) NOT NULL,
    hora_inicio varchar(255) NOT NULL,
    hora_fim    varchar(255) NOT NULL,
    data        date
);

CREATE TABLE auditoria_evento
(
    id             serial PRIMARY KEY,
    id_professor   int REFERENCES professor (id),
    id_coordenador int REFERENCES coordenador (id),
    acao           varchar(255) NOT NULL,
    id_evento      int REFERENCES evento (id),
    data           date
);


CREATE TABLE auditoria_alocacao
(
    id             serial PRIMARY KEY,
    id_aula        int REFERENCES aula (id),
    id_professor   int REFERENCES professor (id),
    id_coordenador int REFERENCES coordenador (id),
    acao           varchar(255) NOT NULL,
    data           date
);
