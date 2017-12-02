package com.aark.apps.sahay.screens.items_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aark.apps.sahay.R;
import com.aark.apps.sahay.models.Items;
import com.aark.apps.sahay.models.Villages;

import java.util.List;

/*
 * Created by karthik on 01/07/17.
 */

public class ItemsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Items> itemsList;

    public interface Callback {
        void click(int pos);
    }

    Callback callback;

    public ItemsListAdapter(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public ItemsListAdapter(List<Items> itemsList, Callback callback) {
        this.itemsList = itemsList;
        this.callback = callback;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.villages_list_row, parent, false);

        final RowVH rowVH = new RowVH(v);
        if (callback != null) {
            rowVH.fullView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.click(rowVH.getAdapterPosition());
                }
            });
        }
        return rowVH;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowVH rowVH = (RowVH) holder;

        rowVH.name.setText(itemsList.get(holder.getAdapterPosition()).getName());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    private class RowVH extends RecyclerView.ViewHolder {

        TextView name;
        LinearLayout fullView;

        RowVH(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            fullView = itemView.findViewById(R.id.fullView);
        }
    }
}
