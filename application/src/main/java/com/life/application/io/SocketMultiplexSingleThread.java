package com.life.application.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author tengyun
 * @date 2020/12/17 19:37
 **/
public class SocketMultiplexSingleThread {

    ServerSocketChannel serverSocketChannel = null;
    Selector selector = null;

    public void init() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9090));

        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void bootstrap() throws IOException {
        while (true) {
            while (selector.select(500) > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    if (key.isAcceptable()) {
                        acceptHandler(key);
                    } else if (key.isReadable()) {
                        readHandler(key);
                    } else if (key.isWritable()) {
                        writeHandler(key);
                    }
                }
            }
        }
    }

    private void writeHandler(SelectionKey key) {
    }

    private void readHandler(SelectionKey key) {

    }

    private void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel client = ssc.accept();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
        // 将这个缓冲区和这个连接的文件描述符关联起来
        client.register(selector, SelectionKey.OP_READ, buffer);
    }

    public static void main(String[] args) {

    }

}
