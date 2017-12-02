package com.aark.apps.sahay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aark.apps.sahay.models.Villages;

import java.util.List;

public class VillagesListFragment extends Fragment {

    protected RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.villages_list_dialog_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final List<Villages> villagesList = Villages.getAllVillages();

        VillagesListAdapter villagesListAdapter = new VillagesListAdapter(villagesList, new VillagesListAdapter.Callback() {
            @Override
            public void click(int pos) {
                ((MainActivity) getActivity()).villageSelectedNewFarmer(villagesList.get(pos));
            }
        });

        recyclerView.setAdapter(villagesListAdapter);

    }

}