package com.choco.chocolatier;

/**
 * Created by Alvin on 2016-04-03.
 */
public class Transaction {
    Choco choco;
    String qty;

    public Transaction(Choco choco, String qty) {
        this.choco = choco;
        this.qty = qty;
    }

    public Choco getChoco() {
        return choco;
    }

    public String getQty() {
        return qty;
    }
}
