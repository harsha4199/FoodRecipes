package com.example.foodrecipes.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.foodrecipes.R;
import com.example.foodrecipes.db.DBHandler;
import com.example.foodrecipes.interfaces.AuthInterface;
import com.example.foodrecipes.model.User;
import com.example.foodrecipes.utilities.BaseFragment;
import com.example.foodrecipes.view.home.HomeActivity;

public class SignIn extends BaseFragment implements AuthInterface {

    private String password;
    private String username;
    private EditText usernameField;
    private EditText passField;
    private Button signIn;
    private DBHandler handler;

    public SignIn() {
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
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameField = view.findViewById(R.id.email);
        passField = view.findViewById(R.id.password);
        signIn = view.findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameField.getText().toString().trim();
                password = passField.getText().toString().trim();
                if(!TextUtils.isEmpty(username)){
                    if(!TextUtils.isEmpty(password)){
                        handler.getUser(username);
                    }
                    else passField.setError("Enter Password");
                }
                else usernameField.setError("Enter Username");
            }
        });
    }

    @Override
    public void signIn(User user) {
        if(user != null){
            if(user.getPassword().equals(password)){
                if(saveStatus()){
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else Toast.makeText(getContext(), "Some Error Occurred Please Sign IN Again", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(), "User Not Found", Toast.LENGTH_SHORT).show();
    }

    private boolean saveStatus(){
        SharedPreferences preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("signin", "1");
        editor.apply();
        return true;
    }

    @Override
    public void signUp() {

    }
}