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

    public static final String API_KEY = "api_key";
    public static final String USER_ID = "user_id";
    public static final String PREFERRED_LANGUAGE = "preferred_language";
    public static final String FULL_NAME = "full_name";
    public static final String USER_NAME = "user_name";
    public static final String HELPLINE = "helpline";
    public static final String PHONE_NUMBER = "phone_number";

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

    public String getUSER_ID() {
        return pref.getString(USER_ID, null);
    }

    public String getPREFERRED_LANGUAGE() {
        return pref.getString(PREFERRED_LANGUAGE, null);
    }

    public String getFULL_NAME() {
        return pref.getString(FULL_NAME, null);
    }

    public String getUSER_NAME() {
        return pref.getString(USER_NAME, null);
    }

    public String getHELPLINE() {
        return pref.getString(HELPLINE, null);
    }

    public String getKeyValue(String key){
        return pref.getString(key, null);
    }

    public void setKeyValue(String key,String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }


}
