package com.life.application.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author tengyun
 * @date 2021/1/23 16:14
 **/
public class NettyServerBind3Port {

    public static void main(String[] args) {
        NettyServerBind3Port nettyServer = new NettyServerBind3Port();
        nettyServer.start();
    }

    public void start() {

        NioEventLoopGroup boss = new NioEventLoopGroup(3);
        NioEventLoopGroup worker = new NioEventLoopGroup(10);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture1 = bootstrap.bind(new InetSocketAddress(8090)).sync();
            ChannelFuture channelFuture2 = bootstrap.bind(new InetSocketAddress(8091)).sync();
            ChannelFuture channelFuture3 = bootstrap.bind(new InetSocketAddress(8092)).sync();

            System.out.println("netty server start to listen at port: 8080");
            System.out.println("netty server start to listen at port：8081");
            System.out.println("netty server start to listen at port: 8082");

            channelFuture1.channel().closeFuture().addListener((ChannelFutureListener) future -> future.channel().closeFuture());
            channelFuture2.channel().closeFuture().addListener((ChannelFutureListener) future -> future.channel().closeFuture());
            channelFuture3.channel().closeFuture().addListener((ChannelFutureListener) future -> future.channel().closeFuture());

        } catch (InterruptedException e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
