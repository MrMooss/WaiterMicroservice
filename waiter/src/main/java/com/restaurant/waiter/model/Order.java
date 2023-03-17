package com.restaurant.waiter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Schema(description = "Asztal")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(description = "Azonosító")
    private long id;

    @Column(name = "tableid")
    @NotNull(message = "error.order.tableID.null")
    @Schema(description = "Asztal azonosítója")
    private long tableID;

    @NotBlank(message = "error.order.group.notset")
    @Min(value = 20, message = "error.order.group.min")
    @Schema(description = "Csoport")
    private String group;

    @Column(name = "menuid")
    @Schema(description = "Étel azonosítója a menün")
    private long menuID;

    @Column(name = "menu_name")
    @Schema(description = "Étel megnevezése a menüben")
    private String menuNeve;

    @Schema(description = "Leírás")
    private String desc;

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
    @Column(name = "priceSum")
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
    public Order(long tableID, String group, long menuID, String desc, byte db) {
        this.tableID = tableID;
        this.group = group;
        this.menuID = menuID;
        this.desc = desc;
        this.db = db;
    }
}