/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  matri
 * Created: 23/02/2019
 */
CREATE TABLE tb_usuario (
    id_usuario SERIAL PRIMARY KEY,
    email_usuario VARCHAR(100) UNIQUE,
    senha_usuario VARCHAR(200),
    id_referencia INT,
    tipo_usuario CHAR(1) CHECK (tipo_usuario IN ('a', 'o', 'c')),
    ativo_usuario BOOLEAN DEFAULT true
);

CREATE TABLE tb_comprador (
    id_comprador SERIAL PRIMARY KEY,
    nome_comprador VARCHAR(50),
    sobrenome_comprador VARCHAR(100),
    rg_comprador VARCHAR(15),
    cpf_comprador CHAR(11) UNIQUE,
    data_nascimento_comprador DATE,
);

CREATE TABLE tb_administrador (
    id_administrador SERIAL PRIMARY KEY,
    nome_administrador VARCHAR(50),
    sobrenome_administrador VARCHAR(100),
    rg_administrador VARCHAR(15),
    cpf_administrador CHAR(11) UNIQUE,
    data_nascimento_administrador DATE
);

CREATE TABLE tb_organizador (
    id_organizador SERIAL PRIMARY KEY,
    nome_organizador VARCHAR(150) UNIQUE,
    nome_responsavel_organizador VARCHAR(50),
    sobrenome_responsavel_organizador VARCHAR(100),
    cnpj_organizador CHAR(14) UNIQUE,
    rg_responsavel_organizador VARCHAR(15)
);

/*A senha é admin para todos estes usuários*/
INSERT INTO tb_administrador (nome_administrador, sobrenome_administrador, rg_administrador, cpf_administrador, data_nascimento_administrador) VALUES ('Admin', 'da Silva', '111111111', '11111111111', '1997-05-21');
INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ('admin@admin.com', '21232f297a57a5a743894a0e4a801fc3', 1, 'a');
INSERT INTO tb_endereco (rua_endereco, numero_endereco, cep_endereco, id_cidade, referencia_endereco, id_referencia) VALUES ('Rua teste', 123, '12345678', 2878, 'administrador', 1);

INSERT INTO tb_comprador (nome_comprador, sobrenome_comprador, rg_comprador, cpf_comprador, data_nascimento_comprador) VALUES ('Comprador', 'da Silva', '111111111', '2222222222', '1997-05-21');
INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ('comprador@comprador.com', '21232f297a57a5a743894a0e4a801fc3', 1, 'c');
INSERT INTO tb_endereco (rua_endereco, numero_endereco, cep_endereco, id_cidade, referencia_endereco, id_referencia) VALUES ('Rua teste', 123, '12345678', 2878, 'comprador', 1);

INSERT INTO tb_organizador (nome_organizador, nome_responsavel_organizador, sobrenome_responsavel_organizador, rg_responsavel_organizador, cnpj_organizador) VALUES ('Organizador', 'Reponsavel', 'da Silva', '111111111', '11111111111111');
INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ('organizador@organizador.com', '21232f297a57a5a743894a0e4a801fc3', 1, 'o');
INSERT INTO tb_endereco (rua_endereco, numero_endereco, cep_endereco, id_cidade, referencia_endereco, id_referencia) VALUES ('Rua teste', 123, '12345678', 2878, 'organizador', 1);