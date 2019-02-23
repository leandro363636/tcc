/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  matri
 * Created: 23/02/2019
 */

CREATE TABLE tb_evento (
    id_evento INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome_evento VARCHAR(200),
    data_inicio_evento DATETIME,
    data_fim_evento DATETIME,
    data_evento DATETIME,
    endereco_evento VARCHAR(200),
    descricao_evento TEXT,
    aprovacao_evento BOOLEAN,
    id_usuario INT(6) UNSIGNED,
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
);