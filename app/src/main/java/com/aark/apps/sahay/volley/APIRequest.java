package com.aark.apps.sahay.volley;
/*
 * Created by karthik on 01/07/17.
 */

import android.content.Context;

import com.aark.apps.sahay.dao.ResponseCallback;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Map;

public class APIRequest {

    private Context context;

    public APIRequest(Context context) {
        this.context = context;
    }

    /*private Map<String, String> getHeader() {
        Map<String, String> params = new HashMap<>();
        params.put(Reque.AUTHORIZATION, "Token " + pref.getAuthToken());
        params.put(HttpHeader.ACCEPT, "application/json; version=2.0");
        return params;
    }*/

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
        });

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

}
