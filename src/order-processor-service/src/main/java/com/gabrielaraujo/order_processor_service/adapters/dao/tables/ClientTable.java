package com.gabrielaraujo.order_processor_service.adapters.dao.tables;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_clients")
@Builder
@Data
public class ClientTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private int id;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "tx_description")
    private String description;
}
