/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  matri
 * Created: 23/02/2019
 */
/* MYSQL */
CREATE TABLE tb_usuario (
    id_usuario INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email_usuario VARCHAR(100),
    senha_usuario VARCHAR(200),
    nome_usuario VARCHAR(50),
    sobrenome_usuario VARCHAR(150)
);
/* POSTGRES */
CREATE TABLE tb_usuario (
    id_usuario SERIAL PRIMARY KEY,
    email_usuario VARCHAR(100),
    senha_usuario VARCHAR(200),
    nome_usuario VARCHAR(50),
    sobrenome_usuario VARCHAR(150),
    cpf_usuario VARCHAR(11)
);
/*A senha é admin*/
INSERT INTO tb_usuario (email_usuario, senha_usuario, nome_usuario, sobrenome_usuario) VALUES ('admin@admin.com', '21232f297a57a5a743894a0e4a801fc3', 'Admin', 'Admin');
