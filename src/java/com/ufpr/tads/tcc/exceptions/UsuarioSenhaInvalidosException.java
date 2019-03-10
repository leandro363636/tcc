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
public class UsuarioSenhaInvalidosException extends AppException {

    public UsuarioSenhaInvalidosException() {
        super();
    }

    public UsuarioSenhaInvalidosException(String string) {
        super(string);
    }

    public UsuarioSenhaInvalidosException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UsuarioSenhaInvalidosException(Throwable thrwbl) {
        super(thrwbl);
    }
    
    
    
}
