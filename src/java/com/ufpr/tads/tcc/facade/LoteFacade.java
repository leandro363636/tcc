/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Lote;
import com.ufpr.tads.tcc.dao.EventoDAO;
import com.ufpr.tads.tcc.dao.LoteDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mateus
 */
public class LoteFacade {
    public static List<Lote> buscarTodosLotesPorIdEvento(int id) throws SQLException, ClassNotFoundException {
        LoteDAO lotedao = new LoteDAO();
        return lotedao.selectLotesByIdEvento(id);
    }
    
    public static Lote buscar(int id) throws SQLException, ClassNotFoundException {
        LoteDAO lotedao = new LoteDAO();
        return lotedao.selectLoteById(id);
    }
    
    public static void remover(int id) throws SQLException, ClassNotFoundException {
        LoteDAO lotedao = new LoteDAO();
        lotedao.deleteLoteById(id);
    }
    
    public static void alterar(Lote lote) throws SQLException, ClassNotFoundException {
        LoteDAO lotedao = new LoteDAO();
        lotedao.updateLoteById(lote);
    }
    
    public static void inserir(Lote lote) throws ClassNotFoundException, SQLException {
        LoteDAO lotedao = new LoteDAO();
        lotedao.insertLote(lote);
    }
}
