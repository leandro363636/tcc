/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Administrador;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Gabriel
 */
public class AdministradorDAO {
    private Connection conn;

    public AdministradorDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public String selectNameById (int id) throws SQLException {
        String sql = "SELECT nome_administrador "
                + "FROM tb_administrador "
                + "WHERE id_administrador = (?);";
        
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        String nome = "";
        
        while (rs.next()) {
            nome = rs.getString("nome_administrador");
        }
        this.conn.close();
        return nome;
    }
    
    public int selectIdByData(Administrador administrador) throws SQLException {
        String sql = "SELECT id_administrador from tb_administrador WHERE nome_administrador=(?) AND sobrenome_administrador=(?) AND rg_administrador=(?) AND cpf_administrador=(?) AND data_nascimento_administrador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, administrador.getNome());
        stmt.setString(2, administrador.getSobrenome());
        stmt.setString(3, administrador.getRg());
        stmt.setString(4, administrador.getCpf());
        stmt.setDate(5, new java.sql.Date(administrador.getDataNascimento().getTime()));

        ResultSet res = stmt.executeQuery();
        int id = 0;
        while (res.next()) {
            id = res.getInt("id_administrador");
        }
        return id;
    }

    public Administrador selectAdministradorById(int id) throws SQLException {
        String sql = "SELECT * from tb_administrador "
                + "INNER JOIN tb_usuario ON id_administrador = id_referencia AND tipo_usuario = 'a'"
                + "WHERE id_administrador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        Administrador administrador = new Administrador();

        while (res.next()) {
            administrador.setIdAdministrador(res.getInt("id_administrador"));
            administrador.setId(res.getInt("id_usuario"));
            administrador.setEmail(res.getString("email_usuario"));
            administrador.setSenha(res.getString("senha_usuario"));
            administrador.setAtivo(res.getBoolean("ativo_usuario"));
            administrador.setNome(res.getString("nome_administrador"));
            administrador.setSobrenome(res.getString("sobrenome_administrador"));
            administrador.setCpf(res.getString("cpf_administrador"));
            administrador.setRg(res.getString("rg_administrador"));
            administrador.setDataNascimento(res.getDate("data_nascimento_administrador"));
            this.conn.close();
            return administrador;
        }
        this.conn.close();
        return null;
    }

    public List<Administrador> selectAdministradores() throws SQLException {;;

        List<Administrador> resultados = new ArrayList<>();

        String sql = "SELECT * FROM tb_administrador "
                + "INNER JOIN tb_usuario ON id_administrador = id_referencia AND tipo_usuario = 'a'";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet res = st.executeQuery();

        while (res.next()) {
            Administrador administrador = new Administrador();
            administrador.setIdAdministrador(res.getInt("id_administrador"));
            administrador.setId(res.getInt("id_usuario"));
            administrador.setEmail(res.getString("email_usuario"));
            administrador.setSenha(res.getString("senha_usuario"));
            administrador.setAtivo(res.getBoolean("ativo_usuario"));
            administrador.setNome(res.getString("nome_administrador"));
            administrador.setSobrenome(res.getString("sobrenome_administrador"));
            administrador.setCpf(res.getString("cpf_administrador"));
            administrador.setRg(res.getString("rg_administrador"));
            administrador.setDataNascimento(res.getDate("data_nascimento_administrador"));
            resultados.add(administrador);
        }
        this.conn.close();
        return resultados;
    }

    public void insertAdministrador(Administrador administrador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "INSERT INTO tb_administrador (nome_administrador, sobrenome_administrador, cpf_administrador, rg_administrador, data_nascimento_administrador) VALUES ((?), (?), (?), (?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(administrador.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        String senha = hexString.toString();

        st.setString(1, administrador.getNome());
        st.setString(2, administrador.getSobrenome());
        st.setString(3, administrador.getCpf());
        st.setString(4, administrador.getRg());
        st.setDate(5, new java.sql.Date(administrador.getDataNascimento().getTime()));
        st.executeUpdate();

        administrador.setIdAdministrador(selectIdByData(administrador));

        sql = "INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ((?), (?), (?), 'a')";
        st = conn.prepareStatement(sql);

        st.setString(1, administrador.getEmail());
        st.setString(2, senha);
        st.setInt(3, administrador.getIdAdministrador());
        st.executeUpdate();
        this.conn.close();
    }

    public void suspendAdministradorById(int id) throws SQLException {
        String sql = "UPDATE tb_usuario SET ativo_usuario = FALSE where id_referencia=(?) AND tipo_usuario = 'a';";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        this.conn.close();
    }

    public void activeAdministradorById(int id) throws SQLException {
        String sql = "UPDATE tb_usuario SET ativo_usuario = TRUE where id_referencia=(?) AND tipo_usuario = 'a';";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        this.conn.close();
    }

    public void updateAdministradorById(Administrador administrador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_administrador SET nome_administrador=(?), sobrenome_administrador=(?), cpf_administrador=(?), rg_administrador=(?), data_nascimento_administrador=(?) where id_administrador=(?);";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(administrador.getSenha().getBytes("UTF-8"));
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
        stmt.setString(1, administrador.getNome());
        stmt.setString(2, administrador.getSobrenome());
        stmt.setString(3, administrador.getCpf());
        stmt.setString(4, administrador.getRg());
        stmt.setDate(5, new java.sql.Date(administrador.getDataNascimento().getTime()));
        stmt.setInt(6, administrador.getIdAdministrador());
        stmt.executeUpdate();

        sql = " UPDATE tb_usuario SET email_usuario=(?), senha_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'c';";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, administrador.getEmail());
        stmt.setString(2, senha);
        stmt.setInt(3, administrador.getId());
        stmt.executeUpdate();
        this.conn.close();
    }

    public void updateAdministradorByIdWithoutSenha(Administrador administrador) throws SQLException {
        String sql = "UPDATE tb_administrador SET nome_administrador=(?), sobrenome_administrador=(?), cpf_administrador=(?), rg_administrador=(?), data_nascimento_administrador=(?) where id_administrador=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, administrador.getNome());
        stmt.setString(2, administrador.getSobrenome());
        stmt.setString(3, administrador.getCpf());
        stmt.setString(4, administrador.getRg());
        stmt.setDate(5, new java.sql.Date(administrador.getDataNascimento().getTime()));
        stmt.setInt(6, administrador.getIdAdministrador());
        stmt.executeUpdate();

        sql = " UPDATE tb_usuario SET email_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'c';";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, administrador.getEmail());
        stmt.setInt(2, administrador.getId());
        stmt.executeUpdate();
        this.conn.close();
    }

    public void updateSenhaById(Administrador administrador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET senha_usuario=(?) where id_referencia=(?) AND tipo_usuario = 'c';";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(administrador.getSenha().getBytes("UTF-8"));
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
        stmt.setInt(2, administrador.getId());
        stmt.executeUpdate();
        this.conn.close();
    }
}
