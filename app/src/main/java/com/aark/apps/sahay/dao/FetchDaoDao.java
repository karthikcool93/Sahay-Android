package com.aark.apps.sahay.dao;
/*
 * Created by karthik on 02/12/17.
 */

import android.content.Context;

import com.aark.apps.sahay.models.Farmers;
import com.aark.apps.sahay.utilities.Constants;
import com.aark.apps.sahay.utilities.SharedPreference;
import com.aark.apps.sahay.volley.APIRequest;
import com.aark.apps.sahay.volley.RequestCallback;
import com.aark.apps.sahay.volley.Urls;
import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
}
