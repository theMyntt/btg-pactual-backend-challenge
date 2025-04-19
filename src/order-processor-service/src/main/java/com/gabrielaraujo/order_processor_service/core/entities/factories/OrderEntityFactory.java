package com.gabrielaraujo.order_processor_service.core.entities.factories;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import com.gabrielaraujo.order_processor_service.core.entities.implementations.OrderEntityImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderEntityFactory {
    public static OrderEntity of(ClientEntity client, List<ProductEntity> products) {
        return new OrderEntityImpl(client, products);
    }

    public static OrderEntity of(int id, ClientEntity client, List<ProductEntity> products) {
        return new OrderEntityImpl(id, client, products);
    }

    public static OrderEntity of(int id, BigDecimal finalPrice, ClientEntity client, List<ProductEntity> products) {
        return new OrderEntityImpl(id, finalPrice, client, products);
    }
}
