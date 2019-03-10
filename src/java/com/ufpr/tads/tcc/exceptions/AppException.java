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
public class AppException extends Exception {

    public AppException() {
        super();
    }

    public AppException(String string) {
        super(string);
    }

    public AppException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public AppException(Throwable thrwbl) {
        super(thrwbl);
    }
}
