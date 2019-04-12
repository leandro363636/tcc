/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.facade;

import com.ufpr.tads.tcc.beans.Endereço;
import com.ufpr.tads.tcc.dao.EndereçoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author matri
 */
public class EndereçoFacade {
    
    public static Endereço buscar(int id) throws SQLException, ClassNotFoundException {
        EndereçoDAO endereçodao = new EndereçoDAO();
        return endereçodao.selectEndereçoById(id);
    }
    
    public static Endereço buscarPorReferencia(int id, String referencia) throws SQLException, ClassNotFoundException {
        EndereçoDAO endereçodao = new EndereçoDAO();
        return endereçodao.selectEndereçoByIdReferenciaAndReferencia(id, referencia);
    }
    
    public static void remover(int id) throws SQLException, ClassNotFoundException {
        EndereçoDAO endereçodao = new EndereçoDAO();
        endereçodao.deleteEndereçoById(id);
    }
    
    public static void alterar(Endereço endereço) throws SQLException, ClassNotFoundException {
        EndereçoDAO endereçodao = new EndereçoDAO();
        endereçodao.updateEndereçoById(endereço);
    }
    
    public static void alterarPorIdReferencia(Endereço endereço) throws SQLException, ClassNotFoundException {
        EndereçoDAO endereçodao = new EndereçoDAO();
        endereçodao.updateEndereçoByIdRefencia(endereço);
    }
    
    public static void inserir(Endereço endereço) throws ClassNotFoundException, SQLException {
        EndereçoDAO endereçodao = new EndereçoDAO();
        endereçodao.insertEndereço(endereço);
    }
}
