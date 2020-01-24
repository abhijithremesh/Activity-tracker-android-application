package com.example.manish.activitytracker;

import android.graphics.Color;

public class CategorySpinner {
    private String Category;
    private String color;


    public CategorySpinner(String category, String color) {
        Category = category;
        this.color = color;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
