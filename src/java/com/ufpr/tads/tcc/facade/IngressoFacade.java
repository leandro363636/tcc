/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Ingresso;
import com.ufpr.tads.tcc.dao.IngressoDAO;
import java.sql.SQLException;

/**
 *
 * @author mateus
 */
public class IngressoFacade {
    public static boolean buscarPorSerialIdEvento(String serial, int id) throws SQLException, ClassNotFoundException {
        IngressoDAO ingressodao = new IngressoDAO();
        return ingressodao.selectIngressoBySerialAndIdEvento(serial, id);
    }
    
    public static int buscarIdPorSerialIdEvento(String serial, int id) throws SQLException, ClassNotFoundException {
        IngressoDAO ingressodao = new IngressoDAO();
        return ingressodao.selectIdIngressoBySerialAndIdEvento(serial, id);
    }
    
    public static void inserir(Ingresso ingresso) throws ClassNotFoundException, SQLException {
        IngressoDAO ingressodao = new IngressoDAO();
        ingressodao.insertIngresso(ingresso);
    }
    
    public static void acessar(int id) throws ClassNotFoundException, SQLException {
        IngressoDAO ingressodao = new IngressoDAO();
        ingressodao.accessIngresso(id);
    }
}
