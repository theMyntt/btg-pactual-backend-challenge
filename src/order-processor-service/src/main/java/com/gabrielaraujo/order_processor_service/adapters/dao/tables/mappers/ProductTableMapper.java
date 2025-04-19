package com.gabrielaraujo.order_processor_service.adapters.dao.tables.mappers;

import com.gabrielaraujo.order_processor_service.adapters.dao.tables.ProductTable;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import com.gabrielaraujo.order_processor_service.core.entities.factories.ProductEntityFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductTableMapper {
    public static ProductTable toTableFormat(ProductEntity product) {
        return ProductTable.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

    public static ProductEntity toEntityFormat(ProductTable table) {
        return ProductEntityFactory.of(table.getId(), table.getName(), table.getQuantity(), table.getPrice());
    }
}
