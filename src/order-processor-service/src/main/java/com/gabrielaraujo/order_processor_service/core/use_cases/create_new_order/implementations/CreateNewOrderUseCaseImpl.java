package com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.implementations;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import com.gabrielaraujo.order_processor_service.core.entities.factories.ClientEntityFactory;
import com.gabrielaraujo.order_processor_service.core.entities.factories.OrderEntityFactory;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.CreateNewOrderUseCase;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.implementations.ports.SaveNewOrderPort;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.io.CreateNewOrderUseCaseInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        var client = extractClientFrom(input);
        var order = saveOrderOnDatabase(client, input.getItems());

        log.info("Execution finished for: {}", order.toString());
    }

    private ClientEntity extractClientFrom(CreateNewOrderUseCaseInput input) {
        return ClientEntityFactory.of(input.getClientCode(), null, null);
    }

    private OrderEntity saveOrderOnDatabase(ClientEntity client, List<ProductEntity> products) {
        var portInput = SaveNewOrderPort.SaveNewOrderPortInput.builder()
                .client(client)
                .products(products)
                .build();
        var portOutput = saveNewOrderPort.execute(portInput);
        return portOutput.getSavedOrder();
    }
}
