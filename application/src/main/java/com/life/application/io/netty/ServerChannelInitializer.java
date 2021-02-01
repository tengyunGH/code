package com.life.application.io.netty;

import com.life.application.io.netty.handler.DispatcherHandler;
import com.life.application.io.netty.handler.DoctorHandler;
import com.life.application.io.netty.handler.DriverHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/1/23 16:03
 **/
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        int port = socketChannel.localAddress().getPort();
        System.out.println("initChannel++++++++++++++++++++++++++++++, and port is " + port);
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("idleStateHandler", new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
        //设置解码器
        p.addLast("http-codec",new HttpServerCodec());
        //聚合器，使用websocket会用到
        p.addLast("aggregator",new HttpObjectAggregator(65536));
        p.addLast("http-chunked", new ChunkedWriteHandler());
        // 每个端口对应的handler不相同，这里的port应该从配置文件中获取
        if (port == 8090) {
            p.addLast(new DriverHandler());
        } else if (port == 8091) {
            p.addLast(new DoctorHandler());
        } else {
            p.addLast(new DispatcherHandler());
        }
    }
}
