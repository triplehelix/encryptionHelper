package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;

/**
 * Created by mikeh on 4/10/2016.
 */
public interface encryptionUtil {

    String encrypt(String plainText, String key) throws CryptoException;

    String decrypt(String cypher, String key) throws CryptoException;
}
