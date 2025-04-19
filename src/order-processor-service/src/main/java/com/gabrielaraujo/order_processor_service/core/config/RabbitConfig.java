package com.gabrielaraujo.order_processor_service.core.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {
    public static final String QUEUE_NAME = "process-order-queue";

    @Bean
    public Queue processOrderQueue() {
        return new Queue(QUEUE_NAME, false);
    }
}
