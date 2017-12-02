package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Villages")
public class Villages extends Model {

    @Column(index = true)
    int server_id;

    @Column
    String name;

    public Villages() {
        super();
    }

    public Villages(int server_id, String name) {
        this.server_id = server_id;
        this.name = name;
    }

    public int getServer_id() {
        return server_id;
    }

    public String getName() {
        return name;
    }

    public static List<Villages> getAllVillages() {
        return new Select().from(Villages.class).execute();
    }

    public static Villages getVillageData(int id) {
        return new Select().from(Villages.class).where("server_id=?", id).executeSingle();
    }
}
