/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Comprador;
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
 * @author mateus
 */
public class CompradorDAO {

    private Connection conn;

    public CompradorDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }

    public String selectNameById(int id) throws SQLException {
        String sql = "SELECT nome_comprador "
                + "FROM tb_comprador "
                + "WHERE id_comprador = (?);";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        String nome = "";

        while (rs.next()) {
            nome = rs.getString("nome_comprador");
        }
        return nome;
    }

    public int selectIdByData(Comprador comprador) throws SQLException {
        String sql = "SELECT id_comprador from tb_comprador WHERE nome_comprador=(?) AND sobrenome_comprador=(?) AND rg_comprador=(?) AND cpf_comprador=(?) AND data_nascimento_comprador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, comprador.getNome());
        stmt.setString(2, comprador.getSobrenome());
        stmt.setString(3, comprador.getRg());
        stmt.setString(4, comprador.getCpf());
        stmt.setDate(5, new java.sql.Date(comprador.getDataNascimento().getTime()));

        ResultSet res = stmt.executeQuery();
        int id = 0;
        while (res.next()) {
            id = res.getInt("id_comprador");
        }
        return id;
    }
    
    public int selectIdByDataSocial(Comprador comprador) throws SQLException {
        String sql = "SELECT id_comprador from tb_comprador WHERE nome_comprador=(?) AND sobrenome_comprador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, comprador.getNome());
        stmt.setString(2, comprador.getSobrenome());

        ResultSet res = stmt.executeQuery();
        int id = 0;
        while (res.next()) {
            id = res.getInt("id_comprador");
        }
        return id;
    }

    public Comprador selectCompradorById(int id) throws SQLException {
        String sql = "SELECT * from tb_comprador "
                + "INNER JOIN tb_usuario ON id_comprador = id_referencia AND tipo_usuario = 'c'"
                + "WHERE id_comprador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        Comprador comprador = new Comprador();

        while (res.next()) {
            comprador.setIdComprador(res.getInt("id_comprador"));
            comprador.setId(res.getInt("id_usuario"));
            comprador.setEmail(res.getString("email_usuario"));
            comprador.setSenha(res.getString("senha_usuario"));
            comprador.setNome(res.getString("nome_comprador"));
            comprador.setSobrenome(res.getString("sobrenome_comprador"));
            comprador.setCpf(res.getString("cpf_comprador"));
            comprador.setRg(res.getString("rg_comprador"));
            comprador.setDataNascimento(res.getDate("data_nascimento_comprador"));
            return comprador;
        }

        return null;
    }

    public List<Comprador> selectCompradores() throws SQLException {;;

        List<Comprador> resultados = new ArrayList<>();

        String sql = "SELECT * FROM tb_comprador "
                + "INNER JOIN tb_usuario ON id_comprador = id_referencia AND tipo_usuario = 'c'";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet res = st.executeQuery();

        while (res.next()) {
            Comprador comprador = new Comprador();
            comprador.setIdComprador(res.getInt("id_comprador"));
            comprador.setId(res.getInt("id_usuario"));
            comprador.setEmail(res.getString("email_usuario"));
            comprador.setSenha(res.getString("senha_usuario"));
            comprador.setAtivo(res.getBoolean("ativo_usuario"));
            comprador.setNome(res.getString("nome_comprador"));
            comprador.setSobrenome(res.getString("sobrenome_comprador"));
            comprador.setCpf(res.getString("cpf_comprador"));
            comprador.setRg(res.getString("rg_comprador"));
            comprador.setDataNascimento(res.getDate("data_nascimento_comprador"));
            resultados.add(comprador);
        }
        return resultados;
    }

    public void insertComprador(Comprador comprador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "INSERT INTO tb_comprador (nome_comprador, sobrenome_comprador, cpf_comprador, rg_comprador, data_nascimento_comprador) VALUES ((?), (?), (?), (?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(comprador.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        String senha = hexString.toString();

        st.setString(1, comprador.getNome());
        st.setString(2, comprador.getSobrenome());
        st.setString(3, comprador.getCpf());
        st.setString(4, comprador.getRg());
        st.setDate(5, new java.sql.Date(comprador.getDataNascimento().getTime()));
        st.executeUpdate();

        comprador.setIdComprador(selectIdByData(comprador));

        sql = "INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ((?), (?), (?), 'c')";
        st = conn.prepareStatement(sql);

        st.setString(1, comprador.getEmail());
        st.setString(2, senha);
        st.setInt(3, comprador.getIdComprador());
        st.executeUpdate();
    }
    
    public void insertSocial(Comprador comprador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "INSERT INTO tb_comprador (nome_comprador, sobrenome_comprador) VALUES ((?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);

        st.setString(1, comprador.getNome());
        st.setString(2, comprador.getSobrenome());
        st.executeUpdate();

        comprador.setIdComprador(selectIdByDataSocial(comprador));

        sql = "INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ((?), (?), (?), 'c')";
        st = conn.prepareStatement(sql);

        st.setString(1, comprador.getEmail());
        st.setString(2, comprador.getSenha());
        st.setInt(3, comprador.getIdComprador());
        st.executeUpdate();
    }

    public void suspendCompradorById(int id) throws SQLException {
        String sql = "UPDATE tb_usuario SET ativo_usuario = FALSE where id_referencia=(?) AND tipo_usuario = 'c';";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void activeCompradorById(int id) throws SQLException {
        String sql = "UPDATE tb_usuario SET ativo_usuario = TRUE where id_referencia=(?) AND tipo_usuario = 'c';";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void updateCompradorById(Comprador comprador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_comprador SET nome_comprador=(?), sobrenome_comprador=(?), cpf_comprador=(?), rg_comprador=(?), data_nascimento_comprador=(?) where id_comprador=(?);";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(comprador.getSenha().getBytes("UTF-8"));
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
        stmt.setString(1, comprador.getNome());
        stmt.setString(2, comprador.getSobrenome());
        stmt.setString(3, comprador.getCpf());
        stmt.setString(4, comprador.getRg());
        stmt.setDate(5, new java.sql.Date(comprador.getDataNascimento().getTime()));
        stmt.setInt(6, comprador.getIdComprador());
        stmt.executeUpdate();

        sql = " UPDATE tb_usuario SET email_usuario=(?), senha_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'c';";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, comprador.getEmail());
        stmt.setString(2, senha);
        stmt.setInt(3, comprador.getId());
        stmt.executeUpdate();
    }

    public void updateCompradorByIdWithoutSenha(Comprador comprador) throws SQLException {
        String sql = "UPDATE tb_comprador SET nome_comprador=(?), sobrenome_comprador=(?), cpf_comprador=(?), rg_comprador=(?), data_nascimento_comprador=(?) where id_comprador=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, comprador.getNome());
        stmt.setString(2, comprador.getSobrenome());
        stmt.setString(3, comprador.getCpf());
        stmt.setString(4, comprador.getRg());
        stmt.setDate(5, new java.sql.Date(comprador.getDataNascimento().getTime()));
        stmt.setInt(6, comprador.getIdComprador());
        stmt.executeUpdate();

        sql = " UPDATE tb_usuario SET email_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'c';";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, comprador.getEmail());
        stmt.setInt(2, comprador.getId());
        stmt.executeUpdate();
    }

    public void updateSenhaById(Comprador comprador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET senha_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'c';";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(comprador.getSenha().getBytes("UTF-8"));
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
        stmt.setInt(2, comprador.getId());
        stmt.executeUpdate();
    }
}
