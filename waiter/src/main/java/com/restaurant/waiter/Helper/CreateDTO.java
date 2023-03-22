package com.restaurant.waiter.Helper;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

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

    public long getTableID() {
        return tableID;
    }

    public void setTableID(long tableID) {
        this.tableID = tableID;
    }

    public String getTableGroup() {
        return tableGroup;
    }

    public void setTableGroup(String tableGroup) {
        this.tableGroup = tableGroup;
    }

    public long getMenuID() {
        return menuID;
    }

    public void setMenuID(long menuID) {
        this.menuID = menuID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte getDb() {
        return db;
    }

    public void setDb(byte db) {
        this.db = db;
    }
}
