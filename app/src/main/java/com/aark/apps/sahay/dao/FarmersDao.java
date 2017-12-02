package com.aark.apps.sahay.dao;
/*
 * Created by karthik on 02/12/17.
 */

import android.content.Context;

import com.aark.apps.sahay.utilities.Constants;
import com.aark.apps.sahay.utilities.SharedPreference;
import com.aark.apps.sahay.volley.APIRequest;
import com.aark.apps.sahay.volley.RequestCallback;
import com.aark.apps.sahay.volley.Urls;
import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FarmersDao extends APIRequest {

    private RequestCallback requestCallback;
    private Context context;

    public FarmersDao(Context context, RequestCallback requestCallback) {
        super(context);
        this.context = context;
        this.requestCallback = requestCallback;
    }

    public void authenticateUser(String username, String password) {

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        callAPI(Request.Method.POST, Urls.LOGIN, map, new ResponseCallback() {
            @Override
            public void callback(Object response, boolean status) {
                if (status) {
                    try {
                        SharedPreference sharedPreference = new SharedPreference(context);
                        JSONObject jsonObject = new JSONObject((String) response);
                        if (jsonObject.getString("phone_number") != null)
                            sharedPreference.setKeyValue(SharedPreference.PHONE_NUMBER, jsonObject.getString("phone_number"));

                        if (jsonObject.getString("user_id") != null)
                            sharedPreference.setKeyValue(SharedPreference.USER_ID, jsonObject.getString("user_id"));

                        if (jsonObject.getString("key") != null)
                            sharedPreference.setKeyValue(SharedPreference.API_KEY, jsonObject.getString("key"));

                        if (jsonObject.getString("preferred_language") != null)
                            sharedPreference.setKeyValue(SharedPreference.PREFERRED_LANGUAGE, jsonObject.getString("preferred_language"));

                        if (jsonObject.getString("helpline") != null)
                            sharedPreference.setKeyValue(SharedPreference.HELPLINE, jsonObject.getString("helpline"));

                        if (jsonObject.getString("full_name") != null)
                            sharedPreference.setKeyValue(SharedPreference.FULL_NAME, jsonObject.getString("full_name"));

                        if (jsonObject.getString("user_name") != null)
                            sharedPreference.setKeyValue(SharedPreference.USER_NAME, jsonObject.getString("user_name"));

                        requestCallback.onObjectRequestCallback(null, Constants.API_LOGIN, true);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }


    public void addFarmer(JSONObject jsonObject) {

        callApiPost_Patch(Urls.FARMERS, jsonObject, Request.Method.POST, new ResponseCallback() {
            @Override
            public void callback(Object response, boolean status) {
                requestCallback.onObjectRequestCallback(response, Constants.API_NEW_FARMER, status);
            }
        });
    }
}
