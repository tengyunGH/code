package com.life.rabbitmq.mq.delay;

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
 * @author tengyun
 * @date 2021/3/8 9:08
 **/
@Component
public class DelayTestConsumer {

    /**
     * 死信队列
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue("delay.dead.letter"),
        exchange = @Exchange("quartz.demo.exchange.delay.dead.letter"),
        key = "routing.key.delay.dead.letter"))
    @RabbitHandler
    public void consumeDelay(@Payload String msg, Channel channel, Message message) throws IOException {
        System.out.println("进入死信队列： " + msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 延时队列
     * 延时队列过期后，消息进入绑定的死信队列
     */



}
