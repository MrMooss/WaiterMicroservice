package com.restaurant.waiter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
    private long id;

    @Column(name = "asztal_id")
    private long tableID;

    private String group;

    @Column(name = "menu_id")
    private long menuID;

    @Column(name = "menu_neve")
    private String menuNeve;

    private String desc;

    private byte db;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String hely;

    @Column(name = "egyseg_ar")
    private double price;

    @Column(name = "osszes_ar")
    private double sumPrice;

    @Column(name = "created_time_stamp")
    private Timestamp createdTimeStamp;

    @Column(name = "modified_time_stamp")
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