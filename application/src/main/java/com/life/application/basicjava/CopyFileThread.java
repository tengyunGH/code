package com.life.application.basicjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author tengyun
 * @date 2020/11/24 17:01
 **/
public class CopyFileThread extends Thread {

    private final String fromFileName;

    private final String toFileName;

    private final int threadId;
    /**
     * 该线程处理的字节数
     **/
    private final long blockSize;

    public CopyFileThread(String fromFileName, String toFileName, int threadId, long blockSize) {
        this.fromFileName = fromFileName;
        this.toFileName = toFileName;
        this.threadId = threadId;
        this.blockSize = blockSize;
    }

    @Override
    public void run() {
        try {
            RandomAccessFile reader = new RandomAccessFile(fromFileName, "r");
            FileChannel readerChannel = reader.getChannel();

            RandomAccessFile writer = new RandomAccessFile(toFileName, "rw");
            FileChannel writerChannel = writer.getChannel();
            int bufferSize = 1024 * 8;
            MappedByteBuffer mappedByteBuffer = writerChannel.map(FileChannel.MapMode.READ_WRITE, 0, blockSize);

            long startPos = threadId * blockSize;
            reader.seek(startPos);
            long endPos = Math.max(startPos + blockSize, fromFileName.length());

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bufferSize);
            while (startPos < endPos) {
                readerChannel.read(byteBuffer);
                if (endPos - startPos < bufferSize) {
                    byteBuffer.position((int)(endPos - startPos));
                }
                System.out.println("线程"+(threadId+1)+"下载了:" + byteBuffer.position());
                byteBuffer.flip();
                writer.seek(startPos);
                mappedByteBuffer.put(byteBuffer);
                startPos += bufferSize;
                mappedByteBuffer.clear();
                byteBuffer.clear();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int thirdMax(int[] nums) {



        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        long max = Long.MIN_VALUE;
        long secondMax = Long.MIN_VALUE;
        long thirdMax = Long.MIN_VALUE;
        for (int i : nums) {
            if (i == max || i == secondMax || i == thirdMax) {
                continue;
            }
            if (i > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = i;
                continue;
            }
            if (i > secondMax) {
                thirdMax = secondMax;
                secondMax = i;
                continue;
            }
            if (i > thirdMax) {
                thirdMax = i;
            }
        }
        return (int) (thirdMax == (int)Long.MIN_VALUE ? max : thirdMax);
    }

    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
        FileInputStream fis = new FileInputStream("d://test.txt");
        FileOutputStream fos = new FileOutputStream("d://io.txt");
        FileChannel fc = fis.getChannel();
        long timeStar = System.currentTimeMillis();// 得到当前的时间
        // fc.read(byteBuf);// 1 读取
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        System.out.println(fc.size()/1024);
        long timeEnd = System.currentTimeMillis();// 得到当前的时间
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");
        timeStar = System.currentTimeMillis();
        // fos.write(byteBuf.array());//2.写入
        mbb.flip();
        timeEnd = System.currentTimeMillis();
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");
        fos.flush();
        fc.close();
        fis.close();
    }
}
