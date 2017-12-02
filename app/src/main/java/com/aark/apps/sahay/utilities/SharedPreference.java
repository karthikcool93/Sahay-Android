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

    public SharedPreference(Context context) {
        this._context = context;
        int PRIVATE_MODE = 0;
//        pref = context.getSharedPreferences(context.getString(R.string.shared_preference_name), PRIVATE_MODE);
    }
}
