/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Lote;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateus
 */
public class LoteDAO {
    private Connection conn;

    public LoteDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public void insertLote(Lote lote) throws SQLException {

        String sql = "INSERT INTO tb_lote "
                + "(nome_lote, quantidade_lote, preco_lote, id_evento) "
                + "VALUES ((?), (?), (?), (?));";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setString(1, lote.getNome());
        st.setInt(2, lote.getQuantidade());
        st.setDouble(3, lote.getPreço());
        st.setInt(4, lote.getEvento().getId());
        
        st.executeUpdate();
    }
    
    public void updateLoteById(Lote lote) throws SQLException {
        
        String sql = "UPDATE tb_lote "
                + "SET nome_lote = (?), quantidade_lote = (?), preco_lote = (?), id_evento = (?)"
                + "WHERE id_lote = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, lote.getNome());
        st.setInt(2, lote.getQuantidade());
        st.setDouble(3, lote.getPreço());
        st.setInt(4, lote.getEvento().getId());
        st.setInt(5, lote.getId());
        
        st.executeUpdate();
    }
    
    public void deleteLoteById(int id) throws SQLException {
        
        String sql = "DELETE FROM tb_lote "
                + "WHERE id_lote = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setInt(1, id);
        
        st.executeUpdate();
    }
    
    /*public List<Evento> selectEventos() throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "ORDER BY id_evento;";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("id_evento"));
            evento.setDataInicio(rs.getDate("inicio_evento"));
            evento.setDataFim(rs.getDate("fim_evento"));
            evento.setEndereco(rs.getString("endereco_evento"));
            evento.setDesc(rs.getString("desc_evento"));
            evento.setAprovado(rs.getBoolean("aprovado"));
            
            resultado.add(evento);
        }
        return resultado;
    }*/
    
    public List<Lote> selectLotesByIdEvento(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_lote "
                + "WHERE id_evento = (?) "
                + "ORDER BY id_lote DESC;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        List<Lote> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Lote lote = new Lote();
            lote.setId(rs.getInt("id_lote"));
            lote.setNome(rs.getString("nome_lote"));
            lote.setQuantidade(rs.getInt("quantidade_lote"));
            lote.setPreço(rs.getDouble("preco_lote"));
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            lote.setEvento(evento);
            
            resultado.add(lote);
        }
        return resultado;
    }
    
    public Lote selectLoteById(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_lote "
                + "WHERE id_lote = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Lote lote = new Lote();
        
        while (rs.next()) {
            lote.setId(rs.getInt("id_lote"));
            lote.setNome(rs.getString("nome_lote"));
            lote.setQuantidade(rs.getInt("quantidade_lote"));
            lote.setPreço(rs.getDouble("preco_lote"));
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            lote.setEvento(evento);
        }
        return lote;
    }
}
