package com.example.foodrecipes.utilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodrecipes.R;

public class BaseActivity extends AppCompatActivity {

    public void addFragmentAct(Fragment fragment, String tag){
        getSupportFragmentManager()
                .beginTransaction().add(R.id.fragment_container, fragment, tag)
                .commit();
    }

    public void replaceFragment(Fragment fragment, String tag){
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

}
