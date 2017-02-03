package com.teamtreehouse.instateam;

/**
 * Created by GoranB on 2017-02-01.
 */
public enum Status {
    NOT_STARTED("Not started"),
    ACTIVE("Active"),
    ARCHIVED("Archived");

    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
