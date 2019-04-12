/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  matri
 * Created: 11/04/2019
 */

CREATE TABLE tb_endereco (
    id_endereco SERIAL PRIMARY KEY,
    rua_endereco VARCHAR(100),
    numero_endereco INT,
    cep_endereco CHAR(8),
    id_cidade INT,
    referencia_endereco VARCHAR(50),
    id_referencia INT,
    CONSTRAINT fk_endereco_cidade FOREIGN KEY (id_cidade) REFERENCES tb_cidade(id_cidade),
    CONSTRAINT uq_referencia_id UNIQUE(referencia_endereco, id_referencia)
);