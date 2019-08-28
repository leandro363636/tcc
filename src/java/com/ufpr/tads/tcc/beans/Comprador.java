package com.ufpr.tads.tcc.beans;
//

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ronaldo
 */
public class Comprador extends Usuario implements Serializable {

    private int idComprador;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private Date dataNascimento;

    public Comprador() {

    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int id) {
        this.idComprador = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
