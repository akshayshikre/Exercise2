package com.example.dadasaheb.exercise2;

/**
 * Created by dadasaheb on 6/12/17.
 */

public class row {

    String name,price,volume,change;
    boolean up;

    public row(String name, String price, String volume, String change, boolean up) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.change = change;
        this.up = up;
    }
}
