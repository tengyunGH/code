package com.life.application.basicjava.exception;

/**
 * @author tengyun
 * @date 2020/9/7 15:09
 **/
public class MyException {


        public static void main(String[] args) {
                System.out.println(test());
        }

        public static String test() {
                String x = "";
                try {
                        x = "tengyun";
                        System.out.println(x);
                        return x;
                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(x + "    catch");
                } finally {
                        x = "change";
                        System.out.println(x + "    finally");
                        return x;
                }
        }

}
