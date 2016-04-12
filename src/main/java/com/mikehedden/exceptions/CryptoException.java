package com.mikehedden.exceptions;

/**
 * Created by mikeh on 4/10/2016.
 */
public class CryptoException extends Exception {

    public CryptoException() {
    }

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
