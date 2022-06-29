package com.ag.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class downloadTest {


    public static void main(String[] args) {
        String source = "/Users/chenyongguang/Desktop/第一个.png";
        String target = "/Users/chenyongguang/Desktop/第二个.png";
        download(source,target);

    }



    public static void download(String sourceFilePath, String targetFilePath){
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try{
            inputStream = new FileInputStream(sourceFile);

             outputStream = new FileOutputStream(targetFile);
            byte[] buffer = new byte[4096];
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}

