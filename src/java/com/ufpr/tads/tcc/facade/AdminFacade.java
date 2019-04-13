package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Admin;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.dao.AdminDAO;
import com.ufpr.tads.tcc.dao.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    
     public static List<Admin> buscarTodosUsuarios() throws SQLException, ClassNotFoundException {
        AdminDAO adm = new AdminDAO();
        return adm.getUsuarios();
    }
    
    public static Admin buscar(int id) throws SQLException, ClassNotFoundException {
        AdminDAO adm = new AdminDAO();
        return adm.getUsuarioById(id);
    }
    
    
     public static Admin buscarDados(int id) throws SQLException, ClassNotFoundException {
        AdminDAO adm = new AdminDAO();
        return adm.getBuscarDadosId(id);
    }
     
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
    
        public static void alterar(Admin adms) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        AdminDAO admin = new AdminDAO();
        admin.updateUsuarioById(adms);
    }
    
    public static void alterarSemSenha(Admin adm) throws SQLException, ClassNotFoundException {
        AdminDAO admin = new AdminDAO();
        admin.updateUsuarioByIdWithoutSenha(adm);
    }
    
    public static Admin buscarAdminByEmail(String email) throws SQLException, ClassNotFoundException {
        AdminDAO admin = new AdminDAO();
        return admin.getUsuario(email);
    }
}
