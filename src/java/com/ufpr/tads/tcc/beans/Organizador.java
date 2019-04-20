/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.beans;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class Organizador implements Serializable {
   private int idOrganizador; 
   private String cnpj;
   private String nomeDaOrganizacao;
   private String nomeDoResponsavel;
   private String rgDoResponsavel;
   private String email;
   private String senha;
   private String endereco;
   private int tipo;

    public Organizador() {
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeDaOrganizacao() {
        return nomeDaOrganizacao;
    }

    public void setNomeDaOrganizacao(String nomeDaOrganizacao) {
        this.nomeDaOrganizacao = nomeDaOrganizacao;
    }

    public String getNomeDoResponsavel() {
        return nomeDoResponsavel;
    }

    public void setNomeDoResponsavel(String nomeDoResponsavel) {
        this.nomeDoResponsavel = nomeDoResponsavel;
    }

    public String getRgDoResponsavel() {
        return rgDoResponsavel;
    }

    public void setRgDoResponsavel(String rgDoResponsavel) {
        this.rgDoResponsavel = rgDoResponsavel;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
   
    
   
}
