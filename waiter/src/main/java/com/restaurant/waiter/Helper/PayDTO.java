package com.restaurant.waiter.Helper;

import lombok.ToString;

import javax.persistence.SequenceGenerators;

@ToString
public class PayDTO {
    long TableID;
    String tableGroup;

    public PayDTO(long tableID, String tableGroup) {
        TableID = tableID;
        this.tableGroup = tableGroup;
    }

    public long getTableID() {
        return TableID;
    }

    public String getTableGroup() {
        return tableGroup;
    }
}
