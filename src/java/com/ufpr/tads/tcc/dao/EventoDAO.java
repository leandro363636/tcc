/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;
//
import com.ufpr.tads.tcc.beans.Evento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ronaldo
 */
public class EventoDAO {

    private Connection conn;

    public EventoDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public void insertEvento(Evento evento) throws SQLException {

        String sql = "INSERT INTO tb_evento "
                + "(nome_evento, data_inicio_evento, data_fim_evento, descricao_evento, imagem_evento, aprovacao_evento, id_usuario) "
                + "VALUES ((?), (?), (?), (?), (?), (?), (?));";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setString(1, evento.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(evento.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(evento.getDataFim().getTime()));
        st.setString(4, evento.getDescrição());
        st.setString(5, evento.getImagem());
        st.setBoolean(6, evento.isAprovado());
        st.setInt(7, evento.getUsuario().getId());
        
        st.executeUpdate();
    }
    
    public void updateEventoById(Evento evento) throws SQLException {
        
        String sql = "UPDATE tb_evento "
                + "SET nome_evento = (?), data_inicio_evento = (?), data_fim_evento = (?), descricao_evento = (?), imagem_evento = (?), aprovacao_evento = (?) "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, evento.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(evento.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(evento.getDataFim().getTime()));
        st.setString(4, evento.getDescrição());
        st.setString(5, evento.getImagem());
        st.setBoolean(6, evento.isAprovado());
        st.setInt(7, evento.getId());
        
        st.executeUpdate();
    }
    
    public void updateEventoByIdWithoutImagem(Evento evento) throws SQLException {
        
        String sql = "UPDATE tb_evento "
                + "SET nome_evento = (?), data_inicio_evento = (?), data_fim_evento = (?), descricao_evento = (?), aprovacao_evento = (?) "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, evento.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(evento.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(evento.getDataFim().getTime()));
        st.setString(4, evento.getDescrição());
        st.setBoolean(5, evento.isAprovado());
        st.setInt(6, evento.getId());
        
        st.executeUpdate();
    }
    
    public void deleteEventoById(int id) throws SQLException {
        
        String sql = "DELETE FROM tb_evento "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setInt(1, id);
        
        st.executeUpdate();
    }
    
    public List<Evento> selectEventos() throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "ORDER BY data_fim_evento;";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        return resultado;
    }
    
    public List<Evento> selectEventosByIdUsuario(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE id_usuario = (?) "
                + "ORDER BY data_fim_evento DESC;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        return resultado;
    }
    
    public Evento selectEventoById(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Evento evento = new Evento();
        
        while (rs.next()) {
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
        }
        return evento;
    }
    
    public Evento selectEventoByEventoData(Evento ev) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE nome_evento = (?) AND data_inicio_evento = (?) AND data_fim_evento = (?) AND descricao_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, ev.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(ev.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(ev.getDataFim().getTime()));
        st.setString(4, ev.getDescrição());
        ResultSet rs = st.executeQuery();
        Evento evento = new Evento();
        
        while (rs.next()) {
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
        }
        return evento;
    }
    
    public List<Evento> selectLastThreeEventos() throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "ORDER BY data_fim_evento DESC "
                + "LIMIT 3;";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        return resultado;
    }
}
