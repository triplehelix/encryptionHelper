package com.mikehedden.exceptions;

/**
 * Created by mikeh on 4/10/2016.
 * This is the CryptoException to be thrown when encountering Cryptography execptions in the excryptionUtil Interface implementations
 */
public class CryptoException extends Exception {
    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
