package com.life.application.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tengyun
 * @date 2020/9/2 16:29
 **/
public class TestMain extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        AtomicInteger integer = new AtomicInteger();
        integer.incrementAndGet();

        TestMain classLoader = new TestMain();

        classLoader.loadClass("com.life.application.classloader.Hello");
        Class clazz = classLoader.findClass("com.life.application.classloader.Hello");
        Object hello =  clazz.newInstance();

        System.out.println("success");

        clazz.getMethod("test").invoke(hello);

        // 此时不会进行类加载
        Hello hello1;
        System.out.println("还没new");

        // 进行了类加载、分配内存、对象初始化、设置对象头、实例化
        hello1 = new Hello(2);
        System.out.println(hello1.getClass().getClassLoader());
       // System.out.println(hello.getClass().getClassLoader());



//        Class clazz = Hello.class;
//        System.out.println("++++++++++");
//        System.out.println(Hello.i);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File("D:/develop/idea/projects/code/application/src/main/java/com/life/application/classloader/Hello.class");
        try {
            // 将文件流写入字节数组中
            FileInputStream fileInputStream = new FileInputStream(file);
            int len = fileInputStream.available();
            byte[] bytes = new byte[len];
            fileInputStream.read(bytes);
            fileInputStream.close();

            // 通过字节流产生这个类的class对象
            Class clasz = defineClass(name, bytes, 0, len);
            resolveClass(clasz);
            System.out.println("__________________");
            return clasz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

}
