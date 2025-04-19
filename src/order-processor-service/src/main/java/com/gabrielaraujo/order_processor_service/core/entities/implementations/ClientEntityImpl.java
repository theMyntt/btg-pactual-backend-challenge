package com.gabrielaraujo.order_processor_service.core.entities.implementations;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;

import java.util.Optional;

public class ClientEntityImpl extends ClientEntity {
    public ClientEntityImpl(String name, String description) {
        if (description != null) {
            this.setDescription(description);
        }
        this.setName(name.trim());
    }

    public ClientEntityImpl(int id, String name, String description) {
        if (description != null) {
            this.setDescription(description);
        }
        if (name != null) {
            this.setName(name.trim());
        }
        this.setId(id);
    }
}
