package com.life.net.socket.userlogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tengyun
 * @date 2020/10/23 11:03
 **/
public class LoginServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(8091);

        for(;;) {
            Socket socket = server.accept();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User)objectInputStream.readObject();

            System.out.println(user.toString());
            socket.shutdownInput();
            objectInputStream.close();
            socket.close();
        }

    }

}
