package com.aark.apps.sahay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aark.apps.sahay.dao.FarmersDao;
import com.aark.apps.sahay.volley.RequestCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements RequestCallback {

    EditText userName, password;
    Button login;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialise(view);
        final FarmersDao farmersDao = new FarmersDao(getActivity(), this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    farmersDao.authenticateUser(userName.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private boolean validateData() {
        if (userName.getText().toString().trim().length() == 0) {
            userName.setError(getString(R.string.enter_username));
            return false;
        }
        if (password.getText().toString().trim().length() == 0) {
            password.setError(getString(R.string.enter_password));
            return false;
        }
        return true;
    }

    private void initialise(View view) {
        userName = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);

    }

    @Override
    public void onObjectRequestCallback(Object object, int check, boolean status) {
        if(status){
            ((MainActivity)getActivity()).userLoggedIn();
        }
    }
}
