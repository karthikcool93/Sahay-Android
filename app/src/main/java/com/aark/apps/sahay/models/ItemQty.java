package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

public class ItemQty {
    String item;
    int itemId;
    int qty;

    public ItemQty(String item, int qty, int itemId) {
        this.item = item;
        this.qty = qty;
        this.itemId = itemId;
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

    public int getItemId() {
        return itemId;
    }
}
