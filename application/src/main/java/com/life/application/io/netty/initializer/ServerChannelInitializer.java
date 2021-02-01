package com.life.application.io.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2020/11/9 10:05
 **/
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("idleStateHandler", new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
        //设置解码器
        p.addLast("http-codec",new HttpServerCodec());
        //聚合器，使用websocket会用到
        p.addLast("aggregator",new HttpObjectAggregator(65536));
        p.addLast("http-chunked", new ChunkedWriteHandler());
    }
}
