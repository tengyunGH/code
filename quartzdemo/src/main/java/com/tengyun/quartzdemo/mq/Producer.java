package com.tengyun.quartzdemo.mq;

import com.life.common.dto.JsonResponse;
import com.tengyun.quartzdemo.entity.Student;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/3/1 13:56
 **/
@RestController
public class Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test/direct")
    public JsonResponse<String> producerDirect() {
        rabbitTemplate.convertAndSend("quartz.demo.exchange.direct", "quartz.demo.direct", new Student("tengyun", 26, 2));
        return JsonResponse.success();
    }

    @GetMapping("/test/fanout1")
    public JsonResponse<String> producerFanout1() {
        rabbitTemplate.convertAndSend("quartz.demo.exchange.fanout", "", new Student("tengyu1", 27, 2));
        return JsonResponse.success();
    }

    @GetMapping("/test/fanout2")
    public JsonResponse<String> producerFanout2() {
        rabbitTemplate.convertAndSend("quartz.demo.exchange.fanout", "", new Student("tengyu2", 28, 2));
        return JsonResponse.success();
    }

    @GetMapping("/test/fanout3")
    public JsonResponse<String> producerFanout3() {
        rabbitTemplate.convertAndSend("quartz.demo.exchange.fanout", "", new Student("tengyu3", 29, 2));
        return JsonResponse.success();
    }



    @GetMapping("/test/header1")
    public JsonResponse<String> producerHeader1() {
        rabbitTemplate.convertAndSend("quartz.demo.exchange.header", "", new Student("ty", 20, 2), message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setHeader("header1", "1");
            properties.setHeader("header2", "2");
            return message;
        });
        return JsonResponse.success();
    }

    @GetMapping("/test/header2")
    public JsonResponse<String> producerHeader2() {
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend("quartz.demo.exchange.header", "", new Student("ty", 20, 2), message -> {
                MessageProperties properties = message.getMessageProperties();
                // 设置消息的持久化
                properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                properties.setHeader("header2", "2");
                return message;
            });
        }
        return JsonResponse.success();
    }


}
