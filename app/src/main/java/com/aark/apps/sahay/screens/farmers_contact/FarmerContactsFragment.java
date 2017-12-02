package com.aark.apps.sahay.screens.farmers_contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aark.apps.sahay.R;
import com.aark.apps.sahay.models.Farmers;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FarmerContactsFragment extends Fragment {

    RecyclerView recyclerView;

    public FarmerContactsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.farmer_contacts_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        List<Farmers> farmersArrayList = Farmers.getAllFarmers();
        FarmersListAdapter farmersListAdapter = new FarmersListAdapter(farmersArrayList);

        recyclerView.setAdapter(farmersListAdapter);
    }
}
