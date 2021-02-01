package com.life.application.io.netty.initializer;

import com.life.application.io.netty.handler.DriverHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * @author tengyun
 * @date 2020/11/9 10:21
 **/
public class DriverChannelInitializer extends ServerChannelInitializer {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        super.initChannel(socketChannel);
        socketChannel.pipeline().addLast(new DriverHandler());
    }

}
