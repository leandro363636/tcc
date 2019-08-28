/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.dao.CompradorDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mateus
 */
public class CompradorFacade {
    
    public static List<Comprador> buscarTodosCompradores() throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        return comp.selectCompradores();
    }

    public static Comprador buscar(int id) throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        return comp.selectCompradorById(id);
    }

    public static String buscarNomePorId(int id) throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        return comp.selectNameById(id);
    }

    public static int buscarIdPorDadosComprador(Comprador comprador) throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        return comp.selectIdByData(comprador);
    }
    
    public static int buscarTotal() throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        return comp.selectCountCompradores();
    }
    
        
    public static List<Comprador> buscarTodosCompradores(int pagina) throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        return comp.selectCompradores(pagina);
    }

    public static void inserir(Comprador comprador) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        CompradorDAO comp = new CompradorDAO();
        comp.insertComprador(comprador);
    }
    
    public static void inserirSocial(Comprador comprador) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        CompradorDAO comp = new CompradorDAO();
        comp.insertSocial(comprador);
    }

    public static void alterar(Comprador comprador) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        CompradorDAO comp = new CompradorDAO();
        comp.updateCompradorById(comprador);
    }

    public static void alterarSemSenha(Comprador comprador) throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        comp.updateCompradorByIdWithoutSenha(comprador);
    }
    
    public static void alterarSenha(Comprador comprador) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
        CompradorDAO comp = new CompradorDAO();
        comp.updateSenhaById(comprador);
    }

    public static void suspender(int id) throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        comp.suspendCompradorById(id);
    }

    public static void ativar(int id) throws SQLException, ClassNotFoundException {
        CompradorDAO comp = new CompradorDAO();
        comp.activeCompradorById(id);
    }
    
    public static void deletar(int id) throws SQLException, ClassNotFoundException {		
        CompradorDAO comp = new CompradorDAO();		
        comp.deleteCompradorById(id);		
    }
}
