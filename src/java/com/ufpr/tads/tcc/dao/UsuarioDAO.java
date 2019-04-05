package com.ufpr.tads.tcc.dao;
//
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

    /*public Usuario selectUsuarioEsp(String login, String senha) throws SQLException {

        String sql = "SELECT * FROM tb_usuario WHERE login_usuario = (?) AND senha_usuario = (?) LIMIT 1;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, login);
        st.setString(2, senha);

        ResultSet rs = st.executeQuery();
        Usuario usuario = new Usuario();

        while (rs.next()) {
            usuario.setId(rs.getInt(1));
            usuario.setEmail(rs.getString(2));
            usuario.setSenha(rs.getString(3));
            usuario.setNome(rs.getString(4));
            return usuario;
        }
        return null;
    }

//    public List<Usuario> selectUsuario() throws SQLException {;;
//
//        List<Usuario> resultados = new ArrayList<>();
//
//        String sql = "SELECT * FROM tb_usuario";
//        PreparedStatement st = conn.prepareStatement(sql);
//
//        ResultSet rs = st.executeQuery();
//
//        while (rs.next()) {
//            Usuario usuario = new Usuario();
//            usuario.setId(rs.getInt("id_usuario"));
//            usuario.setLogin(rs.getString("login_usuario"));
//            usuario.setSenha(rs.getString("senha_usuario"));
//            usuario.setNome(rs.getString("nome_usuario"));
//            resultados.add(usuario);
//        }
//        return resultados;
//    }

    public void insertUsuario(Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

        String sql = "INSERT INTO tb_usuario (nome_usuario, sobrenome_usuario, email_usuario, senha_usuario) VALUES ((?), (?), (?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(usuario.getSenha().getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senha = hexString.toString();

        st.setString(1, usuario.getSobrenome());
        st.setString(2, usuario.getNome());
        st.setString(3, usuario.getEmail());
        st.setString(4, senha);
        st.executeUpdate();
    }*/
    
    public Usuario getUsuario(String email) throws SQLException {
        String sql = "SELECT * from tb_usuario where email_usuario=(?) LIMIT 1;";
		
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,email);

        ResultSet res = stmt.executeQuery();
        Usuario usuario = new Usuario();

        while (res.next())
        { 
            usuario.setId(res.getInt(1));
            usuario.setEmail(res.getString(2));
            usuario.setSenha(res.getString(3));
            usuario.setNome(res.getString(4));
            usuario.setSobrenome(res.getString(5));
            return usuario;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
    
    public Usuario getUsuarioById(int id) throws SQLException {
        String sql = "SELECT * from tb_usuario where id_usuario=(?) LIMIT 1;";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        Usuario usuario = new Usuario();

        while (res.next()) {
            usuario.setId(res.getInt(1));
            usuario.setEmail(res.getString(2));
            usuario.setSenha(res.getString(3));
            usuario.setNome(res.getString(4));
            usuario.setSobrenome(res.getString(5));
            return usuario;
        }
        //System.out.println("Executed: "+ usuario.toString());

        return null;
    }
    
    public List<Usuario> getUsuarios() throws SQLException {;;

        List<Usuario> resultados = new ArrayList<>();

        String sql = "SELECT * FROM tb_usuario";
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setEmail(rs.getString("email_usuario"));
            usuario.setSenha(rs.getString("senha_usuario"));
            usuario.setNome(rs.getString("nome_usuario"));
            usuario.setSobrenome(rs.getString("sobrenome_usuario"));
            resultados.add(usuario);
        }
        return resultados;
    }
    
    public void insertUsuario (Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "INSERT INTO tb_usuario (nome_usuario, sobrenome_usuario, email_usuario, senha_usuario, rg_usuario, cpf_usuario) VALUES ((?), (?), (?), (?), (?), (?))";
        PreparedStatement st = conn.prepareStatement(sql);

        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        //try {
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
        /*} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            request.setAttribute("javax.servlet.jsp.jspException", ex);
            request.setAttribute("javax.servlet.error.status_code", 500);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }*/
        
        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getSobrenome());
        st.setString(3, usuario.getEmail());
        st.setString(4, hexString.toString());
        st.setString(5, usuario.getRg());
        st.setString(6, usuario.getCpf());
        st.executeUpdate();
    }
    
    public void removeUsuarioById(int id) throws SQLException {
        String sql = "DELETE FROM tb_usuario where id_usuario=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.executeUpdate();
   }
    
    public void updateUsuarioById(Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String sql = "UPDATE tb_usuario SET nome_usuario=(?), sobrenome_usuario=(?), email_usuario=(?), senha_usuario=(?) where id_usuario=(?);";

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
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getSobrenome());
        stmt.setString(3, usuario.getEmail());
        stmt.setString(4, hexString.toString());
        stmt.setInt(5, usuario.getId());
        stmt.executeUpdate();
    }
    
    public void updateUsuarioByIdWithoutSenha(Usuario usuario) throws SQLException {
        String sql = "UPDATE tb_usuario SET nome_usuario=(?), sobrenome_usuario=(?), email_usuario=(?) where id_usuario=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getSobrenome());
        stmt.setString(3, usuario.getEmail());
        stmt.setInt(4, usuario.getId());
        stmt.executeUpdate();
    }

}
