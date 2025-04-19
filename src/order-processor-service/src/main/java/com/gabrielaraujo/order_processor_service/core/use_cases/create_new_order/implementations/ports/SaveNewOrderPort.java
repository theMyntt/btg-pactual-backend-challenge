package com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.implementations.ports;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

public interface SaveNewOrderPort {
    SaveNewOrderPortOutput execute(SaveNewOrderPortInput input);

    @Getter
    @Builder
    class SaveNewOrderPortInput {
        private int orderCode;
        private BigDecimal finalPrice;
        private ClientEntity client;
        private List<ProductEntity> products;
    }

    @Getter
    @Builder
    class SaveNewOrderPortOutput {
        private OrderEntity savedOrder;
    }
}
