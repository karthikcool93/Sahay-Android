package com.aark.apps.sahay.screens.items_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aark.apps.sahay.R;
import com.aark.apps.sahay.models.Items;

import java.util.List;

public class ItemsListFragment extends Fragment {

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

        final List<Items> itemsList = Items.getAllItems();

        ItemsListAdapter itemsListAdapter;

        if (clickHandler) {
            itemsListAdapter = new ItemsListAdapter(itemsList, new ItemsListAdapter.Callback() {
                @Override
                public void click(int pos) {

//                ((MainActivity) getActivity()).villageSelectedNewFarmer(itemsList.get(pos));
                }
            });
        } else {
            itemsListAdapter = new ItemsListAdapter(itemsList);
        }

        recyclerView.setAdapter(itemsListAdapter);

    }

}