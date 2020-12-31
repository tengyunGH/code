package com.life.application.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * @author tengyun
 * @date 2020/12/28 20:19
 **/
public class MyNetty {


    @Test
    public void myByteBuf() {
//        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(8,20);
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8,20);


        print(byteBuf);

        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);
        byteBuf.writeBytes(new byte[]{1,2,3,4});
        print(byteBuf);


    }

    public static void print (ByteBuf buf) {
        // 是否可读
        System.out.println("buf.isReadable()" + buf.isReadable());
        // 从哪可以读
        System.out.println("buf.readerIndex()" + buf.readerIndex());
        // 可以读多少字节
        System.out.println("buf.readableBytes()" + buf.readableBytes());


        System.out.println("buf.isWritable()" + buf.isWritable());
        System.out.println("buf.writerIndex()" + buf.writerIndex());
        System.out.println("buf.writableBytes()" + buf.writableBytes());

        System.out.println("buf.capacity()" + buf.capacity());
        System.out.println("buf.maxCapacity()" + buf.maxCapacity());

        System.out.println("buf.isDirect()" + buf.isDirect());
    }


    /*
     * 客户端
     * 1、主动发送数据
     * 2、别人什么时候给我发 event selector
     **/

    @Test
    public void clientMode() {
        // NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioSocketChannel client = new NioSocketChannel();
        client.connect(new InetSocketAddress("127.0.0.1", 9090));
        ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.compositeDirectBuffer();

    }

}
