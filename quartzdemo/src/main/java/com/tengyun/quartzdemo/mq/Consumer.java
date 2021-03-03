package com.tengyun.quartzdemo.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author tengyun
 * @date 2021/3/1 13:56
 **/
@Component
public class Consumer {

    @Resource
    private ConsumerService consumerService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(), exchange = @Exchange(value = "quartz.demo.exchange.direct"), key = "quartz.demo.direct"))
    @RabbitHandler
    public void consumeDirect(Object msg) {
        System.out.println("1   " + msg.toString());
    }

    /**
     * 广播类型的交换机不需要指定binding key，交换机会转发到他绑定的所有队列中
     * @author tengyun
     * @date 14:59 2021/3/1
     **/
    @RabbitListener(bindings = @QueueBinding(value = @Queue("queueA"), exchange = @Exchange(value = "quartz.demo.exchange.fanout", type = ExchangeTypes.FANOUT)))
    @RabbitHandler
    public void consumeFanout1(Object msg) {
        System.out.println("2   " + msg.toString());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue("queueB"), exchange = @Exchange(value = "quartz.demo.exchange.fanout", type = ExchangeTypes.FANOUT)))
    @RabbitHandler
    public void consumeFanout2(Object msg) {
        System.out.println("3   " + msg.toString());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(), exchange = @Exchange(value = "quartz.demo.exchange.fanout", type = ExchangeTypes.FANOUT)))
    @RabbitHandler
    public void consumeFanout5(Object msg) {
        System.out.println("5   " + msg.toString());
    }

    /**
     * durable = "true" 设置队列持久化
     * @author tengyun
     * @date 14:14 2021/3/2
     * @param msg 消息
     **/
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "qu", durable = "true"),
        exchange = @Exchange(value = "quartz.demo.exchange.header", type = ExchangeTypes.HEADERS),
        arguments = {
            @Argument(name = "header1", value = "1"),
            @Argument(name = "header2", value = "2"),
            @Argument(name = "x-match", value = "any")
        }
    ))
    @RabbitHandler
    public void consumeHeader(Object msg, Channel channel, Message message) throws IOException {
        try {
            consumerService.execute(msg);
            // 业务代码
            System.out.println("4   " + msg.toString());
            // 手动ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收..." + e);
                // 拒绝消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                System.out.println("消息即将再次返回队列处理..." + e);
                // 回调nack接口返回队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

}
