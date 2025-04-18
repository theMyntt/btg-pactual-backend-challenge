package com.gabrielaraujo.order_processor_service.core.entities.implementations;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;

import java.util.List;

public class OrderEntityImpl extends OrderEntity {
    public OrderEntityImpl(ClientEntity client, List<ProductEntity> products) {
        this.setClient(client);
        this.setItems(products);

        validateEntity();
    }

    public OrderEntityImpl(int id, ClientEntity client, List<ProductEntity> products) {
        this.setId(id);
        this.setClient(client);
        this.setItems(products);

        validateEntity();
    }

    @Override
    protected void validateEntity() {
        if(this.getClient() == null) {
            throw new RuntimeException("Client cant be null");
        }
    }
}
