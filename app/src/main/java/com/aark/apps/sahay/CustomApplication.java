package com.aark.apps.sahay;
/*
 * Created by karthik on 02/12/17.
 */

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.activeandroid.ActiveAndroid;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ActiveAndroid.initialize(this);
    }
}
