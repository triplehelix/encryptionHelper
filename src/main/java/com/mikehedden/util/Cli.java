package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by MHEDDEN on 2016-04-12.
 */
public class Cli {
    private static final Logger LOGGER = LoggerFactory.getLogger(Cli.class);
    private String[] args;
    private Options options = new Options();
    private static final String header = "Use this utility to quickly encrypt & decrypt files";
    private static final String footer = "Github page: https://github.com/triplehelix/encryptionHelper";

    public Cli(String[] args){
        this.args = args;

        options.addOption("h", "help", false, "Show help");
        options.addOption("k", "key", true, "Provide key to use for encryption.");
        options.addOption("m", "mode", true, "set mode: encrypt or decrypt");
        options.addOption("f", "file", true, "target file to encrypt or decrypt");
        options.addOption("y", "key-file", true, "target file to read key from");
    }

    public void parse(){
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try{
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")){
                printHelpString();
            }else{
                String filename = cmd.getOptionValue("f");
                String key = cmd.getOptionValue("k");
                String keyfilename = cmd.getOptionValue("y");
                String mode = cmd.getOptionValue("m");
                if (null == mode || null == filename || (null == key && null == keyfilename)){
                    // Invalid parameters
                    printHelpString();
                }else{
                    // Do work
                    FileEncryptor encryptor = null;
                    File file = new File(filename);
                    if (null == key){
                        File keyFile = new File(keyfilename);
                        try {
                            encryptor = new FileEncryptor(keyFile, file);
                        }catch (IOException e){
                            LOGGER.error("IO exception occurred while opening key file.", e);
                            return;
                        }
                    }else{
                        encryptor = new FileEncryptor(key, file);
                    }
                    if (mode.toLowerCase().equals("encrypt")){
                        try{
                            encryptor.encryptFile();
                        } catch (IOException e){
                            LOGGER.error("IOException occurred while encrypting file", e);
                            return;
                        } catch (CryptoException e){
                            LOGGER.error("CryptoException occurred while encryption file", e);
                            return;
                        }
                    }else if (mode.toLowerCase().equals("decrypt")) {
                        try{
                            encryptor.decryptFile();
                        } catch (IOException e){
                            LOGGER.error("IOException occurred while decrypting file", e);
                            return;
                        } catch (CryptoException e){
                            LOGGER.error("CryptoException occurred while decryption file", e);
                            return;
                        }
                    }else{
                        LOGGER.error("Invalid parameter provided for mode. Must be 'encrypt' or 'decrypt'");
                        printHelpString();
                        return;
                    }
                }
            }
        } catch (ParseException e){
            LOGGER.error("Failed to parse command line properties", e);
            printHelpString();
        }
    }

    private void printHelpString(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Encryption Helper", header, options, footer, true);
    }
}
