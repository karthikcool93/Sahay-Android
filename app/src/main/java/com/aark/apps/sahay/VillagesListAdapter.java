package com.aark.apps.sahay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aark.apps.sahay.models.Villages;

import java.util.List;

/*
 * Created by karthik on 01/07/17.
 */

public class VillagesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Villages> villagesList;

    public interface Callback {
        void click(int pos);
    }

    Callback callback;

    public VillagesListAdapter(List<Villages> villages) {
        this.villagesList = villages;
    }

    public VillagesListAdapter(List<Villages> villages, Callback callback) {
        this.villagesList = villages;
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

        rowVH.name.setText(villagesList.get(holder.getAdapterPosition()).getName());
    }

    @Override
    public int getItemCount() {
        return villagesList.size();
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
