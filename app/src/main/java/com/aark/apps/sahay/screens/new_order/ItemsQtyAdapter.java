package com.aark.apps.sahay.screens.new_order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aark.apps.sahay.R;
import com.aark.apps.sahay.models.ItemQty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Created by karthik on 01/07/17.
 */

public class ItemsQtyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ItemQty> itemQtyArrayList;

    public ItemsQtyAdapter(ArrayList<ItemQty> itemQtyArrayList) {
        this.itemQtyArrayList = itemQtyArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qty_row, parent, false);
        return new RowVH(v);



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowVH rowVH = (RowVH) holder;

        rowVH.itemName.setText(itemQtyArrayList.get(holder.getAdapterPosition()).getItem());
        rowVH.quantity.setText(itemQtyArrayList.get(holder.getAdapterPosition()).getQty()+"");
    }

    @Override
    public int getItemCount() {
        return itemQtyArrayList.size();
    }

    private class RowVH extends RecyclerView.ViewHolder {

        TextView itemName, quantity;

        RowVH(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
