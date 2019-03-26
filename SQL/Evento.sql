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
    id_evento SERIAL PRIMARY KEY,
    nome_evento VARCHAR(200),
    data_inicio_evento TIMESTAMP,
    data_fim_evento TIMESTAMP,
    endereco_evento VARCHAR(200),
    descricao_evento TEXT,
    aprovacao_evento BOOLEAN,
    id_usuario INT,
    CONSTRAINT fk_evento_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
);

INSERT INTO tb_evento(nome_evento, id_usuario) values ('Evento teste', 1);