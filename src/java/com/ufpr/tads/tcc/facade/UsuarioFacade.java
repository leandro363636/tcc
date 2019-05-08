/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.dao.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mateus
 */
public class UsuarioFacade {
    /*public static List<Comprador> buscarTodosUsuarios() throws SQLException, ClassNotFoundException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        return usuariodao.getUsuarios();
    }
    
    public static Comprador buscar(int id) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        return usuariodao.getUsuarioById(id);
    }*/
    
    public static Usuario buscarUsuarioByEmail(String email) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        return usuariodao.selectUsuarioByEmail(email);
    }
    
    /*public static void remover(int id) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuariodao.removeUsuarioById(id);
    }
    
    public static void alterar(Comprador usuario) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuariodao.updateUsuarioById(usuario);
    }
    
    public static void alterarSemSenha(Comprador usuario) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuariodao.updateUsuarioByIdWithoutSenha(usuario);
    }
    
        public static void alterarSenha(Comprador usuario) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuariodao.updateSenhaById(usuario);
    }*/
    
    
    /*public static void inserir(Comprador usuario) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuariodao.insertUsuario(usuario);
    }*/
}
