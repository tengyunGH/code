package com.life.net.socket.userlogin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author tengyun
 * @date 2020/10/23 11:03
 **/
public class LoginClient {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 20; i++) {
            Socket socket = new Socket("127.0.0.1", 8091);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            User user = new User("tengyun", "tengyun123");
            outputStream.writeObject(user);
            socket.shutdownOutput();
            outputStream.close();
            socket.close();
        }

    }


}
