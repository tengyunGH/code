package com.life.rabbitmq.mq.delay;

import com.life.common.dto.JsonResponse;
import com.life.rabbitmq.mq.entity.Student;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonUtil;

import javax.annotation.Resource;

/**
 * @author tengyun
 * @date 2021/3/8 9:08
 **/
@RestController
public class DelayTestProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test/delay")
    public JsonResponse<String> producerDirect() {
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend("quartz.demo.exchange.delay.business",
                "quartz.demo.businessA",
                JsonUtil.javaBeanToString(new Student((long)i, "tengyun", 26, 2)),
                message -> {
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setExpiration("6000");
                    return message;
                });
        }
        for (int i = 5; i < 9; i++) {
            rabbitTemplate.convertAndSend("quartz.demo.exchange.delay.business",
                "quartz.demo.businessA",
                JsonUtil.javaBeanToString(new Student((long)i, "tengyun", 26, 2)),
                message -> {
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setExpiration("1000");
                    return message;
                });
        }
        return JsonResponse.success();
    }
}
