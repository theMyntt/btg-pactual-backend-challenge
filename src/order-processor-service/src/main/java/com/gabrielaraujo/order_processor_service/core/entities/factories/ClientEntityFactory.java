package com.gabrielaraujo.order_processor_service.core.entities.factories;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.implementations.ClientEntityImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientEntityFactory {
    public static ClientEntity of(String name, String description) {
        return new ClientEntityImpl(name, description);
    }

    public static ClientEntity of(int id, String name, String description) {
        return new ClientEntityImpl(id, name, description);
    }
}
