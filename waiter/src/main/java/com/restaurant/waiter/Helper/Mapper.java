package com.restaurant.waiter.Helper;

import com.restaurant.waiter.model.OrderTable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Mapper {

    public OrderTable EntityFromCreateDTO(CreateDTO dto) {
        OrderTable orderTable = new OrderTable(dto.tableID, dto.tableGroup, dto.menuID, dto.desc, dto.db);
        orderTable.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        orderTable.setPrice(1);
        orderTable.setSumPrice(1);
        orderTable.setPlace("kitchen");

        return orderTable;
    }

    public InfDTO InfDTOFromEntity(OrderTable orderTable) {
        InfDTO inform = new InfDTO(orderTable.getTableGroup(), orderTable.getMenuID(), orderTable.getOrderDesc(),
                orderTable.getDb(), orderTable.getStatus(), orderTable.getId(), orderTable.getPrice(), orderTable.getSumPrice());

        return inform;
    }
}
