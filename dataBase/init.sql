CREATE DATABASE schoolvdb;

CREATE TABLE tb_materia (
  id_materia BIGSERIAL PRIMARY KEY,
  materia VARCHAR(255) NOT NULL,
  turno_materia VARCHAR(30) NOT NULL
);

CREATE TABLE tb_curso (
  id_curso BIGSERIAL PRIMARY KEY,
  curso VARCHAR(255) NOT NULL,
  turno_curso VARCHAR(30) NOT NULL
);

CREATE TABLE tb_frequencia (
  id_frequencia BIGSERIAL PRIMARY KEY,
  id_materia BIGINT,
  id_estudante BIGINT,
  data_frequencia DATE NOT NULL,
  falta_justificada BOOLEAN
);

CREATE TABLE tb_estudante (
  id_estudante BIGSERIAL PRIMARY KEY,
  nome_completo VARCHAR(255) NOT NULL,
  matricula BIGINT NOT NULL,
  data_de_cadastro TIMESTAMP,
  telefone VARCHAR(20),
  email VARCHAR(255) NOT NULL,
  data_nascimento DATE NOT NULL,
  id_curso BIGINT,
  id_frequencia BIGINT
);

CREATE TABLE tb_usuarios (
  id BIGSERIAL PRIMARY KEY,
  login VARCHAR(100) NOT NULL,
  senha VARCHAR(255) NOT NULL
);

CREATE TABLE tb_materia_estudante (
  id_materia_estudante BIGSERIAL PRIMARY KEY,
  id_materia BIGINT,
  id_estudante BIGINT
);

CREATE TABLE tb_usuario_roles (
  id_usuario_roles BIGSERIAL PRIMARY KEY,
  id_usuario BIGINT,
  role_usuario VARCHAR(10) NOT NULL,
  usuario_ativo BOOLEAN NOT NULL
);

ALTER TABLE tb_frequencia
  ADD CONSTRAINT fk_tb_frequencia_materia
  FOREIGN KEY (id_materia) REFERENCES tb_materia (id_materia);

ALTER TABLE tb_frequencia
  ADD CONSTRAINT fk_tb_frequencia_estudante
  FOREIGN KEY (id_estudante) REFERENCES tb_estudante (id_estudante);

ALTER TABLE tb_estudante
  ADD CONSTRAINT fk_tb_estudante_curso
  FOREIGN KEY (id_curso) REFERENCES tb_curso (id_curso);

ALTER TABLE tb_estudante
  ADD CONSTRAINT fk_tb_estudante_frequencia
  FOREIGN KEY (id_frequencia) REFERENCES tb_frequencia (id_frequencia);

ALTER TABLE tb_materia_estudante
  ADD CONSTRAINT fk_tb_materia_estudante_materia
  FOREIGN KEY (id_materia) REFERENCES tb_materia (id_materia);

ALTER TABLE tb_materia_estudante
  ADD CONSTRAINT fk_tb_materia_estudante_estudante
  FOREIGN KEY (id_estudante) REFERENCES tb_estudante (id_estudante);

ALTER TABLE tb_usuario_roles
  ADD CONSTRAINT fk_tb_usuario_roles_usuario
  FOREIGN KEY (id_usuario) REFERENCES tb_usuarios (id);


CREATE TABLE tb_solicitacao_nova_senha(
      id_solicitacao_nova_senha BIGSERIAL PRIMARY KEY,
      email_usuario_solicitacao VARCHAR(100) NOT NULL,
      codigo_solicitacao_senha VARCHAR(7) NOT NULL
);