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
public class Organizador extends Usuario implements Serializable {

    private int idOrganizador;
    private String cnpj;
    private String rg;
    private String nomeOrganizador;
    private String nomeResponsavel;
    private String sobrenome;

    public Organizador() {
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(int id) {
        this.idOrganizador = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeOrganizador() {
        return nomeOrganizador;
    }

    public void setNomeOrganizador(String nomeOrganizador) {
        this.nomeOrganizador = nomeOrganizador;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
