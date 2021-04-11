package com.example.foodrecipes.view.detail;

import com.example.foodrecipes.model.Meals;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);

}
