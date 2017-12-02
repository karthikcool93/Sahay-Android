package com.aark.apps.sahay.utilities;
/*
 * Created by karthik on 02/12/17.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.aark.apps.sahay.R;

public class SharedPreference {

    private SharedPreferences pref;
    private Context _context;

    private final String API_KEY = "api_key";

    public SharedPreference(Context context) {
        this._context = context;
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(context.getString(R.string.shared_preference_name), PRIVATE_MODE);
    }

    public void setAPI_KEY(String key) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(API_KEY, key);
        editor.apply();
    }

    public String getAPI_KEY() {
        return pref.getString(API_KEY, null);
    }


}
