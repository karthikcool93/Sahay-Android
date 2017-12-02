package com.aark.apps.sahay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aark.apps.sahay.utilities.SharedPreference;

public class MainActivity extends AppCompatActivity {

    SharedPreference sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp = new SharedPreference(this);

        Fragment fragment;
        if (sp.getAPI_KEY() == null) {
            fragment = new LoginFragment();
        } else {
            fragment = new HomeScreenFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newFarmer(View view){
        Fragment newFarmer1 = new NewFarmerFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, newFarmer1)
                .addToBackStack(null)
                .commit();
    }

    public void allFarmers(View view){
        Fragment allFarmers1 = new FarmerContactsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, allFarmers1)
                .addToBackStack(null)
                .commit();
    }
}
