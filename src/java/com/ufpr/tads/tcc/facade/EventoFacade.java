/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.dao.EventoDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mateus
 */
public class EventoFacade {
    public static List<Evento> buscarTodosEventos(int pagina) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventos(pagina);
    }
        public static List<Evento> buscarTodosEventosPorIdUsuarioPag(int pagina, int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventosIdPag(pagina, id);
    }
    public static List<Evento> buscarTodosEventosPorComFiltros(int pagina, String nomeEvento, int cidade, Date data) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventosWithFilters(pagina, nomeEvento, cidade, data);
    }
    
    public static List<Evento> buscarTodosEventosPorIdUsuario(int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventosByIdUsuario(id);
    }
    
    public static List<Evento> buscarUltimosTresEventos() throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectLastThreeEventos();
    }
    
    public static Evento buscar(int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventoById(id);
    }
    
    public static Evento buscarPorDadosEvento(Evento evento) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectEventoByEventoData(evento);
    }
    
    public static int buscarTotal() throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectCountEventos();
    }
    
    public static int buscarTotalComFiltros(String nomeEvento, int cidade, Date data) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectCountEventos(nomeEvento, cidade, data);
    }
    
    public static int buscarTotalPorIdUsuario(int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        return eventodao.selectCountEventos(id);
    }
    
    public static void remover(int id) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.deleteEventoById(id);
    }
    
    public static void alterar(Evento evento) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.updateEventoById(evento);
    }
    
    public static void alterarSemImagem(Evento evento) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.updateEventoByIdWithoutImagem(evento);
    }
    
    public static void inserir(Evento evento) throws ClassNotFoundException, SQLException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.insertEvento(evento);
    }
    
    public static List<Evento> selectEventos(int pagina) throws SQLException, ClassNotFoundException {		
        EventoDAO comp = new EventoDAO();		
        return comp.selectEventos(pagina);		
    }		
    public static void suspender(int id) throws SQLException, ClassNotFoundException {		
        EventoDAO comp = new EventoDAO();		
        comp.suspendEventoById(id);		
    }		
    public static void ativar(int id) throws SQLException, ClassNotFoundException {		
        EventoDAO comp = new EventoDAO();		
        comp.activeEventoById(id);		
    }		
    public static void deletar(int id) throws SQLException, ClassNotFoundException {		
        EventoDAO comp = new EventoDAO();		
        comp.deleteEventoById(id);		
    }
}
