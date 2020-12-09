package com.life.application.basicjava;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 多线程复制文件工具类
 *
 * @author denny
 *
 */
public class MultiCopyFileUtil {
    /**
     * 源文件
     **/
    private final String src;
    /**
     * 目标文件
     **/
    private final String dst;
    /**
     * 开启多少个线程去复制源文件
     **/
    private final int threadSize;
    /**
     * 包含3个参数的构造方法
     *
     * @param src
     *            源文件
     * @param dst
     *            目标文件
     * @param threadSize
     *            开启多少个线程去复制源文件
     */
    public MultiCopyFileUtil(String src, String dst, int threadSize) {
        super();
        this.src = src;
        this.dst = dst;
        this.threadSize = threadSize;
    }

    /**
     * 复制文件的方法
     */
    public void copyFile() {
        // 源文件的File对象
        File file = new File(src);
        // 获取源文件大小字节数
        long fileSize = file.length();
        // 计算每个线程下载的字节数
        long block = fileSize % threadSize == 0 ? fileSize / threadSize : fileSize / threadSize + 1;
        // 开启循环调用
        for (int threadId = 0; threadId < threadSize; threadId++) {
            // 开启多个个线程来进行文件的复制，
            new DownloadThread(fileSize, block, threadId).start();
        }
    }

    /**
     *
     * @author denny
     *
     */
    private class DownloadThread extends Thread {
        private final long fileSize;// 文件大小
        private final long block;// 每个线程下载的字节数
        private final int threadId;// 线程的ID号,从0开始

        public DownloadThread(long fsize, long block, int threadId) {
            super();
            this.fileSize = fsize;
            this.block = block;
            this.threadId = threadId;

        }

        /**
         * 重写run方法
         */
        @Override
        public void run() {
            try {

                // 随机访问文件对象 声明IO类，以只读的模式访问文件
                RandomAccessFile reader = new RandomAccessFile(src, "r");
                // 随机访问文件对象
                RandomAccessFile writer = new RandomAccessFile(dst, "rw");
                // 每个线程下载的启始位置
                long startPos = threadId * block;
                // 确定每个线程下载的结束位置
                long endPos = Math.min(startPos + block, fileSize);
                /*
                 * 判断下载的结束位置是不是比文件最大的位置还大，
                 * 是的话就是文件的最大位置，
                 * 不是的话就是开始位置+要下载的字节大小
                 */

                //设置游标的位置
                reader.seek(startPos);
                //设置目标文件的
                writer.seek(startPos);
                //设置缓冲区
                //缓冲区大小
                int buffSize = 1024 * 1024;
                byte[] buf=new byte[buffSize];
                while(startPos<endPos){
                    int len;
                    //判断是不是到结束位置
                    if(startPos+ buffSize <endPos){
                        //读取文件到缓冲区,读满
                        len=reader.read(buf);

                    }else{
                        //把剩下的填不满缓冲区的数据写到缓冲区中
                        len=reader.read(buf, 0, (int)(endPos-startPos));
                    }
                    //改变起始位置
                    startPos+=len;
                    /*写入目标文件
                     * 把缓冲区的文件写入目标文件
                     */
                    writer.write(buf,0,len);
                    System.out.println("线程"+(threadId+1)+"下载了:"+len);
                }
                reader.close();
                writer.close();
                System.out.println("线程"+(threadId+1)+"下载完毕");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

