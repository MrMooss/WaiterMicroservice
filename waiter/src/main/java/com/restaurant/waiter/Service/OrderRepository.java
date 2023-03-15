package com.restaurant.waiter.Service;

import com.restaurant.waiter.model.Order;
import org.springframework.data.repository.CrudRepository;
import com.restaurant.waiter.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findByAsztalID(long asztalID);
    Order findByIdAndStatus(long id, Status status);
    List<Order> findByAsztalIDAndCsoport(long asztalID, String Csoport);
}
