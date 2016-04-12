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
    Logger LOGGER = LoggerFactory.getLogger(FileEncryptor.class);
    private File targetFile;
    private String key;

    // TODO refactor with locking

    public FileEncryptor(String key, File targetFile){
        this.key = key;
        this.targetFile = targetFile;
    }

    public FileEncryptor(File keyfile, File targetFile) throws IOException{
        new FileEncryptor(getKeyFromFile(keyfile), targetFile);
    }

    public void encryptFile() throws CryptoException, IOException{
        String input = FileHelper.readStringFromFile(targetFile);
        encryptionUtil util = new encryptionUtilAESImpl();
        String encryptedString = util.encrypt(input, key);
        FileHelper.writeStringToFile(targetFile, encryptedString);
    }

    public void decryptFile() throws CryptoException, IOException{
        String input = FileHelper.readStringFromFile(targetFile);
        encryptionUtil util = new encryptionUtilAESImpl();
        String encryptedString = util.decrypt(input, key);
        FileHelper.writeStringToFile(targetFile, encryptedString);
    }

    private String getKeyFromFile(File keyfile) throws IOException {
        return FileHelper.readStringFromFile(keyfile);
    }

}
