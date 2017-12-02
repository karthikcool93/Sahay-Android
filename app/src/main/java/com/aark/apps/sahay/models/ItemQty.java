package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

public class ItemQty {
    String item;
    int qty;

    public ItemQty(String item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public String getItem() {
        return item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
