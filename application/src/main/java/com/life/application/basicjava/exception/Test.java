package com.life.application.basicjava.exception;

/**
 * @author tengyun
 * @date 2021/3/5 20:44
 **/
public class Test {

    public static void main(String[] args) throws Exception {
        try {
            int i = 10;
            for (int j = 0; j < 10; j++) {
                System.out.println(i / j);
            }
        } catch (Exception e) {
            System.out.println("yichang");
            throw new Exception("123");
        } finally {
            System.out.println("finally");
        }
    }

}
