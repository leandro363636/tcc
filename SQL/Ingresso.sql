/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  matri
 * Created: 23/02/2019
 */

CREATE TABLE tb_ingresso (
    id_ingresso INT SERIAL PRIMARY KEY,
    serial_ingresso CHAR(10),
    id_lote INT,
    id_usuario INT,
    CONSTRAINT fk_ingresso_lote FOREIGN KEY (id_lote) REFERENCES tb_lote(id_lote),
    CONSTRAINT fk_ingresso_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
);

