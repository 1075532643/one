package com.ag.meath;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class serverTest {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel sschannel = ServerSocketChannel.open();
        sschannel.configureBlocking(false);

        sschannel.bind(new InetSocketAddress(9999));

        System.out.println("服务端启动......");
        Selector selector = Selector.open();
        sschannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select()>0){
            //获取选择期汇总所有注册的通道中已经就须好的时间
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
            //提取这个事件
                    SelectionKey sk = it.next();
               //说明接受事件就绪，获取介入的客户端通道
                if(sk.isAcceptable()){
                    SocketChannel schannel = sschannel.accept();
                    //切换为非阻塞模式
                    schannel.configureBlocking(false);
                    //客户端已经接入，将本客户端通道注册到选择器
                    schannel.register(selector,SelectionKey.OP_READ);

                }else if(sk.isReadable()){
                    //读事件--》获取当前读事件
                    SocketChannel schannel = (SocketChannel) sk.channel();
                    //读取事件
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len=0;
                    while ((len =schannel.read(buf)) != -1){
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        //归到第一个位置继续读
                        buf.clear();
                    }
                    it.remove();
                }
            }


            }

    }
}
