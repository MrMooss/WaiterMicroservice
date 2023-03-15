package com.restaurant.waiter.Controller;

import com.restaurant.waiter.Helper.CreateDTO;
import com.restaurant.waiter.Helper.Mapper;
import com.restaurant.waiter.Service.OrderRepository;
import com.restaurant.waiter.model.Order;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class Controller {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Mapper mapper;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres rendelés"),
            @ApiResponse(responseCode = "500", description = "Rendelés nem lehetséges")
    })

    @Operation(summary = "Rendelés felvétel")
    @PostMapping(path = "/save")
    public void save(@Parameter(description = "Rendelés", required = true) @RequestBody(required = true) CreateDTO pData) throws Exception{
        Order order = mapper.EntityFromCreateDTO(pData);
        orderRepository.save(order);
    }
}