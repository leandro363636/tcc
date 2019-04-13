/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Admin;
import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Usuario;
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
public class AdminDAO {
    private Connection conn;

    public AdminDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
        public void insertAdmin (Admin admin) throws SQLException {
        String sql = "INSERT INTO tb_usuario (email_usuario, senha_usuario, nome_usuario, sobrenome_usuario, rg_usuario, cpf_usuario, datanascimento, tipo, endereco) VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);
        //try {
      /*  md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(admin.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }*/
        /*} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            request.setAttribute("javax.servlet.jsp.jspException", ex);
            request.setAttribute("javax.servlet.error.status_code", 500);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }*/
        
        st.setString(1, admin.getEmail());
        st.setString(2, admin.getSenha());
        st.setString(3, admin.getNome());
        st.setString(4, admin.getSobrenome());
        st.setString(5, admin.getRg());
        st.setString(6, admin.getCpf());
        st.setDate(7, new java.sql.Date(admin.getDataNascimento().getTime()));
        st.setString(8, admin.getTipo());
        st.setString(9, admin.getEndereco());
        st.executeUpdate();
    }
      
        
     public Admin selectAdminById(int id) throws SQLException {
       
        String sql = "SELECT * "
                + "FROM tb_usuario "
                + "WHERE id = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Admin adm = new Admin();
        
        while (rs.next()) {
            adm.setId(rs.getInt("id_usuario"));
            adm.setEmail(rs.getString("email_usuario"));
            adm.setSenha(rs.getString("senha_usuario"));
            adm.setNome(rs.getString("nome_usuario"));
            adm.setSobrenome(rs.getString("sobrenome_usuario"));
            adm.setRg(rs.getString("rg_usuario"));
            adm.setCpf(rs.getString("cpf_usuario"));
            adm.setDataNascimento(rs.getDate("dataNascimento"));
            adm.setTipo(rs.getString("tipo"));
            adm.setEndereco(rs.getString("endereco_usuario"));
        }
        return adm;
    }
     
     
        public List<Admin> selectAdminByIdAdm(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM admin "
                + "WHERE id = (?) "
                + "ORDER BY id DESC;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        List<Admin> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Admin adm = new Admin();
            
            adm.setId(rs.getInt("id"));
            adm.setEmail(rs.getString("email"));
            adm.setSenha(rs.getString("senha"));
            adm.setNome(rs.getString("nome"));
            adm.setTipo(rs.getString("tipo"));
           
            resultado.add(adm);
        }
        return resultado;
    }
        
}
