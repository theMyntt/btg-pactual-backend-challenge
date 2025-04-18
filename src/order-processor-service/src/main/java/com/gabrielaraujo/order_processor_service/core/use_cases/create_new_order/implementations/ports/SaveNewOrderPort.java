package com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.implementations.ports;

import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import lombok.Builder;
import lombok.Getter;

public interface SaveNewOrderPort {
    SaveNewOrderPortOutput execute(SaveNewOrderPortInput input);

    @Getter
    @Builder
    class SaveNewOrderPortInput {
        private OrderEntity orderToSave;
    }

    @Getter
    @Builder
    class SaveNewOrderPortOutput {
        private OrderEntity savedOrder;
    }
}
