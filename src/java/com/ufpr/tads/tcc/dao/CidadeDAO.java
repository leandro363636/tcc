/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Cidade;
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
public class CidadeDAO {
    private final Connection conn;

    public CidadeDAO() throws SQLException, ClassNotFoundException {
        this.conn = (Connection) new ConnectionFactory().getConnection();
    }

    public List<Cidade> getCidades() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        List<Cidade> cidades = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String query = "select c.id_cidade, c.nome_cidade, e.id_estado, e.nome_estado, e.sigla_estado from tb_cidade c, tb_estado e where c.id_estado = e.id_estado";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id_cidade");
            String nomecid = rs.getString("nome_cidade");
            int idestado = rs.getInt("id_estado");
            String nomeest = rs.getString("nome_estado");
            String sigla = rs.getString("sigla_estado");
            Cidade cidade = new Cidade();
            cidade.setId(id);
            cidade.setNome(nomecid);
            Estado estado = new Estado();
            estado.setId(idestado);
            estado.setNome(nomeest);
            estado.setSigla(sigla);
            cidade.setEstado(estado);
            cidades.add(cidade);
        }
        return cidades;
    }
    
    public List<Cidade> getCidadesByIdEstado(int idestado) throws SQLException {
        String sql = "SELECT c.id_cidade, c.nome_cidade, e.id_estado, e.nome_estado, e.sigla_estado FROM tb_cidade c, tb_estado e WHERE c.id_estado = e.id_estado AND c.id_estado=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idestado);

        ResultSet res = stmt.executeQuery();
        List<Cidade> cidades = new ArrayList<>();

        while (res.next()) {
            int id = res.getInt("id_cidade");
            String nomecid = res.getString("nome_cidade");
            idestado = res.getInt("id_estado");
            String nomeest = res.getString("nome_estado");
            String sigla = res.getString("sigla_estado");
            Cidade cidade = new Cidade();
            cidade.setId(id);
            cidade.setNome(nomecid);
            cidades.add(cidade);
        }
        return cidades;
    }
    
    
    public Cidade getCidadeById(int idcidade) throws SQLException {
        String sql = "SELECT c.id_cidade, c.nome_cidade, e.id_estado, e.nome_estado, e.sigla_estado FROM tb_cidade c, tb_estado e WHERE c.id_estado = e.id_estado AND c.id_cidade=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idcidade);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            int id = res.getInt("id_cidade");
            String nomecid = res.getString("nome_cidade");
            int idestado = res.getInt("id_estado");
            String nomeest = res.getString("nome_estado");
            String sigla = res.getString("sigla_estado");
            Cidade cidade = new Cidade();
            cidade.setId(id);
            cidade.setNome(nomecid);
            Estado estado = new Estado();
            estado.setId(idestado);
            estado.setNome(nomeest);
            estado.setSigla(sigla);
            cidade.setEstado(estado);
            return cidade;
        }
        return null;
    }
}
