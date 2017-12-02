package com.aark.apps.sahay.screens.farmers_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aark.apps.sahay.MainActivity;
import com.aark.apps.sahay.R;
import com.aark.apps.sahay.models.Farmers;

import java.util.List;

public class FarmersListFragment extends Fragment {

    protected RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.villages_list_dialog_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean clickHandler = true;
        if (getArguments() != null && getArguments().containsKey("click")) {
            clickHandler = false;
        }
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final List<Farmers> farmersList = Farmers.getAllFarmers();

        FarmersNamesListAdapter farmersNamesListAdapter;

        if (clickHandler) {
            farmersNamesListAdapter = new FarmersNamesListAdapter(farmersList, new FarmersNamesListAdapter.Callback() {
                @Override
                public void click(int pos) {
                    ((MainActivity) getActivity()).farmerSelectedNewOrder(farmersList.get(pos));
                }
            });
        } else {
            farmersNamesListAdapter = new FarmersNamesListAdapter(farmersList);
        }

        recyclerView.setAdapter(farmersNamesListAdapter);

    }

}