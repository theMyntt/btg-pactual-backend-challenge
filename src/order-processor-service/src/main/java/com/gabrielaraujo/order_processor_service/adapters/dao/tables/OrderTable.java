package com.gabrielaraujo.order_processor_service.adapters.dao.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_orders")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTable {
    @Id
    @Column(name = "id_order")
    private int id;

    @Column(name = "tx_final_price")
    private BigDecimal finalPrice;

    @ManyToOne
    @JoinColumn(name = "fk_id_client")
    private ClientTable client;

    @OneToMany
    @JoinColumn(name = "fk_id_product")
    private List<ProductTable> products;
}
