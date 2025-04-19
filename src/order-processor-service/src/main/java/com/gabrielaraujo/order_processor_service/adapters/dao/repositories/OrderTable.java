package com.gabrielaraujo.order_processor_service.adapters.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTable extends JpaRepository<OrderTable, Integer> {
}
