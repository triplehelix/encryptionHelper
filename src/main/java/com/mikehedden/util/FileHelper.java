package com.mikehedden.util;

import java.io.*;

/**
 * Created by MHEDDEN on 2016-04-12.
 */
public class FileHelper {

    public static String readStringFromFile(File file) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String currentLine;
            while (null != (currentLine = br.readLine())) {
                sb.append(currentLine);
                sb.append("\n");
            }
        }catch (IOException e){
            throw new IOException(e);
        }finally {
            if (null != br){
                br.close();
            }
        }
        return sb.toString();
    }

    public static String readStringFromFile(String filename) throws IOException{
        File file = new File(filename);
        return readStringFromFile(file);
    }

    public static void writeStringToFile(File file, String string) throws IOException{
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            bw.write(string);
        }catch (IOException e){
            throw new IOException(e);
        }finally{
            bw.close();
        }
    }

    public static void writeStringToFile(String filename, String string) throws IOException{
        File file = new File(filename);
        writeStringToFile(file, string);
    }
}
