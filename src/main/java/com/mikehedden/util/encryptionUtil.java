package com.mikehedden.util;

/**
 * Created by mikeh on 4/10/2016.
 */
public interface encryptionUtil {

    String encrypt(String plainText, String key);

    String decrypt(String cypher, String key);
}
