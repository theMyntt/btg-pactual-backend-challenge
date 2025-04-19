package com.gabrielaraujo.order_processor_service.listeners.create_new_order.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
public class CreateNewOrderListenerInput {
    @JsonProperty("codigoPedido")
    private int orderCode;

    @JsonProperty("codigoCliente")
    private int clientCode;

    @JsonProperty("itens")
    private List<ProductDTO> items;

    @Setter
    @Getter
    public static class ProductDTO {
        @JsonProperty("produto")
        private String name;

        @JsonProperty("quantidade")
        private BigInteger quantity;

        @JsonProperty("preco")
        private BigDecimal price;
    }
}
