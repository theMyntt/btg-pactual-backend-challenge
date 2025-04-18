package com.gabrielaraujo.order_processor_service.core.entities.implementations;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;

import java.util.Optional;

public class ClientEntityImpl extends ClientEntity {
    public ClientEntityImpl(String name, String description) {
        if (!description.isEmpty()) {
            this.setDescription(description);
        }
        this.setName(name.trim());

        validateEntity();
    }

    public ClientEntityImpl(int id, String name, String description) {
        if (!description.isEmpty()) {
            this.setDescription(description);
        }
        this.setName(name.trim());
        this.setId(id);

        validateEntity();
    }

    @Override
    protected void validateEntity() {
        if (this.getName().isEmpty()) {
            throw new RuntimeException("Name cant be empty");
        }
    }
}
