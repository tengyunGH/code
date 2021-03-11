package com.life.rabbitmq.mq.delay;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tengyun
 * @date 2021/3/8 11:13
 **/
@Configuration
public class RabbitMqDelayConfig {
    /**
     * 延时队列
     **/
    @Bean("delayBusinessQueue")
    public Queue createBusinessQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", "quartz.demo.exchange.delay.dead.letter");
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", "routing.key.delay.dead.letter");
        // x-message-ttl  声明队列的TTL
        // args.put("x-message-ttl", 6000);
        return QueueBuilder.durable("delay.business.queue").withArguments(args).build();
    }

    /**
     * 延时交换机
     **/
    @Bean("delayBusinessExchange")
    public Exchange createBusinessExchange() {
        return new DirectExchange("quartz.demo.exchange.delay.dead.letter");
    }

}
