package com.life.application.io.netty.initializer;

import com.life.application.io.netty.handler.DoctorHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * @author tengyun
 * @date 2020/11/9 10:23
 **/
public class DoctorChannelInitializer extends ServerChannelInitializer {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        super.initChannel(socketChannel);
        socketChannel.pipeline().addLast(new DoctorHandler());
    }

}
