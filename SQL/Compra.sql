/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mateus
 * Created: 14/03/2019
 */

CREATE TABLE tb_compra (
    id_compra SERIAL PRIMARY KEY,
    status_compra VARCHAR(10) CHECK (status_compra IN ('carrinho', 'finalizado')),
    valor_total_compra NUMERIC(6,2),
    id_usuario INT,
    CONSTRAINT fk_compra_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
);
