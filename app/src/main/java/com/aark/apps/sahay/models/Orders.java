package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Orders")
public class Orders {

    @Column(index = true)
    int server_id;

    @Column
    String date;

    @Column
    int farmer_id;

    @Column
    int item_id;

    @Column
    int qty;

    @Column
    int advancePayment;

    public Orders() {
        super();
    }
}
