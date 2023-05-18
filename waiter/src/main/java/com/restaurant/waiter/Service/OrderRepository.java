package com.restaurant.waiter.Service;

import com.restaurant.waiter.model.OrderTable;
import com.restaurant.waiter.utils.logging.AspectLogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderTable, Long>{

    List<OrderTable> findByTableID(long tableID);
    OrderTable findByTableIDAndTableGroup(long tableID, String tableGroup);
    @AspectLogger
    @Override
    List<OrderTable> findAll();

}
