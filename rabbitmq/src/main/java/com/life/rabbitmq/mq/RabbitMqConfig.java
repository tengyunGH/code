package com.life.rabbitmq.mq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tengyun
 * @date 2021/3/2 14:43
 **/
@Configuration
public class RabbitMqConfig {

    @Bean
    public ConfirmCallbackService confirmCallbackService() {
        return new ConfirmCallbackService();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(@Autowired CachingConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setConfirmCallback(confirmCallbackService());
        return rabbitTemplate;
    }

}
