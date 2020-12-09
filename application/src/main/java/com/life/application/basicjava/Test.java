package com.life.application.basicjava;

/**
 * @author tengyun
 * @date 2020/11/24 15:48
 **/
public class Test {

    public static void main(String[] args) {
        String src="/home/denny/Downloads/android-studio-ide-141.2135290-linux.zip";
        String dst="/home/denny/Downloads/a.zip";
        MultiCopyFileUtil mcf=new MultiCopyFileUtil(src,dst,10);
        mcf.copyFile();
    }

}
