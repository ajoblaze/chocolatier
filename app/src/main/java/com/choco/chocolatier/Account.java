package com.choco.chocolatier;

import java.util.ArrayList;

/**
 * Created by Alvin on 2016-04-02.
 */
public class Account {
    String username, fullname, password, address, phone;
    ArrayList<Transaction> transactionList;

    public Account() {
        this.username = "";
        this.fullname = "";
        this.password = "";
        this.address = "";
        this.phone = "";
        transactionList = new ArrayList<>();
    }

    public Account(String username, String fullname, String password, String address, String phone) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.address = address;
        this.phone = phone;
        transactionList = new ArrayList<>();
    }

    public void update(String username, String password, String address, String phone) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }
}
