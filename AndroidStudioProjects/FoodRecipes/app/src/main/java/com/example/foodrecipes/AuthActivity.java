package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.foodrecipes.fragments.AuthOptions;
import com.example.foodrecipes.utilities.BaseActivity;
import com.example.foodrecipes.view.home.HomeActivity;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_auth);
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(preferences.getString("signin", "0").equals("1")){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);finish();
        }
        else addFragmentAct(new AuthOptions(), "AuthOptions");
    }
}