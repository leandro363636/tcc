/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Cidade;
import com.ufpr.tads.tcc.beans.Endereço;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matri
 */
public class EndereçoDAO {
    private Connection conn;

    public EndereçoDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public void insertEndereço(Endereço endereço) throws SQLException {

        String sql = "INSERT INTO tb_endereco "
                + "(rua_endereco, numero_endereco, cep_endereco, id_cidade, referencia_endereco, id_referencia) "
                + "VALUES ((?), (?), (?), (?), (?), (?));";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setString(1, endereço.getRua());
        st.setInt(2, endereço.getNumero());
        st.setString(3, endereço.getCep());
        st.setInt(4, endereço.getCidade().getId());
        st.setString(5, endereço.getReferencia());
        st.setInt(6, endereço.getIdReferencia());
        
        st.executeUpdate();
    }
    
    public void updateEndereçoById(Endereço endereço) throws SQLException {
        
        String sql = "UPDATE tb_endereco "
                + "SET rua_endereco = (?), numero_endereco = (?), cep_endereco = (?), id_cidade = (?), referencia_endereco = (?), id_referencia = (?) "
                + "WHERE id_endereco = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, endereço.getRua());
        st.setInt(2, endereço.getNumero());
        st.setString(3, endereço.getCep());
        st.setInt(4, endereço.getCidade().getId());
        st.setString(5, endereço.getReferencia());
        st.setInt(6, endereço.getIdReferencia());
        st.setInt(7, endereço.getId());
        
        st.executeUpdate();
    }
    
    public void updateEndereçoByIdRefencia(Endereço endereço) throws SQLException {
        
        String sql = "UPDATE tb_endereco "
                + "SET rua_endereco = (?), numero_endereco = (?), cep_endereco = (?), id_cidade = (?) "
                + "WHERE id_referencia = (?) AND referencia_endereco = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, endereço.getRua());
        st.setInt(2, endereço.getNumero());
        st.setString(3, endereço.getCep());
        st.setInt(4, endereço.getCidade().getId());
        st.setInt(5, endereço.getIdReferencia());
        st.setString(6, endereço.getReferencia());
        
        st.executeUpdate();
    }
    
    public void deleteEndereçoById(int id) throws SQLException {
        
        String sql = "DELETE FROM tb_endereco "
                + "WHERE id_endereco = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setInt(1, id);
        
        st.executeUpdate();
    }
    
    public Endereço selectEndereçoById(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_endereco "
                + "WHERE id_endereco = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Endereço endereço = new Endereço();
        
        while (rs.next()) {
            endereço.setId(rs.getInt("id_endereco"));
            endereço.setRua(rs.getString("rua_endereco"));
            endereço.setNumero(rs.getInt("numero_endereco"));
            endereço.setCep(rs.getString("cep_endereco"));
            Cidade cidade = new Cidade();
            cidade.setId(rs.getInt("id_cidade"));
            endereço.setCidade(cidade);
            endereço.setReferencia(rs.getString("referencia_endereco"));
            endereço.setIdReferencia(rs.getInt("id_referencia"));
        }
        return endereço;
    }
    
    public Endereço selectEndereçoByIdReferenciaAndReferencia(int id, String referencia) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_endereco "
                + "WHERE id_referencia = (?) AND referencia_endereco = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        st.setString(2, referencia);
        ResultSet rs = st.executeQuery();
        Endereço endereço = new Endereço();
        
        while (rs.next()) {
            endereço.setId(rs.getInt("id_endereco"));
            endereço.setRua(rs.getString("rua_endereco"));
            endereço.setNumero(rs.getInt("numero_endereco"));
            endereço.setCep(rs.getString("cep_endereco"));
            Cidade cidade = new Cidade();
            cidade.setId(rs.getInt("id_cidade"));
            endereço.setCidade(cidade);
            endereço.setReferencia(rs.getString("referencia_endereco"));
            endereço.setIdReferencia(rs.getInt("id_referencia"));
        }
        return endereço;
    }
}
