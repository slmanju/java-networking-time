package com.slmanju.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class EchoServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new EchoServerHandler());
    }

}
