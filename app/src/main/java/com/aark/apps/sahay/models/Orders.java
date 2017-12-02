package com.aark.apps.sahay.models;
/*
 * Created by karthik on 02/12/17.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Table(name = "Orders")
public class Orders extends Model {

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
    float advancePayment;

    public Orders() {
        super();
    }

    public Orders(int server_id, String date, int farmer_id, int item_id, int qty, float advancePayment) {
        this.server_id = server_id;
        this.date = date;
        this.farmer_id = farmer_id;
        this.item_id = item_id;
        this.qty = qty;
        this.advancePayment = advancePayment;
    }

    public Orders(JSONObject jobj) throws JSONException {
        this.server_id = jobj.getInt("server_id");
        this.date = jobj.getString("date");
        this.farmer_id = jobj.getJSONObject("farmer").getInt("id");
        this.item_id = jobj.getJSONObject("item").getInt("id");
        this.advancePayment = (float) jobj.getDouble("advance_amount");
        this.qty = jobj.getInt("quantity");
    }
}
