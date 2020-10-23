package com.life.application.synchro;

/**
 * @author tengyun
 * @date 2020/9/21 9:03
 **/
public class AtomicIntegerTest {

    public static void main(String[] args) {
  /*      AtomicInteger m = new AtomicInteger(3);
        System.out.println(ClassLayout.parseInstance(m).toPrintable());
        m.incrementAndGet();
        System.out.println(ClassLayout.parseInstance(m).toPrintable());
        synchronized (m) {
            System.out.println(ClassLayout.parseInstance(m).toPrintable());
        }

        StringBuffer stringBuffer = new StringBuffer("tengyun");
        stringBuffer.append("ty");*/

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("tengyun");
        stringThreadLocal.get();
    }
}
