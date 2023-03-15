package com.restaurant.waiter.Helper;

import com.restaurant.waiter.model.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Mapper {

    public Order EntityFromCreateDTO(CreateDTO dto) {
        Order order = new Order(dto.tableID, dto.group, dto.menuID, dto.desc, dto.db);
        order.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));

        return order;
    }
}
