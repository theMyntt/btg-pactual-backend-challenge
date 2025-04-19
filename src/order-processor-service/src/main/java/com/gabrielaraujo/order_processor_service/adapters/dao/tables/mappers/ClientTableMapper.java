package com.gabrielaraujo.order_processor_service.adapters.dao.tables.mappers;

import com.gabrielaraujo.order_processor_service.adapters.dao.tables.ClientTable;
import com.gabrielaraujo.order_processor_service.core.entities.ClientEntity;
import com.gabrielaraujo.order_processor_service.core.entities.factories.ClientEntityFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientTableMapper {
    public static ClientEntity toEntityFormat(ClientTable table) {
        return ClientEntityFactory.of(table.getId(), table.getName(), table.getDescription());
    }

    public static ClientTable toTableFormat(ClientEntity entity) {
        return ClientTable.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
