package com.life.rabbitmq.mq.confirm;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author tengyun
 * @date 2021/3/2 14:16
 **/
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (ack) {
            System.out.println("rabbitmq收到了   " + correlationData + "------" + s);
        } else {
            System.out.println("没收到，重传   " + correlationData + "------" + s);
        }
    }
}
