package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;
import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mikeh on 4/10/2016.
 */
public abstract class encryptionUtilBase implements encryptionUtil {
    Logger LOGGER = LoggerFactory.getLogger(encryptionUtilBase.class);
    @Override
    public String encrypt(String plainText, String key) {
        String cipher;
        try{
            cipher = Base64.encodeBase64String(doCrypto(Cipher.ENCRYPT_MODE, key, plainText.getBytes()));
        }catch (CryptoException e){
            LOGGER.error("Encryption failed!", e);
            return null;
        }
        return cipher;
    }

    @Override
    public String decrypt(String cypher, String key) {
        String plainText;
        try{
            plainText =  new String(doCrypto(Cipher.DECRYPT_MODE, key, Base64.decodeBase64(cypher.getBytes())));
        }catch (CryptoException e){
            LOGGER.error("Decryption failed! ", e);
            return null;
        }
        return plainText;
    }

    protected abstract byte[] doCrypto(int cipherMode, String key, byte[] input) throws CryptoException;
}
