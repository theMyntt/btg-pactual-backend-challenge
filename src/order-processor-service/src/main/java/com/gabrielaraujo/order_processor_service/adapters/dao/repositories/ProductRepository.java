package com.gabrielaraujo.order_processor_service.adapters.dao.repositories;

import com.gabrielaraujo.order_processor_service.adapters.dao.tables.ProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductTable, Integer> {
    ProductTable findByName(String name);
}
