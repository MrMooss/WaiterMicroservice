package com.restaurant.waiter.Helper;

public class CreateDTO {

    long tableID;
    String tableGroup;
    long menuID;
    String desc;
    byte db;

    public CreateDTO(long tableID, String tableGroup, long menuID, String desc, byte db) {
        this.tableID = tableID;
        this.tableGroup = tableGroup;
        this.menuID = menuID;
        this.desc = desc;
        this.db = db;
    }
}
