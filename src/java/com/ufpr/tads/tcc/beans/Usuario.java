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
public class Usuario implements Serializable {
    private int id;
    private String email;
    private String senha;
    private String tipo;
    private boolean ativo;
    private int idReferencia;
    private Endereço endereco;

    public Usuario() {
    }

    public Usuario(int id, String email, String senha, String tipo, Endereço endereco) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Endereço getEndereco() {
        return endereco;
    }

    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }
    
    public void setEndereco(Endereço endereco) {
        this.endereco = endereco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
