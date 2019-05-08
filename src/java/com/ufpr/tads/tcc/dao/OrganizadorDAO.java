/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
BANCO DE DADOS

CREATE TABLE organizador(
idOrganizador serial PRIMARY KEY,
cnpj varchar(11),
nomeDaOrganizacao varchar(100),
nomeDoResponsavel varchar(100),
rgResponsavel varchar(11),
email varchar(500),
senha varchar(20),
endereco varchar(200),
tipo int);
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Organizador;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class OrganizadorDAO {

    private Connection conn;

    public OrganizadorDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public String selectNameById (int id) throws SQLException {
        String sql = "SELECT nome_responsavel_organizador "
                + "FROM tb_organizador "
                + "WHERE id_organizador = (?);";
        
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        String nome = "";
        
        while (rs.next()) {
            nome = rs.getString("nome_responsavel_organizador");
        }
        return nome;
    }
    
    public int selectIdByData(Organizador organizador) throws SQLException {
        String sql = "SELECT id_organizador from tb_organizador WHERE nome_organizador=(?) AND nome_responsavel_organizador=(?) AND sobrenome_responsavel_organizador=(?) AND rg_responsavel_organizador=(?) AND cnpj_organizador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, organizador.getNomeOrganizador());
        stmt.setString(2, organizador.getNomeResponsavel());
        stmt.setString(3, organizador.getSobrenome());
        stmt.setString(4, organizador.getRg());
        stmt.setString(5, organizador.getCnpj());

        ResultSet res = stmt.executeQuery();
        int id = 0;
        while (res.next()) {
            id = res.getInt("id_organizador");
        }
        return id;
    }

    public Organizador selectOrganizadorById(int id) throws SQLException {
        String sql = "SELECT * from tb_organizador "
                + "INNER JOIN tb_usuario ON id_organizador = id_referencia AND tipo_usuario = 'o'"
                + "WHERE id_organizador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        Organizador organizador = new Organizador();

        while (res.next()) {
            organizador.setIdOrganizador(res.getInt("id_organizador"));
            organizador.setId(res.getInt("id_organizador"));
            organizador.setEmail(res.getString("email_usuario"));
            organizador.setSenha(res.getString("senha_usuario"));
            organizador.setAtivo(res.getBoolean("ativo_usuario"));
            organizador.setNomeOrganizador(res.getString("nome_organizador"));
            organizador.setNomeResponsavel(res.getString("nome_responsavel_organizador"));
            organizador.setSobrenome(res.getString("sobrenome_responsavel_organizador"));
            organizador.setCnpj(res.getString("cnpj_organizador"));
            organizador.setRg(res.getString("rg_responsavel_organizador"));
            return organizador;
        }

        return null;
    }

    public List<Organizador> selectOrganizadores() throws SQLException {;;

        List<Organizador> resultados = new ArrayList<>();

        String sql = "SELECT * FROM tb_organizador "
                + "INNER JOIN tb_usuario ON id_organizador = id_referencia AND tipo_usuario = 'o'";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet res = st.executeQuery();

        while (res.next()) {
            Organizador organizador = new Organizador();
            organizador.setIdOrganizador(res.getInt("id_organizador"));
            organizador.setId(res.getInt("id_organizador"));
            organizador.setEmail(res.getString("email_usuario"));
            organizador.setSenha(res.getString("senha_usuario"));
            organizador.setAtivo(res.getBoolean("ativo_usuario"));
            organizador.setNomeOrganizador(res.getString("nome_organizador"));
            organizador.setNomeResponsavel(res.getString("nome_responsavel_organizador"));
            organizador.setSobrenome(res.getString("sobrenome_responsavel_organizador"));
            organizador.setCnpj(res.getString("cnpj_organizador"));
            organizador.setRg(res.getString("rg_responsavel_organizador"));
            resultados.add(organizador);
        }
        return resultados;
    }

    public void insertOrganizador(Organizador organizador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "INSERT INTO tb_organizador (nome_organizador, nome_responsavel_organizador, sobrenome_responsavel_organizador, cnpj_organizador, rg_responsavel_organizador) VALUES ((?), (?), (?), (?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(organizador.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        String senha = hexString.toString();

        st.setString(1, organizador.getNomeOrganizador());
        st.setString(2, organizador.getNomeResponsavel());
        st.setString(3, organizador.getSobrenome());
        st.setString(4, organizador.getCnpj());
        st.setString(5, organizador.getRg());
        st.executeUpdate();

        organizador.setIdOrganizador(selectIdByData(organizador));

        sql = "INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ((?), (?), (?), 'o')";
        st = conn.prepareStatement(sql);

        st.setString(1, organizador.getEmail());
        st.setString(2, senha);
        st.setInt(3, organizador.getIdOrganizador());
        st.executeUpdate();
    }

    public void suspendOrganizadorById(int id) throws SQLException {
        String sql = "UPDATE tb_usuario SET ativo_usuario = FALSE where id_referencia=(?) AND tipo_usuario = 'o';";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void activeOrganizdorById(int id) throws SQLException {
        String sql = "UPDATE tb_usuario SET ativo_usuario = TRUE where id_referencia=(?) AND tipo_usuario = 'o';";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void updateOrganizadorById(Organizador organizador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_organizador SET nome_organizador=(?), nome_responsavel_organizador=(?), sobrenome_responsavel_organizador=(?), cnpj_organizador=(?), rg_responsavel_organizador=(?) where id_organizador=(?);";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(organizador.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        String senha = hexString.toString();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, organizador.getNomeOrganizador());
        stmt.setString(2, organizador.getNomeResponsavel());
        stmt.setString(3, organizador.getSobrenome());
        stmt.setString(4, organizador.getCnpj());
        stmt.setString(5, organizador.getRg());
        stmt.setInt(6, organizador.getIdOrganizador());
        stmt.executeUpdate();

        sql = " UPDATE tb_usuario SET email_usuario=(?), senha_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'o';";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, organizador.getEmail());
        stmt.setString(2, senha);
        stmt.setInt(3, organizador.getIdOrganizador());
        stmt.executeUpdate();
    }

    public void updateOrganizadorByIdWithoutSenha(Organizador organizador) throws SQLException {
        String sql = "UPDATE tb_organizador SET nome_organizador=(?), nome_responsavel_organizador=(?), sobrenome_responsavel_organizador=(?), cnpj_organizador=(?), rg_responsavel_organizador=(?) where id_organizador=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, organizador.getNomeOrganizador());
        stmt.setString(2, organizador.getNomeResponsavel());
        stmt.setString(3, organizador.getSobrenome());
        stmt.setString(4, organizador.getCnpj());
        stmt.setString(5, organizador.getRg());
        stmt.setInt(6, organizador.getIdOrganizador());
        stmt.executeUpdate();

        sql = " UPDATE tb_usuario SET email_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'o';";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, organizador.getEmail());
        stmt.setInt(2, organizador.getIdOrganizador());
        stmt.executeUpdate();
    }

    public void updateSenhaById(Organizador organizador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET senha_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'o';";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(organizador.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        String senha = hexString.toString();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, senha);
        stmt.setInt(2, organizador.getId());
        stmt.executeUpdate();
    }
}
