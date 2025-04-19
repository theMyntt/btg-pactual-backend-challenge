package com.gabrielaraujo.order_processor_service.adapters.dao.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_clients")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientTable {
    @Id
    @Column(name = "id_client")
    private Integer id;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "tx_description")
    private String description;
}
