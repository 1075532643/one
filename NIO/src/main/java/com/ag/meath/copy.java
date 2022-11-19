package com.ag.meath;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class copy {
    public static void main(String[] args) throws IOException {
        File source = new File("NIO/data01.txt");
        File dest = new File("NIO/data02.txt");
        FileInputStream fileInputStream = new FileInputStream(source);
        //获得字节输出流
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        FileChannel outc = fileOutputStream.getChannel();
        FileChannel inc = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(2014);
        while (true){
            //开始读取
            int flag = inc.read(buffer);
            if(flag==-1){
                break;
            }
            //先清空缓冲区，再写入数据到缓冲区
            buffer.clear();
            //切换模式
            buffer.flip();
            outc.write(buffer);
        }
        outc.close();
        inc.close();

    }


}
