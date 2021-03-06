package com.life.rabbitmq.mq.dead;

import com.rabbitmq.client.Channel;
import com.life.rabbitmq.mq.entity.Student;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import utils.JsonUtil;

import java.io.IOException;

/**
 * channel.basicReject(deliveryTag, true);
 * 1、第二个参数为true表示消息重新进入队列，false表示被丢弃或者进入死信队列，
 * 2、如果为true的话，这条消息还是会被转发到这个消费者这里来
 *
 * channel.basicNack(deliveryTag, false, true);
 * 1、第二个参数表示是否应用于多消息
 * 2、第三个参数表示是否重新进入队列，如果进入队列的话，则还是会被转发到这个消费者这里来
 *
 * channel.basicRecover(true);
 * 用于消息恢复，为true则表示尽可能的分配给其他的消费者，为false。则会分配给和之前相同的消费者，默认为true。
 *
 * @author tengyun
 * @date 2021/3/5 19:46
 **/
@Component
public class ConsumerDeadLetter {

    /**
     * 死信队列，key为test.dead1的业务逻辑处理
     **/
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "deadLetterQueue", durable = "true"),
        exchange = @Exchange("quartz.demo.exchange.deadLetter"),
        key = "test.dead1"))
    @RabbitHandler
    public void consumeDeadLetter1(@Payload String msg, Channel channel, Message message) throws IOException {
        System.out.println("进入死信队列，消费完毕" + msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 业务队列，
     **/
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(
            name = "businessA",
            arguments = {
                @Argument(name = "x-dead-letter-exchange", value = "quartz.demo.exchange.deadLetter"), // 死信交换机
                @Argument(name = "x-dead-letter-routing-key", value = "test.dead1") // 死信routingKey
            }),
        exchange = @Exchange("quartz.demo.exchange.business"),
        key = "businessA.test"))
    @RabbitHandler
    public void consumeBusinessA(@Payload String msg, Channel channel, Message message) throws IOException {
        Student student = (Student) JsonUtil.stringToJavaBean(msg, Student.class);
        try {
            if (student.getId() % 2 == 0) {
                throw new Exception("异常: " + student.getId());
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // 备注：这里这么写的话，和重试机制就没有关系了
            if (message.getMessageProperties().getRedelivered()) {
                // 已经是重新执行的消息了，拒接消息再进入队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            } else {
                // 是第一次消费这个消息出现异常，回调nack接口，重新发送消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false, true);
            }
        }
    }

    /**
     * 业务队列，
     **/
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(
            name = "businessB",
            arguments = {
                @Argument(name = "x-dead-letter-exchange", value = "quartz.demo.exchange.deadLetter"), // 死信交换机
                @Argument(name = "x-dead-letter-routing-key", value = "test.dead1") // 死信routingKey
            }),
        exchange = @Exchange("quartz.demo.exchange.business"),
        key = "businessB.test"))
    @RabbitHandler
    public void consumeBusinessB(@Payload String msg, Channel channel, Message message) throws IOException {
        Student student = (Student) JsonUtil.stringToJavaBean(msg, Student.class);
        try {
            if (student.getId() % 2 == 0) {
                throw new Exception("异常: " + student.getId());
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // 注意把第三个参数配置为false，表示不进行重传了，消息会直接进入死信队列或者被丢弃
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false, false);
        }
    }

}
