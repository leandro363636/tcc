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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                + "FROM tb_usuario "
                + "WHERE id = (?) "
                + "ORDER BY id DESC;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        List<Admin> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Admin adm = new Admin();
            
            adm.setId(rs.getInt("id_usuario"));
            adm.setEmail(rs.getString("email_usuario"));
            adm.setSenha(rs.getString("senha_usuario"));
            adm.setNome(rs.getString("nome_usuario"));
            adm.setTipo(rs.getString("tipo"));
           
            resultado.add(adm);
        }
        return resultado;
    }
        
            
    public List<Admin> getUsuarios() throws SQLException {

        List<Admin> resultados = new ArrayList<>();

        String sql = "SELECT * FROM tb_usuario";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Admin adm = new Admin();
            adm.setId(rs.getInt("id_usuario"));
            adm.setEmail(rs.getString("email_usuario"));
            adm.setSenha(rs.getString("senha_usuario"));
            adm.setNome(rs.getString("nome_usuario"));
            adm.setSobrenome(rs.getString("sobrenome_usuario"));
            resultados.add(adm);
        }
        return resultados;
    }
    

          public void updateUsuarioById(Admin adm) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET email_usuario=(?),  senha_usuario=(?), nome_usuario=(?), sobrenome_usuario=(?), rg_usuario=(?), cpf_usuario=(?), datanascimento=(?), tipo =(?), endereco=(?) where id_usuario=(?);";

        /*StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(adm.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }*/

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, adm.getEmail());
        stmt.setString(2, adm.getSenha());
        stmt.setString(3, adm.getNome());
        stmt.setString(4, adm.getSobrenome());
        stmt.setString(5, adm.getRg());
        stmt.setString(6, adm.getCpf());
        stmt.setDate(7, new java.sql.Date(adm.getDataNascimento().getTime()));
        stmt.setString(8, adm.getTipo());
        stmt.setString(9, adm.getEndereco());
        stmt.setInt(10, adm.getId());
        stmt.executeUpdate();
    }
    
        
        public void updateUsuarioByIdWithoutSenha(Admin adm) throws SQLException {
        String sql = "UPDATE tb_usuario SET nome_usuario=(?), sobrenome_usuario=(?), email_usuario=(?) where id_usuario=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, adm.getNome());
        stmt.setString(2, adm.getSobrenome());
        stmt.setString(3, adm.getEmail());
        stmt.setInt(4, adm.getId());
        stmt.executeUpdate();
    }
    
    
        
        
      public Admin getUsuario(String email) throws SQLException {
        String sql = "SELECT * from tb_usuario where email_usuario=(?) LIMIT 1;";
		
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,email);

        ResultSet res = stmt.executeQuery();
        Admin adm = new Admin();

        while (res.next())
        { 
            adm.setId(res.getInt(1));
            adm.setEmail(res.getString(2));
            adm.setSenha(res.getString(3));
            adm.setNome(res.getString(4));
            adm.setSobrenome(res.getString(5));
            adm.setCpf(res.getString(6));
            return adm;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
        
      
          
    public Admin getUsuarioById(int id) throws SQLException {
        String sql = "SELECT * from tb_usuario where id_usuario=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        Admin  adm = new Admin();

        while (res.next()) {
            adm.setId(res.getInt(1));
            adm.setEmail(res.getString(2));
            adm.setSenha(res.getString(3));
            adm.setNome(res.getString(4));
            adm.setSobrenome(res.getString(5));
            return adm;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
    
    
      public Admin getBuscarDadosId(int id) throws SQLException {
        String sql = "SELECT * from tb_usuario where id_usuario=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        Admin  adm = new Admin();

        while (res.next()) {
            adm.setId(res.getInt(1));
            adm.setEmail(res.getString(2));
            adm.setSenha(res.getString(3));
            adm.setNome(res.getString(4));
            adm.setSobrenome(res.getString(5));
            adm.setRg(res.getString(6));
            adm.setCpf(res.getString(7));
            adm.setDataNascimento(res.getDate(8));
            adm.setTipo(res.getString(9));
            adm.setEndereco(res.getString(10));
            return adm;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
}
