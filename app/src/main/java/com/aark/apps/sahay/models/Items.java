package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Items")
public class Items extends Model {

    @Column(index = true)
    int server_id;

    @Column
    String name;

    @Column
    String name_hi;

    public Items() {
        super();
    }
}
