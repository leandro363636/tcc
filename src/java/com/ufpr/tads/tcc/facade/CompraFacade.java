/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Compra;
import com.ufpr.tads.tcc.dao.CompraDAO;
import java.sql.SQLException;

/**
 *
 * @author mateus
 */
public class CompraFacade {
    public static Compra buscarCarrinho(int id) throws SQLException, ClassNotFoundException {
        CompraDAO compradao = new CompraDAO();
        return compradao.getCarrinhoById(id);
    }
}
