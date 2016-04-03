package com.choco.chocolatier;

/**
 * Created by Alvin on 2016-04-02.
 */
public class Choco {
    String name, desc, price;

    public Choco(String name, String desc, String price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }
}
