package com.gabrielaraujo.order_processor_service.listeners.create_new_order;

import com.gabrielaraujo.order_processor_service.core.config.RabbitConfig;
import com.gabrielaraujo.order_processor_service.core.entities.factories.ProductEntityFactory;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.CreateNewOrderUseCase;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.io.CreateNewOrderUseCaseInput;
import com.gabrielaraujo.order_processor_service.listeners.create_new_order.io.CreateNewOrderListenerInput;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CreateNewOrderListener {

    private final CreateNewOrderUseCase useCase;

    public CreateNewOrderListener(CreateNewOrderUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void consumeMessage(CreateNewOrderListenerInput message) {
        var usecaseInput = CreateNewOrderUseCaseInput.builder()
                .orderCode(message.getOrderCode())
                .items(message.getItems().stream()
                        .map(v -> ProductEntityFactory.of(v.getName(), v.getQuantity(), v.getPrice()))
                        .toList())
                .clientCode(message.getClientCode())
                .build();

        useCase.execute(usecaseInput);
    }
}
