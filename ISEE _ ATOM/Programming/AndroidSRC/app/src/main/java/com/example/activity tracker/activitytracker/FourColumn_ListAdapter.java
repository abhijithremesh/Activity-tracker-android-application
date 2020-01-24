package com.example.manish.activitytracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class FourColumn_ListAdapter extends ArrayAdapter<Activityy> {

    private LayoutInflater mInflater;
    private ArrayList<Activityy> users;
    private int mViewResourceId;

    public FourColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Activityy> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Activityy user = users.get(position);

        if (user != null) {
            TextView Category = (TextView) convertView.findViewById(R.id.cat);
            TextView Activity = (TextView) convertView.findViewById(R.id.act);
            TextView Start = (TextView) convertView.findViewById(R.id.strdate);
            TextView Description = (TextView) convertView.findViewById(R.id.duration);

            Category.setText("CATEGORY");

            if (Category != null) {
                Category.setText(user.getCategory());
                Category.setBackgroundColor(Integer.parseInt(user.getColour()));
            }
            if (Activity != null) {
                Activity.setText(user.getActivity());
                Activity.setBackgroundColor(Integer.parseInt(user.getColour()));
            }
            if (Start != null) {
                Start.setText(user.getStart());
                Start.setBackgroundColor(Integer.parseInt(user.getColour()));
            }
            if (Description != null) {
                Description.setText(user.getDescription());
                Description.setBackgroundColor(Integer.parseInt(user.getColour()));
            }
        }

        return convertView;
    }



}


