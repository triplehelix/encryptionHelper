package com.mikehedden.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by mikeh on 4/10/2016.
 */
public class encryptionUtilAESImplTest {
    Logger LOGGER = LoggerFactory.getLogger(encryptionUtilAESImplTest.class);
    encryptionUtil instance;

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
        String cipher = instance.encrypt(plainText,key);
        LOGGER.info("Cipher=" + cipher);

        assertNotNull(cipher);
        assertNotEquals("Testing plainText not equal to plainText after encryption", plainText, cipher);

        String newPlainText = instance.decrypt(cipher,key);
        LOGGER.info("New PlainText=" + newPlainText);

        assertNotNull(newPlainText);
        assertEquals(newPlainText, plainText);
    }
}