/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;
//

import com.ufpr.tads.tcc.beans.Ingresso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ronaldo
 */
public class IngressoDAO {
    private Connection conn;

    public IngressoDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public void insertIngresso(Ingresso ingresso) throws SQLException {

        String sql = "INSERT INTO tb_ingresso "
                + "(serial_ingresso, acesso_ingresso, id_lote, id_usuario) "
                + " VALUES ((?), (?), (?), (?));";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setString(1, ingresso.getSerial());
        st.setBoolean(2, ingresso.isAcesso());
        st.setInt(3, ingresso.getLote().getId());
        st.setDouble(4, ingresso.getUsuario().getId());
        
        st.executeUpdate();
        this.conn.close();
    }
    
    public boolean selectIngressoBySerialAndIdEvento(String serial, int id) throws SQLException {
        
        String sql = "SELECT i.* "
                + " FROM tb_ingresso i"
                + " INNER JOIN tb_lote l ON l.id_lote = i.id_lote"
                + " INNER JOIN tb_evento e ON e.id_evento = l.id_evento"
                + " WHERE i.serial_ingresso = (?) AND e.id_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, serial);
        st.setInt(2, id);
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            this.conn.close();
            return true;
        }
        this.conn.close();
        return false;
    }
    
    public int selectIdIngressoBySerialAndIdEvento(String serial, int id) throws SQLException {
        
        String sql = "SELECT i.* "
                + " FROM tb_ingresso i"
                + " INNER JOIN tb_lote l ON l.id_lote = i.id_lote"
                + " INNER JOIN tb_evento e ON e.id_evento = l.id_evento"
                + " WHERE i.serial_ingresso = (?) AND e.id_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, serial);
        st.setInt(2, id);
        ResultSet rs = st.executeQuery();
        int idIngresso = 0;
        while (rs.next()) {
            idIngresso = rs.getInt("id_ingresso");
        }
        this.conn.close();
        return idIngresso;
    }
    
    public void accessIngresso(int id) throws SQLException {
        String sql = "UPDATE tb_ingresso SET acesso_ingresso = TRUE where id_ingresso=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        this.conn.close();
    }
}
