package com.example.manish.activitytracker;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends ArrayAdapter {

    private Context mContext;
    private List<CategorySpinner> categoryList;

    public CategoryAdapter(@NonNull Context context,  ArrayList<CategorySpinner> list) {
        super(context, 0, list);
        mContext = context;
        categoryList = list;
    }
    @Override
    public int getCount()
    {
        return categoryList.size();
    }
    @Override
    public Object getItem(int arg0)
    {
        return categoryList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }
    @NonNull
    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView =inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        TextView txv=(TextView)convertView.findViewById(android.R.id.text1);

        CategorySpinner currentCategory = categoryList.get(position);



        txv.setText(currentCategory.getCategory());
        //category.setBackgroundColor(Integer.parseInt(currentCategory.getColor()));

        return convertView;
    }

}