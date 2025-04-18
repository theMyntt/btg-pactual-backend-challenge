package com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.io;

import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateNewOrderUseCaseInput {
    private int orderCode;
    private int clientCode;
    private List<ProductEntity> items;
}
