package com.testRPCCodeing.anotherTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Socketserver {
    public static void main(String[] args) throws IOException {
        int port = 9000;
        ServerSocket serverSocket = new ServerSocket(port);
        //socket一直监听连接的客户端
        Socket accept = serverSocket.accept();
        //获取客户端的文件
        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1){
            stringBuilder.append(new String(bytes,0,len,"UTF-8"));
        }
        //收到的信息
        System.out.println(stringBuilder.toString());
        outputStream.write("返回的信息".getBytes("UTF-8"));


    }
}
