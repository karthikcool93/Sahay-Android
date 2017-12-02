package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

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

    public Items(int server_id, String name, String name_hi) {
        this.server_id = server_id;
        this.name = name;
        this.name_hi = name_hi;
    }

    public int getServer_id() {
        return server_id;
    }

    public String getName() {
        return name;
    }

    public String getName_hi() {
        return name_hi;
    }

    public static List<Items> getAllItems() {
        return new Select().from(Items.class).execute();
    }
}
