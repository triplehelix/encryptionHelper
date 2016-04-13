package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;
import java.io.IOException;

/**
 * Created by MHEDDEN on 2016-04-12.
 * This is a class that provides static methods to encrypt or decrypt files. As well as retrieve a key from a file.
 */
class FileEncryptor {
    // TODO refactor with locking

    static void encryptFile(String key, String targetFilename) throws CryptoException, IOException{
        if (null == key || null == targetFilename){
            throw new IOException("Parameters for encryptFile() cannot be null");
        }
        String input = FileHelper.readStringFromFile(targetFilename);
        encryptionUtil util = new encryptionUtilAESImpl();
        String encryptedString = util.encrypt(input, key);
        FileHelper.writeStringToFile(targetFilename, encryptedString);
    }

    static void decryptFile(String key, String targetFilename) throws CryptoException, IOException{
        if (null == key || null == targetFilename){
            throw new IOException("Parameters for decryptFile() cannot be null");
        }
        String input = FileHelper.readStringFromFile(targetFilename);
        encryptionUtil util = new encryptionUtilAESImpl();
        String encryptedString = util.decrypt(input, key);
        FileHelper.writeStringToFile(targetFilename, encryptedString);
    }

    static String getKeyFromFile(String keyfile) throws IOException {
        return FileHelper.readStringFromFile(keyfile);
    }

}
