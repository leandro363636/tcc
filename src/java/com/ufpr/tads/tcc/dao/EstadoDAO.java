/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateus
 */
public class EstadoDAO {
    private final Connection conn;

    public EstadoDAO() throws SQLException, ClassNotFoundException {
        this.conn = (Connection) new ConnectionFactory().getConnection();
    }

    public List<Estado> getEstados() throws SQLException {
        ResultSet rs;
        List<Estado> estados = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String query = "select * from tb_estado";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id_estado");
            String nome = rs.getString("nome_estado");
            String sigla = rs.getString("sigla_estado");
            Estado estado = new Estado();
            estado.setId(id);
            estado.setNome(nome);
            estado.setSigla(sigla);
            estados.add(estado);
        }
        return estados;
    }

    public Estado getEstadoById(int idestado) throws SQLException {
        String sql = "SELECT * FROM tb_estado e WHERE AND id_estado=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idestado);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            int id = res.getInt("id_estado");
            String nomeest = res.getString("nome_estado");
            String sigla = res.getString("sigla_estado");
            Estado estado = new Estado();
            estado.setId(idestado);
            estado.setNome(nomeest);
            estado.setSigla(sigla);
            return estado;
        }
        return null;
    }
}
