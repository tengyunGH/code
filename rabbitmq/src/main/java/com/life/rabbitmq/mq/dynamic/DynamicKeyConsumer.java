package com.life.rabbitmq.mq.dynamic;

import com.rabbitmq.client.Channel;
import com.life.rabbitmq.mq.entity.Student;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import utils.JsonUtil;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author tengyun
 * @date 2021/3/6 14:20
 **/
@Component
public class DynamicKeyConsumer implements InitializingBean {

    /**
     * test.id 从配置文件中获取，分布式集群，每台配置的不一样
     * 队列名不指定，spring会为每台分布式生成一个随机的队列名
     * 生产者根据key做一个hash，作为这个消息的routingKey，和这里的bindingKey做匹配
     * 在mq里面，每台分布式机器对应一个队列，具有相同key的消息就会被顺序的发送到一台消费者上，这样就保证了顺序性
     **/
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(),
        exchange = @Exchange("quartz.demo.exchange.sequence"),
        key = {"${test.id}"}))
    @RabbitHandler
    public void consumeSequence1(Object msg) {
        System.out.println("sequence1  " + msg.toString());
    }


    /**
     * 使用一台消费者，消费者将消息保存在多组内存队列中
     * 具有相关性的消息按照顺序放在一个队列中，每个队列由一个线程去执行，这样也可以保证顺序性
     **/
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue("sequence1"),
        exchange = @Exchange("quartz.demo.exchange.sequence"),
        key = "sequence1"))
    @RabbitHandler
    public void consumeSequence2(@Payload String msg, Channel channel, Message message) throws IOException {
        Student student = (Student) JsonUtil.stringToJavaBean(msg, Student.class);
        long key = student.getId() % groupSize;
        LinkedBlockingDeque<Object> queue = groupMemoryQueue.getOrDefault(key, new LinkedBlockingDeque<>());
        queue.addLast(student);
        groupMemoryQueue.put(key, queue);
        // 有消息放进去了，唤醒消费线程
        synchronized (locks[Math.toIntExact(key)]) {
            locks[Math.toIntExact(key)].notify();
        }
        System.out.println(key + "   消息放入队列，唤醒完成" + student.getId());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    private final ConcurrentHashMap<Long, LinkedBlockingDeque<Object>> groupMemoryQueue = new ConcurrentHashMap<>();

    private final int groupSize = 5;

    private Object[] locks;

    @Override
    public void afterPropertiesSet() throws Exception {
        locks = new Object[groupSize];
        for (int i = 0; i < groupSize; i++) {
            locks[i] = new Object();
            new ConsumeQueue(i).start();
        }
    }

    private class ConsumeQueue extends Thread {

        private final int key;

        final Object lock;

        public ConsumeQueue(int key) {
            this.key = key;
            lock = locks[key];
        }

        @Override
        public void run() {
            LinkedBlockingDeque<Object> queue = groupMemoryQueue.get((long) key);
            while (true) {
                synchronized (lock) {
                    // 如果队列中没有消息要处理，就阻塞
                    if (queue == null || queue.size() == 0) {
                        try {
                            System.out.println(key + "   没有消息，阻塞等待！");
                            lock.wait();
                            System.out.println(key + "   被唤醒！");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                queue = groupMemoryQueue.get((long) key);
                try {
                    Student o = (Student) queue.takeFirst();
                    System.out.println(o.toString());
                    System.out.println(key + "   消费完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
