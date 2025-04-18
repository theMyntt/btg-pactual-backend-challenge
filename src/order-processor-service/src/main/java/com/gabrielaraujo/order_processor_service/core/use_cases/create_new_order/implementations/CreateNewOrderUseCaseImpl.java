package com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.implementations;

import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.factories.ClientEntityFactory;
import com.gabrielaraujo.order_processor_service.core.entities.factories.OrderEntityFactory;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.CreateNewOrderUseCase;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.implementations.ports.SaveNewOrderPort;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.io.CreateNewOrderUseCaseInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateNewOrderUseCaseImpl extends CreateNewOrderUseCase {
    private final SaveNewOrderPort saveNewOrderPort;

    public CreateNewOrderUseCaseImpl(SaveNewOrderPort saveNewOrderPort) {
        this.saveNewOrderPort = saveNewOrderPort;
    }

    @Override
    public void execute(CreateNewOrderUseCaseInput input) {
        applyInternalLogic(input);
    }

    @Override
    protected void applyInternalLogic(CreateNewOrderUseCaseInput input) {
        var order = extractOrderFrom(input);
        order = saveOrderOnDatabase(order);

        log.info("Saved order: {}", order.toString());
    }

    private OrderEntity extractOrderFrom(CreateNewOrderUseCaseInput input) {
        var clientEntity = ClientEntityFactory.of(input.getClientCode(), null, null);
        return OrderEntityFactory.of(input.getOrderCode(), clientEntity, input.getItems());
    }

    private OrderEntity saveOrderOnDatabase(OrderEntity order) {
        var portInput = SaveNewOrderPort.SaveNewOrderPortInput.builder()
                .orderToSave(order)
                .build();
        var portOutput = saveNewOrderPort.execute(portInput);
        return portOutput.getSavedOrder();
    }
}
