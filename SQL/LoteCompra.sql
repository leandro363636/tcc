/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mateus
 * Created: 14/03/2019
 */

CREATE TABLE tb_lote_compra (
    quantidade_lote int,
    id_compra INT,
    id_lote INT,
    CONSTRAINT fk_lote_compra FOREIGN KEY (id_compra) REFERENCES tb_compra(id_compra),
    CONSTRAINT fk_compra_lote FOREIGN KEY (id_lote) REFERENCES tb_lote(id_lote)
);
