package com.restaurant.waiter.Helper;

public class CreateDTO {

    long tableID;
    String group;
    long menuID;
    String desc;
    byte db;

    public CreateDTO(long tableID, String group, long menuID, String desc, byte db) {
        this.tableID = tableID;
        this.group = group;
        this.menuID = menuID;
        this.desc = desc;
        this.db = db;
    }
}
