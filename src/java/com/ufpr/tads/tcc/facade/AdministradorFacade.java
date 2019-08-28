package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Administrador;
import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.dao.AdministradorDAO;
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
public class AdministradorFacade {

    public static List<Administrador> buscarTodosAdministradores() throws SQLException, ClassNotFoundException {
        AdministradorDAO adm = new AdministradorDAO();
        return adm.selectAdministradores();
    }

    public static Administrador buscar(int id) throws SQLException, ClassNotFoundException {
        AdministradorDAO adm = new AdministradorDAO();
        return adm.selectAdministradorById(id);
    }

    public static String buscarNomePorId(int id) throws SQLException, ClassNotFoundException {
        AdministradorDAO adm = new AdministradorDAO();
        return adm.selectNameById(id);
    }

    public static int buscarIdPorDadosAdministrador(Administrador admin) throws SQLException, ClassNotFoundException {
        AdministradorDAO adm = new AdministradorDAO();
        return adm.selectIdByData(admin);
    }

    public static void inserir(Administrador adm) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        AdministradorDAO adms = new AdministradorDAO();
        adms.insertAdministrador(adm);
    }

    public static void alterar(Administrador adms) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        AdministradorDAO admin = new AdministradorDAO();
        admin.updateAdministradorById(adms);
    }

    public static void alterarSemSenha(Administrador adm) throws SQLException, ClassNotFoundException {
        AdministradorDAO admin = new AdministradorDAO();
        admin.updateAdministradorByIdWithoutSenha(adm);
    }

    public static void suspender(int id) throws SQLException, ClassNotFoundException {
        AdministradorDAO admin = new AdministradorDAO();
        admin.suspendAdministradorById(id);
    }

    public static void ativar(int id) throws SQLException, ClassNotFoundException {
        AdministradorDAO admin = new AdministradorDAO();
        admin.activeAdministradorById(id);
    }
}
