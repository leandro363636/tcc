/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;
//
import com.ufpr.tads.tcc.beans.Evento;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ronaldo
 */
public class EventoDAO {

    private Connection conn;

    public EventoDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public void insertEvento(Evento evento) throws SQLException {

        String sql = "INSERT INTO tb_evento "
                + "(nome_evento, data_inicio_evento, data_fim_evento, descricao_evento, imagem_evento, aprovacao_evento, id_usuario) "
                + "VALUES ((?), (?), (?), (?), (?), (?), (?));";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setString(1, evento.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(evento.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(evento.getDataFim().getTime()));
        st.setString(4, evento.getDescrição());
        st.setString(5, evento.getImagem());
        st.setBoolean(6, evento.isAprovado());
        st.setInt(7, evento.getUsuario().getId());
        
        st.executeUpdate();
        this.conn.close();
    }
    
    public void updateEventoById(Evento evento) throws SQLException {
        
        String sql = "UPDATE tb_evento "
                + "SET nome_evento = (?), data_inicio_evento = (?), data_fim_evento = (?), descricao_evento = (?), imagem_evento = (?), aprovacao_evento = (?) "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, evento.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(evento.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(evento.getDataFim().getTime()));
        st.setString(4, evento.getDescrição());
        st.setString(5, evento.getImagem());
        st.setBoolean(6, evento.isAprovado());
        st.setInt(7, evento.getId());
        
        st.executeUpdate();
        this.conn.close();
    }
    
    public void updateEventoByIdWithoutImagem(Evento evento) throws SQLException {
        
        String sql = "UPDATE tb_evento "
                + "SET nome_evento = (?), data_inicio_evento = (?), data_fim_evento = (?), descricao_evento = (?), aprovacao_evento = (?) "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareCall(sql);
        
        st.setString(1, evento.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(evento.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(evento.getDataFim().getTime()));
        st.setString(4, evento.getDescrição());
        st.setBoolean(5, evento.isAprovado());
        st.setInt(6, evento.getId());
        
        st.executeUpdate();
        this.conn.close();
    }
    
    public void deleteEventoById(int id) throws SQLException {
        
        String sql = "DELETE FROM tb_evento "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setInt(1, id);
        
        st.executeUpdate();
        this.conn.close();
    }
    
    public List<Evento> selectEventos(int pagina) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "ORDER BY data_fim_evento, nome_evento, id_evento "
                + "LIMIT 9 OFFSET (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        int start = (pagina - 1) * 9; 
        st.setInt(1, start);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        this.conn.close();
        return resultado;
    }
       public List<Evento> selectEventosIdPag(int pagina, int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE id_usuario = (?) "
                + "ORDER BY data_fim_evento "
                + "LIMIT 9 OFFSET (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        int start = (pagina - 1) * 9;
        st.setInt(1, id);
        st.setInt(2, start);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        this.conn.close();
        return resultado;
    }
       
       
              public List<Evento> selectEventosIdPagAprovado(int pagina, int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE id_usuario = (?) AND aprovacao_evento = true "
                + "ORDER BY data_fim_evento "
                + "LIMIT 9 OFFSET (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        int start = (pagina - 1) * 9;
        st.setInt(1, id);
        st.setInt(2, start);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        this.conn.close();
        return resultado;
    }
              
    public List<Evento> selectEventosWithFilters(int pagina, String nomeEvento, int cidade, Date data) throws SQLException {
        String where = "";
        if ( (nomeEvento != null && !nomeEvento.equals("")) ) {
            where += "WHERE e.nome_evento LIKE (?) ";
        }
        
        if ( cidade != 0 ) {
            if ( where.equals("") ) {
                where += "WHERE c.id_cidade = (?) ";
            } else {
                where += "AND c.id_cidade = (?) ";
            }
        }
        
        if ( data != null ) {
            if ( where.equals("") ) {
                where += "WHERE e.data_inicio_evento <= (?) AND e.data_fim_evento >= (?) ";
            } else {
                where += "AND e.data_inicio_evento <= (?) AND e.data_fim_evento >= (?) ";
            }
        }
                
        String sql = "SELECT e.* "
                + "FROM tb_evento e "
                + "INNER JOIN tb_endereco en ON e.id_evento = en.id_referencia AND en.referencia_endereco = 'evento' "
                + "INNER JOIN tb_cidade c ON c.id_cidade = en.id_cidade "
                + where
                + "ORDER BY e.data_fim_evento "
                + "LIMIT 9 OFFSET (?);";
                
        PreparedStatement st = conn.prepareStatement(sql);
        
        int id = 1;
        if ( (nomeEvento != null && !nomeEvento.equals("")) || (cidade != 0) || (data != null)) {
            
            if ( (nomeEvento != null && !nomeEvento.equals("")) ) {
                st.setString(id, nomeEvento);
                ++id;
            }
            if ( cidade != 0) {
                st.setInt(id, cidade);
                ++id;
            }
            if (data != null) {
                st.setTimestamp(id, new java.sql.Timestamp(data.getTime()));
                ++id;
                st.setTimestamp(id, new java.sql.Timestamp(data.getTime()));
                ++id;
            }
        }
        int start = (pagina - 1) * 9; 
        st.setInt(id, start);
        
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        this.conn.close();
        return resultado;
    }
    
    public List<Evento> selectEventosByIdUsuario(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE id_usuario = (?) "
                + "ORDER BY data_fim_evento DESC;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        this.conn.close();
        return resultado;
    }
    
    public Evento selectEventoById(int id) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE id_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Evento evento = new Evento();
        
        while (rs.next()) {
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
        }
        this.conn.close();
        return evento;
    }
    
    public Evento selectEventoByEventoData(Evento ev) throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "WHERE nome_evento = (?) AND data_inicio_evento = (?) AND data_fim_evento = (?) AND descricao_evento = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, ev.getNome());
        st.setTimestamp(2, new java.sql.Timestamp(ev.getDataInicio().getTime()));
        st.setTimestamp(3, new java.sql.Timestamp(ev.getDataFim().getTime()));
        st.setString(4, ev.getDescrição());
        ResultSet rs = st.executeQuery();
        Evento evento = new Evento();
        
        while (rs.next()) {
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
        }
        this.conn.close();
        return evento;
    }
    
    public List<Evento> selectLastThreeEventos() throws SQLException {
        
        String sql = "SELECT * "
                + "FROM tb_evento "
                + "ORDER BY data_fim_evento DESC "
                + "LIMIT 3;";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<Evento> resultado = new ArrayList<>();
        
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id_evento"));
            evento.setNome(rs.getString("nome_evento"));
            evento.setDataInicio(rs.getTimestamp("data_inicio_evento"));
            evento.setDataFim(rs.getTimestamp("data_fim_evento"));
            evento.setDescrição(rs.getString("descricao_evento"));
            evento.setImagem(rs.getString("imagem_evento"));
            evento.setAprovado(rs.getBoolean("aprovacao_evento"));
            
            resultado.add(evento);
        }
        this.conn.close();
        return resultado;
    }
    
    public int selectCountEventos(String nomeEvento, int cidade, Date data) throws SQLException {
        String where = "";
        if ( (nomeEvento != null && !nomeEvento.equals("")) ) {
            where += "WHERE e.nome_evento LIKE (?) ";
        }
        
        if ( cidade != 0 ) {
            if ( where.equals("") ) {
                where += "WHERE c.id_cidade = (?) ";
            } else {
                where += "AND c.id_cidade = (?) ";
            }
        }
        
        if ( data != null ) {
            if ( where.equals("") ) {
                where += "WHERE e.data_inicio_evento <= (?) AND e.data_fim_evento >= (?) ";
            } else {
                where += "AND e.data_inicio_evento <= (?) AND e.data_fim_evento >= (?) ";
            }
        }
        
        String sql = "SELECT COUNT(e.*) "
                + "FROM tb_evento e "
                + "INNER JOIN tb_endereco en ON e.id_evento = en.id_referencia AND en.referencia_endereco = 'evento' "
                + "INNER JOIN tb_cidade c ON c.id_cidade = en.id_cidade "
                + where + ";";
        
        PreparedStatement st = conn.prepareStatement(sql);
        if ( (nomeEvento != null && !nomeEvento.equals("")) || (cidade != 0) || (data != null)) {
            int id = 1;
            if ( (nomeEvento != null && !nomeEvento.equals("")) ) {
                st.setString(id, nomeEvento);
                ++id;
            }
            if ( cidade != 0) {
                st.setInt(id, cidade);
                ++id;
            }
            if (data != null) {
                st.setTimestamp(id, new java.sql.Timestamp(data.getTime()));
                ++id;
                st.setTimestamp(id, new java.sql.Timestamp(data.getTime()));
            }
        }
        
        ResultSet rs = st.executeQuery();
        int total = 0;
        while (rs.next()) {
            total = rs.getInt(1);
        }
        this.conn.close();
        return total;
    }
    
    public int selectCountEventos(int id) throws SQLException {
        String sql = "SELECT COUNT(*) "
                + "FROM tb_evento "
                + "WHERE id_usuario = (?);";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        int total = 0;
        while (rs.next()) {
            total = rs.getInt(1);
        }
        this.conn.close();
        return total;
    }
    
        public int selectCountEventosAprovados(int id) throws SQLException {
        String sql = "SELECT COUNT(*) "
                + "FROM tb_evento "
                + "WHERE id_usuario = (?) and aprovacao_evento = true;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        int total = 0;
        while (rs.next()) {
            total = rs.getInt(1);
        }
        this.conn.close();
        return total;
    }
    
    
    
    public int selectCountEventos() throws SQLException {
        String sql = "SELECT COUNT(*) "
                + "FROM tb_evento;";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        int total = 0;
        while (rs.next()) {
            total = rs.getInt(1);
        }
        this.conn.close();
        return total;
    }
    
    public void suspendEventoById(int id) throws SQLException {		
        String sql = "UPDATE tb_evento SET aprovacao_evento = FALSE where id_evento=(?);";		
        PreparedStatement stmt = conn.prepareStatement(sql);		
        stmt.setInt(1, id);		
        stmt.executeUpdate();
        this.conn.close();
    }		
        		
    public void activeEventoById(int id) throws SQLException {		
        String sql = "UPDATE tb_evento SET aprovacao_evento = TRUE where id_evento=(?);";		
        PreparedStatement stmt = conn.prepareStatement(sql);		
        stmt.setInt(1, id);		
        stmt.executeUpdate();
        this.conn.close();
    }
}
