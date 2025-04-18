package com.gabrielaraujo.order_processor_service.core.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class ClientEntity {
    private int id;
    private String name;
    private String description;

    protected abstract void validateEntity();
}
