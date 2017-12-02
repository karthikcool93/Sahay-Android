package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Farmers")
public class Farmers extends Model {
    @Column(index = true)
    int server_id;

    @Column
    String name;

    @Column
    String phone;

    @Column
    int village;

    @Column
    String aadharNo;

    @Column
    String gender;

    public Farmers() {
        super();
    }

    public Farmers(int server_id, String name, String phone, int village, String aadharNo, String gender) {
        this.server_id = server_id;
        this.name = name;
        this.phone = phone;
        this.village = village;
        this.aadharNo = aadharNo;
        this.gender = gender;
    }

    public int getServer_id() {
        return server_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getVillage() {
        return village;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public String getGender() {
        return gender;
    }

    public static List<Farmers> getAllFarmers() {
        return new Select().from(Farmers.class).execute();
    }

}
