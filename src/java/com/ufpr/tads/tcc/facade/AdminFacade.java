package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Admin;
import com.ufpr.tads.tcc.dao.AdminDAO;
import java.sql.SQLException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
public class AdminFacade {
     /*  public static void alterar(Evento evento) throws SQLException, ClassNotFoundException {
        EventoDAO eventodao = new EventoDAO();
        eventodao.updateEventoById(evento);
    }*/
    
    public static Admin selectAdminById(int id) throws SQLException, ClassNotFoundException {
        AdminDAO adms = new AdminDAO();
        return adms.selectAdminById(id);
    }
    
        
    public static List<Admin> buscarListaAdmin(int id) throws SQLException, ClassNotFoundException {
        AdminDAO adm = new AdminDAO();
        return adm.selectAdminByIdAdm(id);
    }
    
    public static void inserir(Admin adm) throws ClassNotFoundException, SQLException {
        AdminDAO adms = new AdminDAO();
        adms.insertAdmin(adm);    
 }
}
