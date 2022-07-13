package com.ag.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j(topic = "nio.channel")
public class nioFileChannel {
    public static void main(String[] args) throws IOException {

        //文件的复制
        File file = new File("/Users/chenyongguang/Desktop/bufferTest1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream("/Users/chenyongguang/Desktop/bufferTest2.txt");

        FileChannel readChannel = fileInputStream.getChannel();
        FileChannel writeChannel = outputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        while (true){

            //这里有一个重要的操作，一定不要忘了
             /*
            public final Buffer clear() {
                position = 0;
                limit = capacity;
                mark = -1;
                return this;
            }
            */
            allocate.clear();
            int read = readChannel.read(allocate);
            System.out.println(read+"read");
            if(read == -1){
                break;
            }
            allocate.flip();
            writeChannel.write(allocate);
        }
        fileInputStream.close();
        outputStream.close();
    }
}
