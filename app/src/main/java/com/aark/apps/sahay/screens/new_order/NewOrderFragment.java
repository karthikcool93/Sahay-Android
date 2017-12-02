package com.aark.apps.sahay.screens.new_order;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aark.apps.sahay.R;
import com.aark.apps.sahay.dao.OrdersDao;
import com.aark.apps.sahay.models.Farmers;
import com.aark.apps.sahay.models.ItemQty;
import com.aark.apps.sahay.models.Items;
import com.aark.apps.sahay.models.Orders;
import com.aark.apps.sahay.volley.RequestCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewOrderFragment extends Fragment implements RequestCallback {

    RecyclerView recyclerView;
    ItemsQtyAdapter itemsQtyAdapter;

    ArrayList<ItemQty> itemQtyArrayList;

    EditText qty, advanceMoney;
    ImageButton addItem;
    TextView dateTextView, selectFarmerName;

    Button selectFarmer, selectItem, save;

    String selectedFarmerString, selectedItemString;
    int selectedFarmerInt, selectedItemInt;

    int countAPIResponse = 0;

    ProgressDialog progressDialog;

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

        if (itemQtyArrayList == null)
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
                    if (selectedItemString != null) {
                        int q = Integer.parseInt(qty.getText().toString());
                        boolean found = false;
                        for (int i = 0; i < itemQtyArrayList.size(); ++i) {
                            if (itemQtyArrayList.get(i).getItem().equals(selectedItemString)) {
                                int q1 = itemQtyArrayList.get(i).getQty() + q;
                                itemQtyArrayList.get(i).setQty(q1);
                                itemsQtyAdapter.notifyItemChanged(i);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            itemQtyArrayList.add(new ItemQty(selectedItemString, q, selectedItemInt));
                            itemsQtyAdapter.notifyItemInserted(itemQtyArrayList.size() - 1);
                        }
                        qty.setText(null);
                        selectedItemString = null;
                        selectedItemInt = 0;

                    }
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    OrdersDao ordersDao = new OrdersDao(getActivity(), NewOrderFragment.this);

                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage(getActivity().getString(R.string.authenticating));
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    for (int i = 0; i < itemQtyArrayList.size(); ++i) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("date", dateTextView.getText().toString());
                            JSONObject farmer = new JSONObject();
                            farmer.put("server_id", selectedFarmerInt);
                            jsonObject.put("farmer", farmer);

                            JSONObject item = new JSONObject();
                            item.put("server_id", itemQtyArrayList.get(i).getItemId());
                            jsonObject.put("item", item);

                            jsonObject.put("advance_amount", advanceMoney.getText().toString());
                            jsonObject.put("quantity", itemQtyArrayList.get(i).getQty());

                            jsonObject.put("status", 0);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ordersDao.addOrder(jsonObject);
                    }
                }
            }
        });

    }

    private boolean validateData() {
        if (selectedFarmerInt == 0) {
            Toast.makeText(getActivity(), getString(R.string.farmer_not_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (itemQtyArrayList.size() == 0) {
            Toast.makeText(getActivity(), getString(R.string.items_not_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (advanceMoney.getText().toString().trim().length() == 0) {
            advanceMoney.setError(getString(R.string.money_not_empty));
            return false;
        }

        return true;
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

        selectFarmer = view.findViewById(R.id.select_farmer);
        selectItem = view.findViewById(R.id.select_item);

        selectFarmerName = view.findViewById(R.id.select_farmer_name);
        if (selectedFarmerString != null)
            selectFarmerName.setText(selectedFarmerString);

        save = view.findViewById(R.id.save);
        advanceMoney = view.findViewById(R.id.advanceMoney);

    }

    public void farmerSelected(Farmers farmers) {
        selectedFarmerInt = farmers.getServer_id();
        selectedFarmerString = farmers.getName();
    }

    public void itemSelected(Items items) {
        selectedItemInt = items.getServer_id();
        selectedItemString = items.getName();
    }

    @Override
    public void onObjectRequestCallback(Object object, int check, boolean status) {
        if (status) {
            JSONObject jsonObject = (JSONObject) object;
            try {
                Orders orders = new Orders(jsonObject);
                orders.save();

                countAPIResponse++;
                if (countAPIResponse == itemQtyArrayList.size()) {
                    try {
                        if (progressDialog != null)
                            progressDialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    getActivity().getSupportFragmentManager().popBackStack();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
