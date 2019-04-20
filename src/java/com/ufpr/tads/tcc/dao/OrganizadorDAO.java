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
    
        public void insertOrganizador (Organizador organizador) throws SQLException {
        String sql = "INSERT INTO organizador (cnpj, nomeDaOrganizacao, nomeDoResponsavel, rgResponsavel, email, senha, endereco, tipo) VALUES ((?), (?), (?), (?), (?), (?), (?), (?))";
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
        st.setString(1, organizador.getCnpj());
        st.setString(2, organizador.getNomeDaOrganizacao());
        st.setString(3, organizador.getNomeDoResponsavel());
        st.setString(4, organizador.getRgDoResponsavel());
        st.setString(5, organizador.getEmail());
        st.setString(6, organizador.getSenha());
        st.setString(7, organizador.getEndereco());
        st.setInt(8, organizador.getTipo());
        st.executeUpdate();
    }
      
        
     public Organizador selectOrganizadorById(int idOrganizador) throws SQLException {
       
        String sql = "SELECT * "
                + "FROM organizador "
                + "WHERE idOrganizador = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, idOrganizador);
        ResultSet rs = st.executeQuery();
        Organizador org = new Organizador();
        
        while (rs.next()) {
            org.setIdOrganizador(rs.getInt("idOrganizador"));
            org.setNomeDaOrganizacao(rs.getString("nomeDaOrganizacao"));
            org.setNomeDoResponsavel(rs.getString("nomeDoResponsavel"));
            org.setRgDoResponsavel(rs.getString("rgDoResponsavel"));
            org.setEmail(rs.getString("email"));
            org.setSenha(rs.getString("senha"));
            org.setEndereco(rs.getString("endereco"));
            org.setTipo(rs.getInt("tipo"));
        }
        return org;
    }
     
     
        public List<Organizador> selectOrganizadorByIdAdm(int idOrganizador) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM organizador "
                + "WHERE idOrganizador = (?) "
                + "ORDER BY idOrganizador DESC;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, idOrganizador);
        ResultSet rs = st.executeQuery();
        List<Organizador> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Organizador org = new Organizador();
            
            org.setIdOrganizador(rs.getInt("idOrganizador"));
            org.setNomeDaOrganizacao(rs.getString("nomeDaOrganizacao"));
            org.setNomeDoResponsavel(rs.getString("nomeDoResponsavel"));
            org.setRgDoResponsavel(rs.getString("rgDoResponsavel"));
            org.setEndereco(rs.getString("endereco"));
           
            resultado.add(org);
        }
        return resultado;
    }
        
            
    public List<Organizador> getOrganizador() throws SQLException {

        List<Organizador> resultados = new ArrayList<>();

        String sql = "SELECT * FROM organizador";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Organizador org = new Organizador();
            org.setIdOrganizador(rs.getInt("idOrganizador"));
            org.setNomeDaOrganizacao(rs.getString("nomeDaOrganizacao"));
            org.setNomeDoResponsavel(rs.getString("nomeDoResponsavel"));
            org.setRgDoResponsavel(rs.getString("rgDoResponsavel"));
            org.setEmail(rs.getString("email"));
            org.setSenha(rs.getString("senha"));
            org.setEndereco(rs.getString("endereco"));
            org.setTipo(rs.getInt("tipo"));
            resultados.add(org);
        }
        return resultados;
    }
    

public void updateOrganizadorById(Organizador org) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET cnpj=(?), nomeDaOrganizacao=(?), nomeDoResponsavel=(?), rgResponsavel=(?), email=(?), senha=(?), endereco=(?), tipo=(?) where idOrganizador=(?);";

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
        stmt.setString(1, org.getNomeDaOrganizacao());
        stmt.setString(2, org.getNomeDoResponsavel());
        stmt.setString(3, org.getRgDoResponsavel());
        stmt.setString(4, org.getEmail());
        stmt.setString(5, org.getSenha());
        stmt.setString(6, org.getEndereco());
        stmt.setInt(7, org.getTipo());
        stmt.setInt(8, org.getIdOrganizador());
        stmt.executeUpdate();
    }
    
        
        public void updateOrganizadorByIdWithoutSenha(Organizador org) throws SQLException {
        String sql = "UPDATE tb_usuario SET nomeDaOrganizacao=(?), nomeDoResponsavel=(?), email=(?) where idOrganizador=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, org.getNomeDaOrganizacao());
        stmt.setString(2, org.getNomeDoResponsavel());
        stmt.setString(4, org.getEmail());
        stmt.setInt(4, org.getIdOrganizador());
        stmt.executeUpdate();
    }
    
    
        
        
      public Organizador getOrganizadorEmail(String email) throws SQLException {
        String sql = "SELECT * from organizador where email=(?) LIMIT 1;";
		
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,email);

        ResultSet res = stmt.executeQuery();
        Organizador org = new Organizador();

        while (res.next())
        { 
            
            org.setIdOrganizador(res.getInt(1));
            org.setEmail(res.getString(2));
            org.setSenha(res.getString(3));
            org.setNomeDaOrganizacao(res.getString(4));            
            org.setNomeDoResponsavel(res.getString(5));
            org.setRgDoResponsavel(res.getString(6));
            return org;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
        
      
          
    public Organizador getOrganizadorById(int idOrganizador) throws SQLException {
        String sql = "SELECT * from organizador where idOrganizador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idOrganizador);

        ResultSet res = stmt.executeQuery();
        Organizador  org = new Organizador();

        while (res.next()) {
            org.setIdOrganizador(res.getInt(1));
            org.setEmail(res.getString(2));
            org.setSenha(res.getString(3));
            org.setNomeDaOrganizacao(res.getString(4));
            org.setNomeDoResponsavel(res.getString(5));
            return org;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
    
    
      public Organizador getBuscarDadosId(int idOrganizador) throws SQLException {
        String sql = "SELECT * from organizador where idOrganizador=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idOrganizador);

        ResultSet res = stmt.executeQuery();
        Organizador  org = new Organizador();

        while (res.next()) {
            org.setIdOrganizador(res.getInt(1));
            org.setCnpj(res.getString(2));
            org.setNomeDaOrganizacao(res.getString(3));
            org.setNomeDoResponsavel(res.getString(4));
            org.setRgDoResponsavel(res.getString(5));
            org.setEmail(res.getString(6));
            org.setSenha(res.getString(7));
            org.setEndereco(res.getString(8));
            org.setTipo(res.getInt(1));
            return org;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
}
