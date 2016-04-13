package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;
import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by mikeh on 4/10/2016.
 * This is the abstract class that implmeents the interface methods of encryptionUtil. The children of this class will implemenent doCrypto in the desired method.
 */
abstract class encryptionUtilBase implements encryptionUtil {

    @Override
    public String encrypt(String plainText, String key) throws CryptoException {
        return Base64.encodeBase64String(doCrypto(Cipher.ENCRYPT_MODE, key, plainText.getBytes()));
    }

    @Override
    public String decrypt(String cypher, String key) throws CryptoException {
        return new String(doCrypto(Cipher.DECRYPT_MODE, key, Base64.decodeBase64(cypher.getBytes())));
    }

    protected abstract byte[] doCrypto(int cipherMode, String key, byte[] input) throws CryptoException;
}
