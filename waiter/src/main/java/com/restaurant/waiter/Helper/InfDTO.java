package com.restaurant.waiter.Helper;

import com.restaurant.waiter.model.Status;
import lombok.ToString;

@ToString
public class InfDTO {
    String tableGroup;
    long menuID;
    String desc;
    byte db;
    Status state;
    long id;
    double price;

    double sumPrice;

    public InfDTO(String tableGroup, long menuID, String desc, byte db, Status state, long id, double price, double sumPrice) {
        this.tableGroup = tableGroup;
        this.menuID = menuID;
        this.desc = desc;
        this.db = db;
        this.state = state;
        this.id = id;
        this.price = price;
        this.sumPrice = sumPrice;
    }

    public String getTableGroup() {
        return tableGroup;
    }

    public long getMenuID() {
        return menuID;
    }

    public String getDesc() {
        return desc;
    }

    public byte getDb() {
        return db;
    }

    public Status getState() {
        return state;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getSumPrice() {
        return sumPrice;
    }
}
