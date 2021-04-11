package com.example.foodrecipes.interfaces;

import com.example.foodrecipes.model.User;

public interface AuthInterface {
    void signIn(User user);
    void signUp();
}
