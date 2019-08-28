/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.dao.RelatorioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mateus
 */
public class RelatorioFacade {
    public static int totalVendas(int id) throws SQLException, ClassNotFoundException {
        RelatorioDAO relatoriodao = new RelatorioDAO();
        return relatoriodao.getVendasTotal(id);
    }
    
    public static double mediaVendas(int id) throws SQLException, ClassNotFoundException {
        RelatorioDAO relatoriodao = new RelatorioDAO();
        return relatoriodao.getVendasMedia(id);
    }
    
    public static ArrayList<HashMap<String, String>> vendasEventos(int id) throws SQLException, ClassNotFoundException {
        RelatorioDAO relatoriodao = new RelatorioDAO();
        return relatoriodao.getVendasEventos(id);
    }
}
