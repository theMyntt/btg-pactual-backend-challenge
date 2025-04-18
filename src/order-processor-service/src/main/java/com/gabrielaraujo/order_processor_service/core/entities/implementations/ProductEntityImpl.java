package com.gabrielaraujo.order_processor_service.core.entities.implementations;

import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductEntityImpl extends ProductEntity {
    public ProductEntityImpl(String name, BigInteger quantity, BigDecimal price) {
        this.setName(name);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public ProductEntityImpl(int id, String name, BigInteger quantity, BigDecimal price) {
        this.setId(id);
        this.setName(name);
        this.setQuantity(quantity);
        this.setPrice(price);
    }
}
