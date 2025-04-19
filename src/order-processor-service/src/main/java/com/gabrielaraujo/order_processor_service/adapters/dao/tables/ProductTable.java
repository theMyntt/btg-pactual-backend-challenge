package com.gabrielaraujo.order_processor_service.adapters.dao.tables;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "tb_products")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_product")
    private int id;

    @Column(name = "tx_name", nullable = false)
    private String name;

    @Column(name = "tx_quantity", nullable = false)
    private BigInteger quantity;

    @Column(name = "tx_price", nullable = false)
    private BigDecimal price;
}
