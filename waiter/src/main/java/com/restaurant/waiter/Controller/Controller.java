package com.restaurant.waiter.Controller;

import com.restaurant.waiter.Service.CrudInterface;
import com.restaurant.waiter.db.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restaurant.waiter.db.Status;

import java.util.List;

@RestController
@RequestMapping("/order")
public class Controller {
    @Autowired
    private CrudInterface crudInterface;

    @PostMapping
    public ResponseEntity<Void> createWaiter(@RequestBody Waiter waiter) {
        waiter.setStatus(Status.PENDING);
        crudInterface.save(waiter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tell-update/{asztalID}")
    public List<Waiter> getWaitersByAsztalID(@PathVariable long asztalID) {
        return crudInterface.findByAsztalID(asztalID);
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<Void> updateWaiterStatus(@PathVariable long id, @RequestBody Waiter waiter) {
        Waiter existingWaiter = crudInterface.findByIdAndStatus(id, Status.IN_PROGRESS);
        if (existingWaiter == null) {
            return ResponseEntity.notFound().build();
        }
        existingWaiter.setMenuID(waiter.getMenuID());
        existingWaiter.setLeiras(waiter.getLeiras());
        existingWaiter.setDb(waiter.getDb());
        crudInterface.save(existingWaiter);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/bring-food/{id}")
    public ResponseEntity<Void> markWaiterAsReady(@PathVariable long id) {
        Waiter existingWaiter = crudInterface.findByIdAndStatus(id, Status.IN_PROGRESS);
        if (existingWaiter == null) {
            return ResponseEntity.notFound().build();
        }
        existingWaiter.setStatus(Status.COMPLETED);
        crudInterface.save(existingWaiter);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(@RequestBody long asztalID, String csoport) {
        List<Waiter> waiters = crudInterface.findByAsztalIDAndCsoport(
                asztalID, csoport);

        double totalPrice = waiters.stream()
                .mapToDouble(Waiter::getOsszesAr)
                .sum();

        waiters.forEach(waiter -> waiter.setStatus(Status.PAID));

        crudInterface.saveAll(waiters);

        // Return a response indicating success
        return ResponseEntity.ok(String.format("Payment successful. Total amount paid: %.2f", totalPrice));
    }
}