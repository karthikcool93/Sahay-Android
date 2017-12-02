package com.aark.apps.sahay.screens.farmers_contact;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aark.apps.sahay.R;
import com.aark.apps.sahay.models.Farmers;
import com.aark.apps.sahay.models.ItemQty;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by karthik on 01/07/17.
 */

public class FarmersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Farmers> farmersArrayList;

    public FarmersListAdapter(List<Farmers> farmersArrayList) {
        this.farmersArrayList = farmersArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_list_row, parent, false);
        return new RowVH(v);



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowVH rowVH = (RowVH) holder;

        rowVH.name.setText(farmersArrayList.get(holder.getAdapterPosition()).getName());
        rowVH.village.setText(farmersArrayList.get(holder.getAdapterPosition()).getVillage()+"");
        rowVH.phone.setText(farmersArrayList.get(holder.getAdapterPosition()).getPhone());
        rowVH.aadhar.setText(farmersArrayList.get(holder.getAdapterPosition()).getAadharNo());
    }

    @Override
    public int getItemCount() {
        return farmersArrayList.size();
    }

    private class RowVH extends RecyclerView.ViewHolder {

        TextView name, village, phone,aadhar;

        RowVH(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            village = itemView.findViewById(R.id.village);
            phone = itemView.findViewById(R.id.phone);
            aadhar = itemView.findViewById(R.id.aadhar);
        }
    }
}
