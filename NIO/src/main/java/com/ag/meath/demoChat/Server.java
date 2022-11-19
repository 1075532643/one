package com.ag.meath.demoChat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/*
* 成员聊天系统
* */
@Slf4j
public class Server {
    //定义一些成员属性，选择器，服务端通道，端口
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private final  static  int PORT=9999;

    //定义初始化代码逻辑
    public Server(){
        //接受选择器
        try {
            selector = Selector.open();
            //获取通道
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            //设置非阻塞通信模式
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
       //创建服务端对象
        Server server = new Server();
        //监听客户端各种消息
        server.listen();

    }

    /*
    * 监听各种状态信息
    * */
    private void listen() {
        try {
            while (selector.select() >0){
                //获取选择器中所有注册通道的就绪事件
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //循环开始遍历事件
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if(next.isAcceptable()){
                        //客户端接入请求
                        System.out.println("客户端接入....");
                        SocketChannel schannel = serverSocketChannel.accept();
                        schannel.configureBlocking(false);
                        //注册到选择器,监听读数据事件
                        schannel.register(selector,SelectionKey.OP_READ);


                    }else if(next.isReadable()){
                        //接收客户端消息，实现转发逻辑
                        readClientData(next);

                    }
                    iterator.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * 功能：接收当前客户端信息，转发给其他全部客户端通道
    * */
    private void readClientData(SelectionKey next) {
       SocketChannel socketChannel  = null;
        try {
            socketChannel=  (SocketChannel) next.channel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int len = socketChannel.read(buf);
            if(len>0){
                buf.flip();
                String msg = new String(buf.array(), 0, buf.remaining());
                log.info("接收到客户端端消息：{}",msg);
                //发送数据
                sendMsgToAllClient(msg,socketChannel);
                buf.clear();
            }
        }catch (Exception e){
            //代表当前是离线状态
            next.cancel();
            try {
                socketChannel.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    /*
    * 把当前客户端消息数据推送到当前全部注册的socketChannel
    * */
    private void sendMsgToAllClient(String msg,SocketChannel socketChannel) throws IOException {
        log.info("服务端开始转发消息,线程是：{}",Thread.currentThread().getName());
        for(SelectionKey key:selector.keys()){
            SocketChannel channel = (SocketChannel) key.channel();
            //不转发给我自己，过滤当前通道
            if(!(channel==socketChannel)){
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                channel.write(wrap);
            }
        };
    }


}
