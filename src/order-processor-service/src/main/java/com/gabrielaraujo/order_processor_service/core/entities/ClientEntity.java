package com.gabrielaraujo.order_processor_service.core.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
public abstract class ClientEntity {
    private int id;
    private String name;
    private String description;

    protected abstract void validateEntity();
}
