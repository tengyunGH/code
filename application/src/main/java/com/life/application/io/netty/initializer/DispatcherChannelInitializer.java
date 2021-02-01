package com.life.application.io.netty.initializer;

import com.life.application.io.netty.handler.DispatcherHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * @author tengyun
 * @date 2020/11/9 10:22
 **/
public class DispatcherChannelInitializer extends ServerChannelInitializer {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        super.initChannel(socketChannel);
        socketChannel.pipeline().addLast(new DispatcherHandler());
    }

}
