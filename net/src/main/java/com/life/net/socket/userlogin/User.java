package com.life.net.socket.userlogin;

import java.io.Serializable;

/**
 * @author tengyun
 * @date 2020/10/23 11:03
 **/
public class User implements Serializable {

    long serialVersionUID = -1L;

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
