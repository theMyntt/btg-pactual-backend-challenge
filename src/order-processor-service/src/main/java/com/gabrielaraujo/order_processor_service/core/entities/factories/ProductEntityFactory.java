package com.gabrielaraujo.order_processor_service.core.entities.factories;

import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import com.gabrielaraujo.order_processor_service.core.entities.implementations.ProductEntityImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductEntityFactory {
    public static ProductEntity of(String name, BigInteger quantity, BigDecimal price) {
        return new ProductEntityImpl(name, quantity, price);
    }

    public static ProductEntity of(int id, String name, BigInteger quantity, BigDecimal price) {
        return new ProductEntityImpl(id, name, quantity, price);
    }
}
