package com.gabrielaraujo.order_processor_service.core.entities.implementations;

import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderEntityImpl extends OrderEntity {
    public OrderEntityImpl(ClientEntity client, List<ProductEntity> products) {
        this.setClient(client);
        this.setItems(products);

        validateEntity();
        calculateFinalPrice();
    }

    public OrderEntityImpl(int id, ClientEntity client, List<ProductEntity> products) {
        this.setId(id);
        this.setClient(client);
        this.setItems(products);

        validateEntity();
        calculateFinalPrice();
    }

    public OrderEntityImpl(int id, BigDecimal finalPrice, ClientEntity client, List<ProductEntity> products) {
        this.setId(id);
        this.setClient(client);
        this.setItems(products);
        this.setFinalPrice(finalPrice);

        validateEntity();
    }

    @Override
    protected void validateEntity() {
        if(this.getClient() == null) {
            throw new RuntimeException("Client cant be null");
        }
    }

    @Override
    protected void calculateFinalPrice() {
        var products = this.getItems();

        if (products.isEmpty())
            return;

        var finalPrice = products.stream()
                .map(p -> p.getPrice().multiply(new BigDecimal(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.setFinalPrice(finalPrice);
    }
}
