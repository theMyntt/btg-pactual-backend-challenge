package com.gabrielaraujo.order_processor_service.core.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
public abstract class OrderEntity {
    private int id;
    private ClientEntity client;
    private List<ProductEntity> items;

    protected abstract void validateEntity();
}
