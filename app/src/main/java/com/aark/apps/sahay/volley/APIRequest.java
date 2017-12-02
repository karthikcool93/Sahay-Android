package com.aark.apps.sahay.volley;
/*
 * Created by karthik on 01/07/17.
 */

import android.content.Context;

import com.aark.apps.sahay.dao.ResponseCallback;
import com.aark.apps.sahay.utilities.SharedPreference;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIRequest {

    private Context context;
    SharedPreference pref;

    public APIRequest(Context context) {
        this.context = context;
        pref = new SharedPreference(context);
    }

    private Map<String, String> getHeader() {
        Map<String, String> params = new HashMap<>();
        if (pref.getKeyValue(SharedPreference.API_KEY) != null)
            params.put("Authorization", "APIKey " + pref.getKeyValue(SharedPreference.USER_NAME) + ":" + pref.getKeyValue(SharedPreference.API_KEY));
        return params;
    }

    protected void callAPI(int method, String url, final Map<String, String> requestParams, final ResponseCallback responseCallback) {

        StringRequest stringRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                responseCallback.callback(response, true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (requestParams != null) {
                    return requestParams;
                }
                return super.getParams();
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueSingelton.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    protected void callApiGet(String url, final ResponseCallback responseCallback) {

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (responseCallback != null)
                    responseCallback.callback(response, true);
//                requestCallback.onListRequestSuccessful(searchModels, Constants.SEARCH_AUTOCOMPLETE, true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeader();
            }
        };

        RequestQueueSingelton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonReq);

    }

    protected void callApiString(String url, int method, final Map<String, String> keyValue, final ResponseCallback responseCallback) {

        StringRequest jsonArrayRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                responseCallback.callback(response, true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (keyValue != null && keyValue.size() > 0)
                    return keyValue;
                return super.getParams();
            }
        };

        RequestQueueSingelton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    public void callApiPost_Patch(String url, JSONObject jsonObject, int method, final ResponseCallback callback) {

        JsonObjectRequest postReq = new JsonObjectRequest(method, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                callback.callback(response, true);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                callback.errorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeader();
            }
        };
        postReq.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueSingelton.getInstance(context.getApplicationContext()).addToRequestQueue(postReq);
    }

}
