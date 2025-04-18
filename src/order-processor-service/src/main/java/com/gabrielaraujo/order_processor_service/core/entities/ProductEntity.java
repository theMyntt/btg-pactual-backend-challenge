package com.gabrielaraujo.order_processor_service.core.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class ProductEntity {
    private int id;
    private String name;
    private BigInteger quantity;
    private BigDecimal price;
}
