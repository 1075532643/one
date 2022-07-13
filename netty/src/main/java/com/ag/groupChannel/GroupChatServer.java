package com.ag.groupChannel;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

@Slf4j
public class GroupChatServer {
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private static final int PORT=9002;

    //构造器，初始化工作
    public GroupChatServer() throws IOException {

        //打开选择器
        Selector.open();
        ServerSocketChannel.open();

        //绑定服务器监听的端口
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        //配置非阻塞
        serverSocketChannel.configureBlocking(false);


    }

}
