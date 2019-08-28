/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mateus
 */
public class Compra implements Serializable {
    private int id;
    private String status;
    private float valorTotal;
    private int idUsuario;
    private List<ItemCompra> items;

    public Compra() {
    }

    public Compra(int id, String status, float valorTotal, int idUsuario, List<ItemCompra> items) {
        this.id = id;
        this.status = status;
        this.valorTotal = valorTotal;
        this.idUsuario = idUsuario;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemCompra> getItems() {
        return items;
    }

    public void setItems(List<ItemCompra> items) {
        this.items = items;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
