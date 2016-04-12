package com.mikehedden.util;

import com.mikehedden.exceptions.CryptoException;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by MHEDDEN on 2016-04-12.
 * This Handles the CLI for the application.
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
        CommandLine cmd;
        try{
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")){
                printHelpString();
            }else{
                LOGGER.info("Processing, please wait...");
                String filename = cmd.getOptionValue("f");
                String key = cmd.getOptionValue("k");
                String keyfilename = cmd.getOptionValue("y");
                String mode = cmd.getOptionValue("m");
                if (null == mode || null == filename || (null == key && null == keyfilename)){
                    // Invalid parameters
                    printHelpString();
                }else{
                    // Do work
                    FileEncryptor encryptor;
                    File keyFile = null;
                    //create encryptor based on
                    if (null == key){
                        keyFile = new File(keyfilename);
                        try {
                            key = FileEncryptor.getKeyFromFile(keyFile);
                        }catch (IOException e){
                            LOGGER.error("IO exception occurred while opening key file.", e);
                            return;
                        }
                    }

                    // key and filename collected, check mode
                    if (mode.toLowerCase().equals("encrypt")){
                        try{
                            FileEncryptor.encryptFile(key, filename);
                            LOGGER.info("File: " + filename + " has been successfully encrypted.");
                        } catch (IOException e){
                            LOGGER.error("IOException occurred while encrypting file", e);
                        } catch (CryptoException e){
                            LOGGER.error("CryptoException occurred while encryption file", e);
                        }
                    }else if (mode.toLowerCase().equals("decrypt")) {
                        try{
                            FileEncryptor.decryptFile(key, filename);
                            LOGGER.info("File: " + filename + " has been successfully decrypted.");
                        } catch (IOException e){
                            LOGGER.error("IOException occurred while decrypting file", e);
                        } catch (CryptoException e){
                            LOGGER.error("CryptoException occurred while decryption file", e);
                        }
                    }else{
                        LOGGER.error("Invalid parameter provided for mode. Must be 'encrypt' or 'decrypt'");
                        printHelpString();
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
