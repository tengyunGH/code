package com.life.application.basicjava;

import java.io.File;

/**
 * @author tengyun
 * @date 2020/11/24 17:01
 **/
public class MyMultiCopyFileUtil {

    public static void main(String[] args) {
        File fromFile = new File("D://test.txt");
        long length = fromFile.length();
        for (int i = 0; i < 10; i ++) {
            long blockSize = length % 10 == 0 ? length / 10 : length / 10 + 1;
            Thread a = new CopyFileThread("D://test.txt", "D://to.txt", i, blockSize);
            a.start();
        }
    }


}
