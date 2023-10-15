CREATE DATABASE schoolvdb;
CREATE TABLE tb_materia(
  id_materia bigint SERIAL PRIMARY KEY,
  materia VARCHAR(255) NOT NULL,
  turno_materia VARCHAR(30) NOT NULL
);
CREATE TABLE tb_curso (
  id_curso bigint SERIAL PRIMARY KEY,
  curso VARCHAR(255) NOT NULL,
  turno_curso VARCHAR(30) NOT NULL
);
CREATE TABLE tb_estudante (
  id_estudante bigint SERIAL PRIMARY KEY,
  nome_completo VARCHAR(255) NOT NULL,
  matricula BIGINT NOT NULL ,
  data_de_cadastro TIMESTAMP,
  telefone VARCHAR(20),
  email VARCHAR(255) NOT NULL,
  data_nascimento DATE NOT NULL,
  id_curso bigint REFERENCES tb_curso NOT NULL,
  id_frequencia bigint REFERENCES tb_frequencia
);
CREATE TABLE tb_frequencia (
  id_frequencia bigint SERIAL PRIMARY KEY,
  id_materia bigint REFERENCES tb_materia,
  id_estudante bigint REFERENCES tb_estudante,
  data_frequencia DATE NOT NULL,
  falta_justificada BOOLEAN
);
CREATE TABLE tb_usuarios(
    id SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL
);
CREATE TABLE tb_materia_estudante(
    id_materia_estudante bigint SERIAL PRIMARY KEY,
    id_materia bigint REFERENCES tb_materia,
    id_estudante bigint REFERENCES tb_estudante
);
CREATE TABLE tb_usuario_roles(
    id_usuario_roles bigint SERIAL PRIMARY KEY,
    id_usuario bigint REFERENCES tb_usuarios NOT NULL,
    role_usuario VARCHAR(10) NOT NULL
);


