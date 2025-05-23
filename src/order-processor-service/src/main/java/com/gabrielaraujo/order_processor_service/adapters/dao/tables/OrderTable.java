package com.gabrielaraujo.order_processor_service.adapters.dao.tables;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_orders")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JoinColumn(name = "fk_id_order")
    private List<ProductTable> products;
}
