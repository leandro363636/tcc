/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  matri
 * Created: 23/02/2019
 */

/* MySQL */
CREATE TABLE tb_ingresso (
    id_ingresso INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    serial_ingresso CHAR(10),
    id_evento INT(6) UNSIGNED,
    id_usuario INT(6) UNSIGNED,
    FOREIGN KEY (id_evento) REFERENCES tb_evento(id_evento),
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
);
/* PostegreSQL*/
CREATE TABLE tb_ingresso (
    id_ingresso SERIAL PRIMARY KEY,
    serial_ingresso CHAR(10),
    id_evento INT,
    id_usuario INT,
    FOREIGN KEY (id_evento) REFERENCES tb_evento(id_evento),
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario)
);

