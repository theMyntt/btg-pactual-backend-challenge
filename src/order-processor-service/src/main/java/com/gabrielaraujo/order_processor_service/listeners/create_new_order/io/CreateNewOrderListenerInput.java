package com.gabrielaraujo.order_processor_service.listeners.create_new_order.io;

import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
public class CreateNewOrderListenerInput {
    private int orderCode;
    private int clientCode;
    private List<ProductDTO> items;

    @Setter
    @Getter
    public static class ProductDTO {
        private String name;
        private BigInteger quantity;
        private BigDecimal price;
    }
}
