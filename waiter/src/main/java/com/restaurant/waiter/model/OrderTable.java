package com.restaurant.waiter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Table(name = "ordertable")
@Schema(description = "Asztal")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(description = "Azonosító")
    private long id;

    @Column(name = "tableid")
    @NotNull(message = "error.order.tableID.null")
    @Schema(description = "Asztal azonosítója")
    private long tableID;

    @NotBlank(message = "error.order.tableGroup.notset")
    @Schema(description = "Csoport")
    private String tableGroup;

    @Column(name = "menuid")
    @Schema(description = "Étel azonosítója a menün")
    private long menuID;

    @Column(name = "menu_name")
    @Schema(description = "Étel megnevezése a menüben")
    private String menuNeve;

    @Column(name = "orderDesc")
    @Schema(description = "Leírás")
    private String orderDesc;

    @Min(value = 1, message = "error.order.quantity.min")
    @Schema(description = "Mennyiség")
    private byte db;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Státusz")
    private Status status;

    @Schema(description = "Elkészítés helye")
    private String place;

    @DecimalMin(value = "0.01", message = "error.order.price.min")
    @Column(name = "price")
    @Schema(description = "Egységár")
    private double price;

    @DecimalMin(value = "0.01", message = "error.order.sumprice.min")
    @Column(name = "pricesum")
    @Schema(description = "Ár összesen")
    private double sumPrice;

    @Column(name = "created_time_stamp")
    @Schema(description = "Létrehozás időbélyeg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Timestamp createdTimeStamp;

    @Column(name = "modified_time_stamp")
    @Schema(description = "Módosítás időbélyeg")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Timestamp modifiedTimeStamp;

    @Builder
    public OrderTable(long tableID, String tableGroup, long menuID, String orderDesc, byte db) {
        this.tableID = tableID;
        this.tableGroup = tableGroup;
        this.menuID = menuID;
        this.orderDesc = orderDesc;
        this.db = db;
    }
}