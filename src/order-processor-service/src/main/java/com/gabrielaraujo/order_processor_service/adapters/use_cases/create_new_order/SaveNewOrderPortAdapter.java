package com.gabrielaraujo.order_processor_service.adapters.use_cases.create_new_order;

import com.gabrielaraujo.order_processor_service.adapters.dao.repositories.ClientRepository;
import com.gabrielaraujo.order_processor_service.adapters.dao.repositories.OrderRepository;
import com.gabrielaraujo.order_processor_service.adapters.dao.repositories.ProductRepository;
import com.gabrielaraujo.order_processor_service.adapters.dao.tables.ClientTable;
import com.gabrielaraujo.order_processor_service.adapters.dao.tables.OrderTable;
import com.gabrielaraujo.order_processor_service.adapters.dao.tables.ProductTable;
import com.gabrielaraujo.order_processor_service.adapters.dao.tables.mappers.ClientTableMapper;
import com.gabrielaraujo.order_processor_service.adapters.dao.tables.mappers.OrderTableMapper;
import com.gabrielaraujo.order_processor_service.adapters.dao.tables.mappers.ProductTableMapper;
import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.OrderEntity;
import com.gabrielaraujo.order_processor_service.core.entities.ProductEntity;
import com.gabrielaraujo.order_processor_service.core.entities.factories.OrderEntityFactory;
import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.implementations.ports.SaveNewOrderPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SaveNewOrderPortAdapter implements SaveNewOrderPort {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public SaveNewOrderPortAdapter(OrderRepository orderRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public SaveNewOrderPortOutput execute(SaveNewOrderPortInput input) {
        var user = createOrFindClientFrom(input);
        var products = createOrFindProductsFrom(input);
        var order = createOrderFrom(user, products);

        orderRepository.save(order);
        log.info("Saved order: {}", order);

        return SaveNewOrderPortOutput.builder()
                .savedOrder(OrderTableMapper.toEntityFormat(order))
                .build();
    }

    private OrderTable createOrderFrom(ClientTable client, List<ProductTable> products) {
        return OrderTable.builder()
                .products(products)
                .client(client)
                .build();
    }

    private List<ProductTable> createOrFindProductsFrom(SaveNewOrderPortInput input) {
        List<ProductTable> products = new ArrayList<>();

        for (ProductEntity item : input.getProducts()) {
            var product = productRepository.findByName(item.getName());

            if (product == null) {
                product = ProductTableMapper.toTableFormat(item);
                productRepository.save(product);
            }

            products.add(product);
        }

        return products;
    }

    private ClientTable createOrFindClientFrom(SaveNewOrderPortInput input) {
        var client = input.getClient();
        var savedClient = clientRepository.findById(client.getId());

        return savedClient
                .orElseGet(() -> createClientFrom(client));
    }

    private ClientTable createClientFrom(ClientEntity entity) {
        var client = ClientTableMapper.toTableFormat(entity);
        client = clientRepository.save(client);

        return client;
    }
}
