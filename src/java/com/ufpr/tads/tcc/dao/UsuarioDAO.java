package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ronaldo
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }

    public Usuario selectUsuarioEsp(String login, String senha) throws SQLException {

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
    }

}
