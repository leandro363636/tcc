/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.beans;

import java.io.Serializable;

/**
 *
 * @author mateus
 */
public class ItemCompra implements Serializable {
    private int idCompra;
    private int idLote;
    private int quantidade;

    public ItemCompra() {
    }

    public ItemCompra(int idCompra, int idLote, int quantidade) {
        this.idCompra = idCompra;
        this.idLote = idLote;
        this.quantidade = quantidade;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
