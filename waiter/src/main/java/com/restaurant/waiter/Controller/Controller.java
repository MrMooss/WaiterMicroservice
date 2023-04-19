package com.restaurant.waiter.Controller;

import com.restaurant.waiter.Helper.*;
import com.restaurant.waiter.Service.OrderRepository;
import com.restaurant.waiter.model.OrderTable;
import com.restaurant.waiter.model.Status;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orderTable")
@Tag(name="Order table")
public class Controller {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Mapper mapper;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderTable.class)))}),
            @ApiResponse(responseCode = "403", description = "Nincs megfelelő jogosultságod",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Lejárt token",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, átirányítás a login oldalra",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Asztal már létezik",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
            security = {
                    @SecurityRequirement(name = "waiter",scopes = {"orderTable"}),
            },
            summary = "Minden lekérdezése")

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderTable> getAll(){
        List<OrderTable> orderTables = orderRepository.findAll();

        return orderTables;
    }

    @Operation(summary = "Rendelés felvétel")
    @PostMapping(path = "/save")
    public void save(@Parameter(description = "Rendelés", required = true) @RequestBody(required = true) CreateDTO pData) throws Exception{
        OrderTable orderTable = mapper.EntityFromCreateDTO(pData);
        orderTable.setStatus(Status.IN_PROGRESS);
        orderRepository.save(orderTable);
    }

    //Vendégek tájékosztatása
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderTable.class)))}),
    })
    @Operation(summary = "Vendegek tajekoztatasa")
    @GetMapping(path = "/{tableID}")
    public List<InfDTO> informGuest(@Parameter(description = "Asztal azonosító") @PathVariable(name = "tableID") long tableID){
        List<OrderTable> orderTables = orderRepository.findByTableID(tableID);

        List<InfDTO> informDTOs = new ArrayList<>();
        orderTables.forEach(orderTable -> informDTOs.add(mapper.InfDTOFromEntity(orderTable)));

        return informDTOs;
    }

    //Rendelés módosítása
    @Operation(summary = "Rendelés módosítása")
    @PatchMapping(path = "/{id}-mod")
    public void modify(@Parameter(description = "Rendelés azonosító") @PathVariable(name = "id") long pID, @Parameter(description = "Rendelés módosítás") @RequestBody ModDTO pModDTO){
        OrderTable orderTable = orderRepository.findById(pID).get();

        orderTable.setMenuID(pModDTO.getMenuID());
        orderTable.setOrderDesc(pModDTO.getDesc());
        orderTable.setDb(pModDTO.getDb());
        orderTable.setModifiedTimeStamp(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(orderTable);
    }

    //Étel kivitele
    @Operation(summary = "Étel kihozása")
    @PatchMapping(path = "/{id}-serve")
    public void serving(@Parameter(description = "Rendelés ID") @PathVariable(name = "id") long ID){
        OrderTable orderTable = orderRepository.findById(ID).get();

        orderTable.setStatus(Status.COMPLETED);

        orderRepository.save(orderTable);
    }

    //Fizetés
    @Operation(summary = "Fizetés kérése")
    @PostMapping(path = "/pay")
    public void pay(@Parameter(description = "Fizetés") @RequestBody PayDTO pPayDTO) {
        OrderTable order = orderRepository.findByTableIDAndTableGroup(pPayDTO.getTableID(), pPayDTO.getTableGroup());
        order.setStatus(Status.PAID);

        orderRepository.save(order);
    }

}