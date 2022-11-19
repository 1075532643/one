package com.ag.meath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class test02 {
    public static void main(String[] args) {
        try {

            //将数据写到channel中
            FileOutputStream fos = new FileOutputStream("NIO/data01.txt");
            FileChannel channel = fos.getChannel();
            //分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello,java".getBytes(StandardCharsets.UTF_8));
            //把缓冲区切换成写出模式
            buffer.flip();
            channel.write(buffer);
            channel.close();
            System.out.println("写数据到文件中");

        } catch (Exception e) {
           e.printStackTrace();
        }

    }
}
