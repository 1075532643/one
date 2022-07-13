package com.ag.INetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import javax.security.auth.message.callback.SecretKeyCallback;

public class client {
    public static void main(String[] args) throws InterruptedException {
        //负责处理channel上面的事件
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            //创建bootstrap对象，配置参数
            Bootstrap bootstrap = new Bootstrap();
            //设置线程组
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    //添加 处理器。可以继承ChannelInitializer实现自己自定义的初始化方法
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //addLast(new handlder) 添加最后一个业务处理，同样可以添加其他的业务处理，因为业务处理是一个链条。串行执行
                            socketChannel.pipeline().addLast(new clientHandler());
                        }
                    });
            System.out.println("客户端准备就绪");
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9001).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }
}
