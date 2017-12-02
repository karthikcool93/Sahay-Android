package com.aark.apps.sahay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aark.apps.sahay.dao.FarmersDao;
import com.aark.apps.sahay.models.Farmers;
import com.aark.apps.sahay.models.Villages;
import com.aark.apps.sahay.volley.RequestCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewFarmerFragment extends Fragment implements RequestCallback {

    EditText name, phone, aadharnumber;
    RadioGroup radioGroup;
    Button villages, save;
    TextView villageName;

    int selectedVillageId = 0;
    String selectedVillageString = null;

    public NewFarmerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_farmer_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);

        villages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).villageSelectClickNewFragment();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    FarmersDao farmersDao = new FarmersDao(getActivity(), NewFarmerFragment.this);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", name.getText().toString());
                        jsonObject.put("phone", phone.getText().toString());
                        JSONObject vill = new JSONObject();
                        vill.put("server_id", selectedVillageId);
                        jsonObject.put("village", vill);
                        jsonObject.put("aadhar_number", aadharnumber.getText().toString());
                        if (radioGroup.getCheckedRadioButtonId() == R.id.male)
                            jsonObject.put("gender", "M");
                        else
                            jsonObject.put("gender", "F");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    farmersDao.addFarmer(jsonObject);
                }
            }
        });
    }

    private boolean validateData() {
        if (name.getText().toString().trim().length() == 0) {
            name.setError(getString(R.string.enter_username));
            return false;
        }
        if (phone.getText().toString().trim().length() == 0) {
            phone.setError(getString(R.string.enter_password));
            return false;
        }
        if (aadharnumber.getText().toString().trim().length() == 0) {
            aadharnumber.setError(getString(R.string.enter_aadhar));
            return false;
        }
        if (selectedVillageId == 0) {
            Toast.makeText(getActivity(), getString(R.string.village_not_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void initialize(View view) {
        name = view.findViewById(R.id.input_name);
        phone = view.findViewById(R.id.phone);
        aadharnumber = view.findViewById(R.id.aadhar);

        radioGroup = view.findViewById(R.id.radioGroup);
        villages = view.findViewById(R.id.select_village);

        villageName = view.findViewById(R.id.village_name);
        save = view.findViewById(R.id.save);

        if (selectedVillageString != null) {
            villageName.setText(selectedVillageString);
        }

    }

    public void villageSelected(Villages villages) {
        selectedVillageId = villages.getServer_id();
        selectedVillageString = villages.getName();
    }

    @Override
    public void onObjectRequestCallback(Object object, int check, boolean status) {
        if (status) {
            JSONObject jsonObject = (JSONObject) object;
            try {
                Farmers farmers = new Farmers(jsonObject);
                farmers.save();
                getActivity().getSupportFragmentManager().popBackStack();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
