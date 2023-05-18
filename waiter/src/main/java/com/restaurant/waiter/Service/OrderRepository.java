package com.restaurant.waiter.Service;

import com.restaurant.waiter.model.OrderTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utils.logging.AspectLogger;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderTable, Long>{
    @AspectLogger
    List<OrderTable> findByTableID(long tableID);
    @AspectLogger
    OrderTable findByTableIDAndTableGroup(long tableID, String tableGroup);
    @AspectLogger
    @Override
    List<OrderTable> findAll();

}
