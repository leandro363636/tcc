/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.exceptions;

/**
 *
 * @author mateus
 */
public class EmailNaoExisteException extends AppException {

    public EmailNaoExisteException() {
        super();
    }

    public EmailNaoExisteException(String string) {
        super(string);
    }

    public EmailNaoExisteException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public EmailNaoExisteException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}