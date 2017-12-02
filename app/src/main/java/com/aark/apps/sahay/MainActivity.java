package com.aark.apps.sahay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aark.apps.sahay.models.Farmers;
import com.aark.apps.sahay.models.Items;
import com.aark.apps.sahay.models.Villages;
import com.aark.apps.sahay.screens.farmers_contact.FarmerContactsFragment;
import com.aark.apps.sahay.screens.farmers_list.FarmersListFragment;
import com.aark.apps.sahay.screens.items_list.ItemsListFragment;
import com.aark.apps.sahay.screens.new_order.NewOrderFragment;
import com.aark.apps.sahay.utilities.Constants;
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
        if (sp.getKeyValue(SharedPreference.API_KEY) == null) {
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

    public void newFarmer(View view) {
        Fragment newFarmer1 = new NewFarmerFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, newFarmer1, Constants.FRAGMENT_NEW_FRAMER)
                .addToBackStack(null)
                .commit();
    }

    public void allFarmers(View view) {
        Fragment allFarmers1 = new FarmerContactsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, allFarmers1)
                .addToBackStack(null)
                .commit();
    }

    public void itemsDisplay(View view) {
        Fragment items = new ItemsListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("click", false);
        items.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, items)
                .addToBackStack(null)
                .commit();
    }

    public void newOrder(View view) {
        Fragment newOrder = new NewOrderFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, newOrder, Constants.FRAGMENT_NEW_ORDER)
                .addToBackStack(null)
                .commit();
    }

    public void userLoggedIn() {
        getSupportFragmentManager().popBackStack();

        Fragment fragment = new HomeScreenFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else
            super.onBackPressed();

    }

    public void villageSelectedNewFarmer(Villages villages) {
        getSupportFragmentManager().popBackStack();
        NewFarmerFragment newFarmerFragment = (NewFarmerFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_NEW_FRAMER);
        if (newFarmerFragment != null) {
            newFarmerFragment.villageSelected(villages);
        }

    }

    public void villageSelectClickNewFragment() {
        Fragment fragment = new VillagesListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }


    public void farmerSelectClickNewOrderFragment(View view) {
        Fragment fragment = new FarmersListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void farmerSelectedNewOrder(Farmers farmers) {
        getSupportFragmentManager().popBackStack();
        NewOrderFragment newOrderFragment = (NewOrderFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_NEW_ORDER);
        if (newOrderFragment != null) {
            newOrderFragment.farmerSelected(farmers);
        }

    }


    public void itemSelectClickNewOrderFragment(View view) {
        Fragment fragment = new ItemsListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void itemSelectedNewOrder(Items items) {
        getSupportFragmentManager().popBackStack();
        NewOrderFragment newOrderFragment = (NewOrderFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_NEW_ORDER);
        if (newOrderFragment != null) {
            newOrderFragment.itemSelected(items);
        }

    }

}
