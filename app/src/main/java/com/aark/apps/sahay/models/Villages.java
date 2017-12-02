package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Villages")
public class Villages {

    @Column(index = true)
    int server_id;

    @Column
    String name;

    public Villages() {
        super();
    }
}
