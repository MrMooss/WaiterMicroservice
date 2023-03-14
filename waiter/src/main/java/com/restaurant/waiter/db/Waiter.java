package com.restaurant.waiter.db;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Waiter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "asztal_id")
    private long asztalID;

    private String csoport;

    @Column(name = "menu_id")
    private long menuID;

    @Column(name = "menu_neve")
    private String menuNeve;

    private String leiras;

    private byte db;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String hely;

    @Column(name = "egyseg_ar")
    private double egysegAr;

    @Column(name = "osszes_ar")
    private double osszesAr;

    @Column(name = "created_time_stamp")
    private Timestamp createdTimeStamp;

    @Column(name = "modified_time_stamp")
    private Timestamp modifiedTimeStamp;
}