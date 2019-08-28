/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mateus
 */
public class RelatorioDAO {
    private final Connection conn;
    
    public RelatorioDAO() throws SQLException, ClassNotFoundException {
        this.conn = (Connection) new ConnectionFactory().getConnection();
    }

    public int getVendasTotal(int idUsuario) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        int total = 0;
        String sql = "SELECT COUNT(c.*) AS total FROM tb_compra c" +
            " INNER JOIN tb_lote_compra lc ON lc.id_compra = c.id_compra" +
            " INNER JOIN tb_lote l ON l.id_lote = lc.id_lote" +
            " INNER JOIN tb_evento e ON e.id_evento = l.id_evento" +
            " WHERE lc.id_lote IN (SELECT l1.id_lote FROM tb_lote l1" +
            " INNER JOIN tb_evento e1 ON e1.id_evento = l1.id_evento" +
            " WHERE e1.id_usuario = (?)) AND c.status_compra = 'finalizado'" +
            " ;";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        rs = stmt.executeQuery();
        while (rs.next()) {
            //++total;
            total = rs.getInt("total");
        }
        this.conn.close();
        return total;
    }
    
    public double getVendasMedia(int idUsuario) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        int totalEventos = 0;
        String sql = "SELECT COUNT(*) AS total FROM tb_evento e" +
            " WHERE e.id_usuario = (?);";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        rs = stmt.executeQuery();
        while (rs.next()) {
            totalEventos = rs.getInt("total");
        }
        int totalVendas = getVendasTotal(idUsuario);
        
        double media = totalVendas / (double) totalEventos;  
        return media;
    }

    public ArrayList<HashMap<String, String>> getVendasEventos(int idUsuario) throws SQLException {
        ResultSet rs;
        ArrayList<HashMap<String, String>> lista = new ArrayList<>();;
        String sql = "SELECT e.id_evento, e.nome_evento, count(*) AS total FROM tb_compra c" +
            " INNER JOIN tb_lote_compra lc ON lc.id_compra = c.id_compra" +
            " INNER JOIN tb_lote l ON l.id_lote = lc.id_lote" +
            " INNER JOIN tb_evento e ON e.id_evento = l.id_evento" +
            " WHERE lc.id_lote IN (SELECT l1.id_lote FROM tb_lote l1" +
            " INNER JOIN tb_evento e1 ON e1.id_evento = l1.id_evento" +
            " WHERE e1.id_usuario = (?)) AND c.status_compra = 'finalizado'" +
            " GROUP BY e.id_evento" +
            " ORDER BY e.data_fim_evento ASC;";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        rs = stmt.executeQuery();
        while (rs.next()) {
            HashMap<String, String> hm = new HashMap<>();
            String nomeEvento = rs.getString("nome_evento");
            int total = rs.getInt("total");
            hm.put("total", Integer.toString(total));
            hm.put("nome", nomeEvento);
            lista.add(hm);
        }
        this.conn.close();
        return lista;
    }
    
    public int getAcessosTotal(int idUsuario) throws SQLException, ClassNotFoundException {
        return 0;
    }
    
    public double getAcessoMedio(int idUsuario) {
        return 0;
    }

    public ArrayList<HashMap<String, String>> getAcessosEventos(int idUsuario) {
        return new ArrayList();
    }
}
