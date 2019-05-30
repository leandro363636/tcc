/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.dao;

import com.ufpr.tads.tcc.beans.Compra;
import com.ufpr.tads.tcc.beans.ItemCompra;
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
public class CompraDAO {
    private final Connection conn;

    public CompraDAO() throws SQLException, ClassNotFoundException {
        this.conn = (Connection) new ConnectionFactory().getConnection();
    }
    
    public Compra getCarrinhoById(int id) throws SQLException {
        String sql = "SELECT * FROM tb_compra c WHERE c.id_compra=(?);";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            Compra compra = new Compra();
            compra.setId(res.getInt("id_compra"));
            compra.setStatus(res.getString("status_compra"));
            compra.setValorTotal(res.getFloat("valor_total_compra"));
            compra.setIdUsuario(res.getInt("id_usuario"));
            
            sql = "SELECT * FROM tb_lote_compra lc WHERE lc.id_compra=(?);";

            List<ItemCompra> items = new ArrayList<>();
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ItemCompra item = new ItemCompra();
                item.setIdCompra(rs.getInt("id_compra"));
                item.setIdLote(rs.getInt("id_lote"));
                item.setQuantidade(rs.getInt("quantidade_lote"));
                items.add(item);
            }
            
            compra.setItems(items);
            return compra;
        }
        this.conn.close();
        return null;
    }
}
