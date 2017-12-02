package com.aark.apps.sahay.dao;
/*
 * Created by karthik on 02/12/17.
 */

import android.content.Context;

import com.aark.apps.sahay.models.Farmers;
import com.aark.apps.sahay.models.Items;
import com.aark.apps.sahay.models.Orders;
import com.aark.apps.sahay.models.Villages;
import com.aark.apps.sahay.utilities.Constants;
import com.aark.apps.sahay.volley.APIRequest;
import com.aark.apps.sahay.volley.RequestCallback;
import com.aark.apps.sahay.volley.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchDaoDao extends APIRequest {

    private RequestCallback requestCallback;
    private Context context;

    public FetchDaoDao(Context context, RequestCallback requestCallback) {
        super(context);
        this.context = context;
        this.requestCallback = requestCallback;
    }

    public void fetchFarmers() {
        callApiGet(Urls.FARMERS, new ResponseCallback() {
            @Override
            public void callback(Object response, boolean status) {
                JSONObject jsonObject = (JSONObject) response;
                try {
                    JSONArray object = jsonObject.getJSONArray("objects");
                    for (int i = 0; i < object.length(); ++i) {
                        JSONObject jonj = object.getJSONObject(i);
                        String aadhar = jonj.getString("aadhar_number");
                        String gender = jonj.getString("gender");
                        int server_id = jonj.getInt("server_id");
                        String name = jonj.getString("name");
                        String phone = jonj.getString("phone");
                        int village_id = jonj.getJSONObject("village").getInt("id");

                        Farmers farmers = new Farmers(server_id, name, phone, village_id, aadhar, gender);
                        farmers.save();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestCallback.onObjectRequestCallback(null, Constants.API_FETCH_FARMER, true);
            }
        });
    }

    public void fetchOrders() {
        callApiGet(Urls.ORDERS, new ResponseCallback() {
            @Override
            public void callback(Object response, boolean status) {
                JSONObject jsonObject = (JSONObject) response;
                try {
                    JSONArray objects = jsonObject.getJSONArray("objects");
                    for (int i = 0; i < objects.length(); ++i) {
                        JSONObject jobj = objects.getJSONObject(i);
                        int server_id = jobj.getInt("server_id");
                        String date = jobj.getString("date");
                        int farmer_id = jobj.getJSONObject("farmer").getInt("id");
                        int item_id = jobj.getJSONObject("item").getInt("id");
                        float advanceAmount = (float) jobj.getDouble("advance_amount");
                        int quantity = jobj.getInt("quantity");

                        Orders orders = new Orders(server_id, date, farmer_id, item_id, quantity, advanceAmount);
                        orders.save();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                requestCallback.onObjectRequestCallback(null, Constants.API_FETCH_ORDERS, true);
            }
        });
    }

    public void fetchItems() {
        callApiGet(Urls.ITEMS, new ResponseCallback() {
            @Override
            public void callback(Object response, boolean status) {
                JSONObject jsonObject = (JSONObject) response;
                try {
                    JSONArray objects = jsonObject.getJSONArray("objects");
                    for (int i = 0; i < objects.length(); ++i) {
                        JSONObject jobj = objects.getJSONObject(i);
                        int server_id = jobj.getInt("id");
                        String name = jobj.getString("item_name_en");
                        String name_hi = jobj.getString("item_name_hi");

                        Items items = new Items(server_id, name, name_hi);
                        items.save();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                requestCallback.onObjectRequestCallback(null, Constants.API_FETCH_ITEMS, true);
            }
        });
    }

    public void fetchVillages() {
        callApiGet(Urls.VILLAGES, new ResponseCallback() {
            @Override
            public void callback(Object response, boolean status) {
                JSONObject jsonObject = (JSONObject) response;
                try {
                    JSONArray objects = jsonObject.getJSONArray("objects");
                    for (int i = 0; i < objects.length(); ++i) {
                        JSONObject jobj = objects.getJSONObject(i);
                        int server_id = jobj.getInt("server_id");
                        String name = jobj.getString("village_name");

                        Villages villages = new Villages(server_id, name);
                        villages.save();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                requestCallback.onObjectRequestCallback(null, Constants.API_FETCH_VILLAGES, true);
            }
        });
    }
}
