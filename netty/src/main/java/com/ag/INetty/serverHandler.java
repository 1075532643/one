package com.ag.INetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import javax.swing.*;

public class serverHandler extends ChannelInboundHandlerAdapter {

    /*ChannelHandlerContext ctx:上下文对象, 含有 管道pipeline , 通道channel, 地址
    2. Object msg: 就是客户端发送的数据 默认Object
    * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
       // System.out.println("收到客户端："+ctx.channel().remoteAddress()+"发送的消息"+byteBuf.toString(CharsetUtil.UTF_8));
       byteBuf.release();

       String response = "hello,这是服务器收到消息后传回的数据";
        ByteBuf buffer = ctx.alloc().buffer(4 * response.length());
        buffer.writeBytes(response.getBytes());
        //把数据写到缓存并刷新
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //每个服务端发送回去的消息都要经过这个方法封装，ctx就是消息的主体
        ctx.writeAndFlush(Unpooled.copiedBuffer("channelRead方法 服务端 copy that!!",CharsetUtil.UTF_8));
       // super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
