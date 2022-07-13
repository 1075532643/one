package com.ag.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.unix.Buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/*
* public int read(ByteBuffer dst)，从通道读取数据并放到缓冲区中
  public int write(ByteBuffer src)，把缓冲区的数据写到通道中
* */
public class bufferTest {
    public static void main(String[] args) throws IOException {
        //将一下内容写到指定的文件，如果文件不存在会自动创建
        String str = "第一次测试吧";
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/chenyongguang/Desktop/bufferTest1.txt");
        FileChannel channel = fileOutputStream.getChannel();

        //创建缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        //将数据放入缓冲区
        allocate.put(str.getBytes());

        allocate.flip();

        //将数据缓冲写入到channel
        channel.write(allocate);
        fileOutputStream.close();
    }
}
