package com.aark.apps.sahay.dao;
/*
 * Created by karthik on 02/12/17.
 */

import android.content.Context;

import com.aark.apps.sahay.utilities.Constants;
import com.aark.apps.sahay.volley.APIRequest;
import com.aark.apps.sahay.volley.RequestCallback;
import com.aark.apps.sahay.volley.Urls;
import com.android.volley.Request;

import org.json.JSONObject;

public class OrdersDao extends APIRequest {

    private RequestCallback requestCallback;
    private Context context;

    public OrdersDao(Context context, RequestCallback requestCallback) {
        super(context);
        this.context = context;
        this.requestCallback = requestCallback;
    }

    public void addOrder(JSONObject jsonObject) {
        callApiPost_Patch(Urls.ORDERS, jsonObject, Request.Method.POST, new ResponseCallback() {
            @Override
            public void callback(Object response, boolean status) {
                requestCallback.onObjectRequestCallback(response, Constants.API_NEW_ORDER, status);
            }
        });
    }

}
