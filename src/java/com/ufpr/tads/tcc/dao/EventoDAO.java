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
                + "(nome_evento, inicio_evento, fim_evento, data, endereco_evento, desc_evento, aprovado) "
                + "VALUES ((?), (?), (?), (?), (?), (?), (?));";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setString(1, evento.getNome());
        st.setDate(2, new Date(evento.getDataInicio().getTime()));
        st.setDate(3, new Date(evento.getDataFim().getTime()));
        st.setDate(4, new Date(evento.getData().getTime()));
        st.setString(5, evento.getEndereco());
        st.setString(6, evento.getDesc());
        st.setBoolean(7, evento.isAprovado());
        
        st.executeUpdate();
    }
    
    public void updateEventoById(Evento evento) throws SQLException {
        
        String sql = "UPDATE tb_evento "
                + "SET nome_evento = (?), inicio_evento = (?), fim_evento = (?), data_evento = (?), endereco_evento = (?), desc_evento = (?), aprovado = (?) "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, evento.getNome());
        st.setDate(2, new Date(evento.getDataInicio().getTime()));
        st.setDate(3, new Date(evento.getDataFim().getTime()));
        st.setDate(4, new Date(evento.getData().getTime()));
        st.setString(5, evento.getEndereco());
        st.setString(6, evento.getDesc());
        st.setBoolean(7, evento.isAprovado());
        
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
            evento.setData(rs.getDate("data_evento"));
            evento.setEndereco(rs.getString("endereco_evento"));
            evento.setDesc(rs.getString("desc_evento"));
            evento.setAprovado(rs.getBoolean("aprovado"));
            
            resultado.add(evento);
        }
        return resultado;
    }
    
    public List<Evento> selectEventosByIdUsuario(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE id_usuario = (?) "
                + "ORDER BY id_evento DESC;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("id_evento"));
            evento.setDataInicio(rs.getDate("inicio_evento"));
            evento.setDataFim(rs.getDate("fim_evento"));
            evento.setData(rs.getDate("data_evento"));
            evento.setEndereco(rs.getString("endereco_evento"));
            evento.setDesc(rs.getString("desc_evento"));
            evento.setAprovado(rs.getBoolean("aprovado"));
            
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
            evento.setId(rs.getInt(1));
            evento.setNome(rs.getString(2));
            evento.setDataInicio(rs.getDate(3));
            evento.setDataFim(rs.getDate(4));
            evento.setData(rs.getDate(5));
            evento.setEndereco(rs.getString(6));
            evento.setDesc(rs.getString(7));
            evento.setAprovado(rs.getBoolean(8));
        }
        return evento;
    }
}
