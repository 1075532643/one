package com.ag.meath;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/*
* 目标：客户端案例实现--基于NIO非阻塞*/
public class clientTest {
    public static void main(String[] args) throws Exception {
        //获取通道
        SocketChannel socket = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        //切换非阻塞
        socket.configureBlocking(false);
        //分配制定缓冲区大小
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //发送数据到服务端
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请输入.....");
            String msg = sc.nextLine();
            buf.put(("小光:"+msg).getBytes());
            buf.flip();
            socket.write(buf);
            buf.clear();
        }


    }
}
