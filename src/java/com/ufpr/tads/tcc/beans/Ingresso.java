/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.beans;
//
import java.io.Serializable;

/**
 *
 * @author Ronaldo
 */
public class Ingresso implements Serializable{
    
    private int id;
    private int serial;
    
    public Ingresso() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
    
    
}
