package com.example.manish.activitytracker;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FourColumn_ListAdapter1 extends ArrayAdapter<Activityy> {

    private LayoutInflater mInflater;
    private ArrayList<Activityy> users;
    private int mViewResourceId;

    public FourColumn_ListAdapter1(Context context, int textViewResourceId, ArrayList<Activityy> users) {
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
            TextView Duration = (TextView) convertView.findViewById(R.id.duration);
            if (Category != null) {
                Category.setText(user.getCategory());
            }
            if (Activity != null) {
                Activity.setText(user.getActivity());
            }
            if (Start != null) {
                Start.setText(user.getStart());
            }
            if (Duration != null) {
                Duration.setText(user.getDuration());
            }
        }

        return convertView;
    }



}
