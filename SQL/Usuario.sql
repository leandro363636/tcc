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
    id_usuario INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email_usuario VARCHAR(100),
    senha_usuario VARCHAR(200),
    nome_usuario VARCHAR(50),
    sobrenome_usuario VARCHAR(150)
);
