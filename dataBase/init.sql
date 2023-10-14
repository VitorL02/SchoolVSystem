CREATE DATABASE schoolvdb;
CREATE TABLE tb_materia(
  id_materia SERIAL PRIMARY KEY,
  materia VARCHAR(255) NOT NULL,
  turno_materia VARCHAR(30) NOT NULL
);

CREATE TABLE tb_curso (
  id_curso SERIAL PRIMARY KEY,
  curso VARCHAR(255) NOT NULL,
  turno_curso VARCHAR(30) NOT NULL
);
CREATE TABLE tb_estudante (
  id_estudante SERIAL PRIMARY KEY,
  nome_completo VARCHAR(255) NOT NULL,
  matricula BIGINT NOT NULL ,
  data_de_cadastro TIMESTAMP,
  telefone VARCHAR(20),
  email VARCHAR(255) NOT NULL,
  data_nascimento DATE NOT NULL,
  id_materia integer REFERENCES tb_materia,
  id_curso integer REFERENCES tb_curso NOT NULL
);
CREATE TABLE tb_frequencia (
  id_frequencia SERIAL PRIMARY KEY,
  id_materia integer REFERENCES tb_materia,
  id_estudante integer REFERENCES tb_estudante,
  falta_justificada BOOLEAN
);
CREATE TABLE tb_usuarios(
    id SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL
);