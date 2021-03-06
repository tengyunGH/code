package com.life.rabbitmq.mq.manualack;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 注意：如果开启了手动ack模式acknowledge-mode为manual，那么和消息重传机制就没有关系了
 * 此时消息需要手动去回调ack或者nack或者rejected
 * @author tengyun
 * @date 2021/3/6 14:16
 **/
@Component
public class ManualAckConsumer {

    /**
     * durable = "true" 设置队列持久化
     *
     * @param msg 消息
     * @author tengyun
     * @date 14:14 2021/3/2
     **/
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "manual", durable = "true"),
        exchange = @Exchange(value = "quartz.demo.exchange.manual"),
        key = "manual.test1"))
    @RabbitHandler
    public void consumeManualAck1(@Payload Object msg, Channel channel, Message message) throws IOException {
        try {
            // 业务代码
            System.out.println("4   " + msg.toString());
            // 手动ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 如果消费者异常这里这么处理的话，和消息重传以及default-requeue-rejected的配置就都没有关系了
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收..." + e);
                // 拒绝消息, 如果配置了死信队列，则会进入死信队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                System.out.println("消息即将再次返回队列处理..." + e);
                // 回调nack接口返回队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "manual", durable = "true"),
        exchange = @Exchange(value = "quartz.demo.exchange.manual"),
        key = "manual.test2"))
    @RabbitHandler
    public void consumeManualAck2(@Payload Object msg, Channel channel, Message message) throws IOException {
        try {
            // 业务代码
            System.out.println("4   " + msg.toString());
            // 手动ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 如果只有这一行的话
            // 如果配置了default-requeue-rejected: true的话，那么消息一直会重传直到处理成功
            // 如果配置了default-requeue-rejected: false的话，消息会重传一次，以后的则直接进入死信队列，没有死信队列则被丢弃
            // 那总结起来的话，只留下下面这个代码，配置中加上default-requeue-rejected: true
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

}
