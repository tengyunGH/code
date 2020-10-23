package com.life.net.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tengyun
 * @date 2020/10/23 9:57
 **/
public class PictureServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();

        FileOutputStream outputStream = new FileOutputStream("tengyun.jpg");
        byte[] bytes = new byte[1024];
        while(inputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
        socket.shutdownInput();
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

    }

}
