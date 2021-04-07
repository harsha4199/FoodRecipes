package com.example.foodrecipes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodrecipes.R;
import com.example.foodrecipes.db.DBHandler;
import com.example.foodrecipes.interfaces.AuthInterface;
import com.example.foodrecipes.model.User;
import com.example.foodrecipes.utilities.BaseFragment;


public class SignUp extends BaseFragment implements AuthInterface {

    private String password;
    private String username, name;
    private EditText usernameField;
    private EditText passField, nameField;
    private Button signUp;
    private DBHandler handler;

    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new DBHandler(getContext(), null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameField = view.findViewById(R.id.email);
        nameField = view.findViewById(R.id.name);
        passField = view.findViewById(R.id.password);
        signUp = view.findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameField.getText().toString().trim();
                username = usernameField.getText().toString().trim();
                password = passField.getText().toString().trim();

                if(!TextUtils.isEmpty(name)){
                    if(!TextUtils.isEmpty(username)){
                        if(!TextUtils.isEmpty(password)){
                            handler.addUser(name, username, password);
                        }
                        else passField.setError("Enter Password");
                    }
                    else usernameField.setError("Enter Username");
                }
                else nameField.setError("Enter Name");

            }
        });

    }

    @Override
    public void signIn(User user) {

    }

    @Override
    public void signUp() {
        replace(new SignIn(), "Signin");
    }
}