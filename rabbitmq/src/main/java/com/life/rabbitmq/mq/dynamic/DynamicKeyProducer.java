package com.life.rabbitmq.mq.dynamic;

import com.life.common.dto.JsonResponse;
import com.life.rabbitmq.mq.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonUtil;

import javax.annotation.Resource;

/**
 * @author tengyun
 * @date 2021/3/6 14:21
 **/
@RestController
public class DynamicKeyProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test/sequence/1")
    public JsonResponse<String> producerSequence1() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("quartz.demo.exchange.sequence", "2", new Student((long) i, "ty", 20, 2));
        }
        return JsonResponse.success();
    }

    @GetMapping("/test/sequence/2")
    public JsonResponse<String> producerSequence2() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("quartz.demo.exchange.sequence", "1", new Student((long) i, "ty", 20, 2));
        }
        return JsonResponse.success();
    }

    @GetMapping("/test/sequence/3")
    public JsonResponse<String> producerSequence3() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("quartz.demo.exchange.sequence", "sequence1", JsonUtil.javaBeanToString(new Student((long) i, "ty", 20, 2)));
        }
        return JsonResponse.success();
    }

}
