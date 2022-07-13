package com.ag.INetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class server {

    public static void main(String[] args) throws InterruptedException {
        //初始化创建2个NioEventLoopGroup，其中boosGroup用于Accetpt连接建立事件并分发请求，
        //workerGroup用于处理I/O读写事件和业务逻辑
        //2  基于ServerBootstrap(服务端启动引导类)，配置EventLoopGroup、Channel类型，连接参数、配置入站、出站事件handler
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //绑定管道类型
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(bossGroup, workerGroup)
                    //设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //使用匿名内部类的形式初始化通道对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //给pipeline管道设置处理器（handler）
                            socketChannel.pipeline().addLast(new serverHandler());
                        }
                    });//给workerGroup的EventLoop对应的管道设置处理器
            System.out.println("服务端准备就绪。。。");
            //绑定端口号，启动服务端
            ChannelFuture channelFuture = serverBootstrap.bind(9001).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功");
                } else {
                    System.out.println("端口绑定失败");

                }
            }).sync();

            //ChannelFuture channelFuture = serverBootstrap.bind(9001).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
