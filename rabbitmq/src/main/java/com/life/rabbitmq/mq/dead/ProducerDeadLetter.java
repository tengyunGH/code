package com.life.rabbitmq.mq.dead;

import com.life.common.dto.JsonResponse;
import com.life.rabbitmq.mq.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonUtil;

import javax.annotation.Resource;

/**
 * @author tengyun
 * @date 2021/3/5 19:51
 **/
@RestController
public class ProducerDeadLetter {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test/dead/letter1")
    public JsonResponse<String> producerHeader2() {
        for (int i = 0; i < 6; i++) {
            rabbitTemplate.convertAndSend("quartz.demo.exchange.business", "businessB.test",
                JsonUtil.javaBeanToString(new Student((long) i, "ty", 20, 2)));
        }
        return JsonResponse.success();
    }

}
