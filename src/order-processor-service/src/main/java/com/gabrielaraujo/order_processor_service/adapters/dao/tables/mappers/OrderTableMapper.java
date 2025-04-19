package com.gabrielaraujo.order_processor_service.adapters.dao.tables.mappers;

import com.gabrielaraujo.order_processor_service.adapters.dao.tables.OrderTable;
import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.factories.OrderEntityFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderTableMapper {
    public static OrderTable toTableFormat(OrderEntity order) {
        return OrderTable.builder()
                .client(ClientTableMapper.toTableFormat(order.getClient()))
                .products(order.getItems().stream()
                        .map(ProductTableMapper::toTableFormat)
                        .toList())
                .finalPrice(order.getFinalPrice())
                .build();
    }

    public static OrderEntity toEntityFormat(OrderTable order) {
        return OrderEntityFactory.of(order.getId(),
                ClientTableMapper.toEntityFormat(order.getClient()),
                order.getProducts().stream()
                        .map(ProductTableMapper::toEntityFormat)
                        .toList());
    }
}
