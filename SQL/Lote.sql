/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mateus
 * Created: 14/03/2019
 */

CREATE TABLE tb_lote (
    id_lote SERIAL PRIMARY KEY,
    nome_lote VARCHAR(100),
    quantidade_lote INT,
    preco_lote NUMERIC(6,2),
    id_evento INT,
    CONSTRAINT fk_lote_evento FOREIGN KEY (id_evento) REFERENCES tb_evento(id_evento)
);
