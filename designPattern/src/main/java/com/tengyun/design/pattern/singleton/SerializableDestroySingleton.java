package com.tengyun.design.pattern.singleton;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化破坏单例
 * 如何防止序列化破坏单例
 * @author tengyun
 * @date 2021/2/8 15:41
 **/
public class SerializableDestroySingleton {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 单例获取
        DoubleCheckSingleton instance = DoubleCheckSingleton.getInstance();

        // 序列化
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(instance);

        // 反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        DoubleCheckSingleton doubleCheckSingleton = (DoubleCheckSingleton) objectInputStream.readObject();

        // 比较，结果是false
        System.out.println(instance == doubleCheckSingleton);

        // 获取单例
        StaticClassSingleton instance1 = StaticClassSingleton.getInstance();
        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(instance1);

        // 反序列化
        ObjectInputStream objectInputStream1 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        StaticClassSingleton o = (StaticClassSingleton)objectInputStream1.readObject();
        // 输出true
        System.out.println(instance1 == o);

    }

}
