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
    
    public static List<Organizador> buscarTodosOrganizadores() throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.selectOrganizadores();
    }
    
    public static Organizador buscar(int id) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.selectOrganizadorById(id);
    }
    
    public static String buscarNomePorId(int id) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.selectNameById(id);
    }
    
    public static int buscarIdPorDadosOrganizador(Organizador org) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        return orgs.selectIdByData(org);
    }
    
    public static void inserir(Organizador org) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
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
    
    public static void suspender(int id) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        orgs.suspendOrganizadorById(id);
    }
    
    public static void ativar(int id) throws SQLException, ClassNotFoundException {
        OrganizadorDAO orgs = new OrganizadorDAO();
        orgs.activeOrganizdorById(id);
    }
}
