package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;

/**
 * Created by mikeh on 4/10/2016.
 * This interface allows clients to encrypt or decrypt Strings using a key. The implementation of encryption type is provided in the concrete class
 */
interface encryptionUtil {

    String encrypt(String plainText, String key) throws CryptoException;

    String decrypt(String cypher, String key) throws CryptoException;
}
