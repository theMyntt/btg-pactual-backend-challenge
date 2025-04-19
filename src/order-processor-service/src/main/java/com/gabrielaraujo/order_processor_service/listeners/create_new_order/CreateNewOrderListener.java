package com.gabrielaraujo.order_processor_service.listeners.create_new_order;

import com.gabrielaraujo.order_processor_service.core.config.RabbitConfig;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.CreateNewOrderUseCase;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.io.CreateNewOrderUseCaseInput;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CreateNewOrderListener {

    private final CreateNewOrderUseCase useCase;

    public CreateNewOrderListener(CreateNewOrderUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void consumeMessage(CreateNewOrderUseCaseInput message) {
        useCase.execute(message);
    }
}
