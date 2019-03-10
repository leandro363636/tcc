/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.dao.EventoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mateus
 */
public class EventoFacade {
    public static List<Evento> buscarTodosEventos() throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventos();
    }
    
    public static List<Evento> buscarTodosEventosPorIdUsuario(int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventosByIdUsuario(id);
    }
    
    public static Evento buscar(int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventoById(id);
    }
    
    public static void remover(int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.deleteEventoById(id);
    }
    
    public static void alterar(Evento evento) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.updateEventoById(evento);
    }
    
    public static void inserir(Evento evento) throws ClassNotFoundException, SQLException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.insertEvento(evento);
    }
}
