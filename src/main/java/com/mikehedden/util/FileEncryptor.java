package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by MHEDDEN on 2016-04-12.
 */
public class FileEncryptor {
    // TODO refactor with locking

    public static void encryptFile(String key, String targetFilename) throws CryptoException, IOException{
        if (null == key || null == targetFilename){
            throw new IOException("Parameters for encryptFile() cannot be null");
        }
        String input = FileHelper.readStringFromFile(new File(targetFilename));
        encryptionUtil util = new encryptionUtilAESImpl();
        String encryptedString = util.encrypt(input, key);
        FileHelper.writeStringToFile(new File(targetFilename), encryptedString);
    }

    public static void decryptFile(String key, String targetFilename) throws CryptoException, IOException{
        if (null == key || null == targetFilename){
            throw new IOException("Parameters for decryptFile() cannot be null");
        }
        String input = FileHelper.readStringFromFile(new File(targetFilename));
        encryptionUtil util = new encryptionUtilAESImpl();
        String encryptedString = util.decrypt(input, key);
        FileHelper.writeStringToFile(new File(targetFilename), encryptedString);
    }

    public static String getKeyFromFile(File keyfile) throws IOException {
        return FileHelper.readStringFromFile(keyfile);
    }

}
