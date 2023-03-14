package com.restaurant.waiter.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.waiter.db.Waiter;
import com.restaurant.waiter.db.Status;

import java.util.List;

public interface CrudInterface extends JpaRepository<Waiter, Long>{
    List<Waiter> findByAsztalID(long asztalID);
    Waiter findByIdAndStatus(long id, Status status);
    List<Waiter> findByAsztalIDAndCsoport(long asztalID, String Csoport);
}
