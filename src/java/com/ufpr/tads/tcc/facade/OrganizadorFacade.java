/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Organizador;
import com.ufpr.tads.tcc.dao.OrganizadorDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class OrganizadorFacade {
        
    
    public static List<Organizador> getOrganizador() throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.getOrganizador();
     }
   
    public static Organizador buscar(int idOrganizador) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.getBuscarDadosId(idOrganizador);
    }
    /*
    
     public static Admin buscarDados(int id) throws SQLException, ClassNotFoundException {
        AdminDAO adm = new AdminDAO();
        return adm.getBuscarDadosId(id);
    }
     */
    public static Organizador selectOrganizadorById(int idOrganizador) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.selectOrganizadorById(idOrganizador);
    }
            
    public static List<Organizador> getOrganizador(int idOrganizador) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.selectOrganizadorByIdAdm(idOrganizador);
    }
    
    public static void insertOrganizador(Organizador org) throws ClassNotFoundException, SQLException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        orgs.insertOrganizador(org);    
 }
    
        public static void alterar(Organizador org) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        orgs.updateOrganizadorById(org);
    }
    
    public static void alterarSemSenha(Organizador org) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        orgs.updateOrganizadorByIdWithoutSenha(org);
    }
    
    public static Organizador getOrganizadorEmail(String email) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.getOrganizadorEmail(email);
    }
}
