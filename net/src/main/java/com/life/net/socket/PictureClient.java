package com.life.net.socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author tengyun
 * @date 2020/10/23 9:57
 **/
public class PictureClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8090);

        FileInputStream fileInputStream = new FileInputStream("4.jpg");

        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        while (fileInputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
        socket.shutdownOutput();
        outputStream.close();
        fileInputStream.close();
        socket.close();
    }

}
