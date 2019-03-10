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
public class EmailDuplicadoException extends AppException {

    public EmailDuplicadoException() {
        super();
    }

    public EmailDuplicadoException(String string) {
        super(string);
    }

    public EmailDuplicadoException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public EmailDuplicadoException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
