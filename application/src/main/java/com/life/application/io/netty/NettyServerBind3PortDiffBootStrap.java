package com.life.application.io.netty;

import com.life.application.io.netty.handler.DriverHandler;
import com.life.application.io.netty.initializer.DispatcherChannelInitializer;
import com.life.application.io.netty.initializer.DoctorChannelInitializer;
import com.life.application.io.netty.initializer.DriverChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author tengyun
 * @date 2021/1/23 17:03
 **/
public class NettyServerBind3PortDiffBootStrap {
    public static void main(String[] args) {
        NettyServerBind3PortDiffBootStrap nettyServer = new NettyServerBind3PortDiffBootStrap();
        nettyServer.start();
    }

    public void start() {

        NioEventLoopGroup boss = new NioEventLoopGroup(3);
        NioEventLoopGroup worker = new NioEventLoopGroup(10);

        try {
            ServerBootstrap bootstrap1 = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new DriverChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture1 = bootstrap1.bind(new InetSocketAddress(8090)).sync();

            ServerBootstrap bootstrap2 = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new DoctorChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture2 = bootstrap2.bind(new InetSocketAddress(8091)).sync();

            ServerBootstrap bootstrap3 = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new DispatcherChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture3 = bootstrap3.bind(new InetSocketAddress(8092)).sync();

            System.out.println("netty server start to listen at port: 8090");
            System.out.println("netty server start to listen at port：8091");
            System.out.println("netty server start to listen at port: 8092");

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
