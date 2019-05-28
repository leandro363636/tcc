package com.ufpr.tads.tcc.dao;
//
import com.ufpr.tads.tcc.beans.Comprador;
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
 * @author Ronaldo
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public Usuario selectUsuarioByEmail(String email) throws SQLException {
        String sql = "SELECT * from tb_usuario where email_usuario=(?) AND ativo_usuario = TRUE;";
		
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,email);

        ResultSet res = stmt.executeQuery();
        Usuario usuario = new Usuario();

        while (res.next())
        { 
            usuario.setId(res.getInt("id_usuario"));
            usuario.setEmail(res.getString("email_usuario"));
            usuario.setSenha(res.getString("senha_usuario"));
            usuario.setTipo(res.getString("tipo_usuario"));
            usuario.setIdReferencia(res.getInt("id_referencia"));
            return usuario;
        }
        return null;
    }
    
    public Usuario selectUsuarioById(int id) throws SQLException {
        String sql = "SELECT * from tb_usuario where id_usuario=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        Usuario usuario = new Usuario();

        while (res.next()) {
            usuario.setId(res.getInt("id_usuario"));
            usuario.setEmail(res.getString("email_usuario"));
            usuario.setSenha(res.getString("senha_usuario"));
            usuario.setTipo(res.getString("tipo_usuario"));
            usuario.setIdReferencia(res.getInt("id_referencia"));
            return usuario;
        }
        return null;
    }
    
    /*public void insertUsuario (Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "INSERT INTO tb_usuario (email_usuario, senha_usuario, tipo_usuario) VALUES ((?), (?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(usuario.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        String senha = hexString.toString();
        
        st.setString(1, usuario.getEmail());
        st.setString(2, senha);
        st.setString(3, usuario.getTipo());
        st.executeUpdate();
    }
    
    public void removeUsuarioById(int id) throws SQLException {
        String sql = "DELETE FROM tb_usuario where id_usuario=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.executeUpdate();
   }
   
   public void removeUsuarioByIdRefenciaAndTipo(int id, String tipo) throws SQLException {
        String sql = "DELETE FROM tb_usuario where id_referencia=(?) AND tipo_usuario=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.setString(2, tipo);
        stmt.executeUpdate();
   }
    
    public void updateUsuarioById(Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET email_usuario=(?), senha_usuario=(?) where id_usuario=(?);";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(usuario.getSenha().getBytes("UTF-8"));
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
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, senha);
        stmt.setInt(3, usuario.getId());
        stmt.executeUpdate();
    }
    
    public void updateUsuarioByIdWithoutSenha(Usuario usuario) throws SQLException {
        String sql = "UPDATE tb_usuario SET email_usuario=(?) where id_usuario=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getEmail());
        stmt.setInt(2, usuario.getId());
        stmt.executeUpdate();
    }*/
    
    
        public void updateSenhaById(Comprador usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET senha_usuario=(?) where id_usuario=(?);";

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(usuario.getSenha().getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0"
                        + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, hexString.toString());
        stmt.setInt(2, usuario.getId());
        stmt.executeUpdate();
    }

}
