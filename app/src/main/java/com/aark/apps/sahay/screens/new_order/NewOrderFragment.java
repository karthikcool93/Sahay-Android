package com.aark.apps.sahay.screens.new_order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aark.apps.sahay.R;
import com.aark.apps.sahay.models.ItemQty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewOrderFragment extends Fragment {

    RecyclerView recyclerView;
    ItemsQtyAdapter itemsQtyAdapter;

    ArrayList<ItemQty> itemQtyArrayList;

    EditText qty;
    ImageButton addItem;
    TextView dateTextView;

    public NewOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_order_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemQtyArrayList = new ArrayList<>();

        initialize(view);
        initializeListener();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String date = df.format(c.getTime());
        dateTextView.setText(date);
    }

    private void initializeListener() {
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qty.getText().toString().trim().length() == 0) {
                    qty.setError(getString(R.string.add_quantity));
                } else {
                    String item = "Tomato";
                    int q = Integer.parseInt(qty.getText().toString());
                    boolean found = false;
                    for (int i = 0; i < itemQtyArrayList.size(); ++i) {
                        if (itemQtyArrayList.get(i).getItem().equals(item)) {
                            int q1 = itemQtyArrayList.get(i).getQty() + q;
                            itemQtyArrayList.get(i).setQty(q1);
                            itemsQtyAdapter.notifyItemChanged(i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        itemQtyArrayList.add(new ItemQty(item, q));
                        itemsQtyAdapter.notifyItemInserted(itemQtyArrayList.size() - 1);
                    }
                }
            }
        });
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemsQtyAdapter = new ItemsQtyAdapter(itemQtyArrayList);

        recyclerView.setAdapter(itemsQtyAdapter);

        qty = view.findViewById(R.id.qty);
        addItem = view.findViewById(R.id.addItem);

        dateTextView = view.findViewById(R.id.date);


    }
}
