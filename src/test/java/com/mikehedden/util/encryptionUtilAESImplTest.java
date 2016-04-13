package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by mikeh on 4/10/2016.
 * This is the Junit test for encryptionUtilAESImpl
 */
public class encryptionUtilAESImplTest {
    private Logger LOGGER = LoggerFactory.getLogger(encryptionUtilAESImplTest.class);
    private encryptionUtil instance;

    @org.junit.Before
    public void setUp() throws Exception {
        this.instance = new encryptionUtilAESImpl();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        this.instance = null;
    }

    @org.junit.Test
    public void doCrypto() throws Exception {
        testEncryptionDecryption();
    }

    private void testEncryptionDecryption() {
        String key = "7D3C7926D3EF71D9";
        String plainText = "This is a cipher test";
        LOGGER.info("Original PlainText=" + plainText);
        String cipher = null;
        try {
            cipher = instance.encrypt(plainText, key);
        }catch (CryptoException e){
            LOGGER.error("CryptoException occurred while encrypting:", e);
            fail("CryptoException occurred when it shouldn't have.");
        }
        LOGGER.info("Cipher=" + cipher);

        assertNotNull(cipher);
        assertNotEquals("Testing plainText not equal to plainText after encryption", plainText, cipher);

        String newPlainText = null;
        try{
            newPlainText = instance.decrypt(cipher,key);
        }catch (CryptoException e){
            LOGGER.error("CryptoException occurred while decrypting:", e);
            fail("CryptoException occurred when it shouldn't have.");
        }
        LOGGER.info("New PlainText=" + newPlainText);

        assertNotNull(newPlainText);
        assertEquals(newPlainText, plainText);
    }
}