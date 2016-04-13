package com.mikehedden.util;

import java.io.*;

/**
 * Created by MHEDDEN on 2016-04-12.
 * Helper class to read a string from a file or write a string to a file.
 */
class FileHelper {

    static String readStringFromFile(File file) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        FileReader fr = null;
        try{
            fr = new FileReader(file);
        }catch (NullPointerException e) {
            throw new IOException("Inputted File returned a NPE. Filename: " + ((null != file) ? file.getAbsolutePath(): null));
        }
        try {
            br = new BufferedReader(fr);
            String currentLine;
            while (null != (currentLine = br.readLine())) {
                sb.append(currentLine);
                sb.append("\n");
            }
        }catch (IOException e) {
            throw new IOException(e);
        }finally {
            if (null != br){
                br.close();
            }
        }
        return sb.toString();
    }

    static String readStringFromFile(String filename) throws IOException{
        File file = new File(filename);
        return readStringFromFile(file);
    }

    static void writeStringToFile(File file, String string) throws IOException{
        if(!file.exists()){
            file.createNewFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))) {
            bw.write(string);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    static void writeStringToFile(String filename, String string) throws IOException{
        File file = new File(filename);
        writeStringToFile(file, string);
    }
}
