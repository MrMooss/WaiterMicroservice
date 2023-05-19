package com.restaurant.waiter.Helper;

import lombok.ToString;

@ToString
public class ModDTO {

    long MenuID;
    String desc;
    byte db;

    public ModDTO(long menuID, String desc, byte db) {
        MenuID = menuID;
        this.desc = desc;
        this.db = db;
    }

    public long getMenuID() {
        return MenuID;
    }

    public String getDesc() {
        return desc;
    }

    public byte getDb() {
        return db;
    }
}
