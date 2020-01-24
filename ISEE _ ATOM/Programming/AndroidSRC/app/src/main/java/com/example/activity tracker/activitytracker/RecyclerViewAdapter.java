package com.example.manish.activitytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private Context mcontext;
    private List<Activityy> mData;

    public RecyclerViewAdapter (Context mcontext,List<Activityy>mData)
    {
      this.mcontext=mcontext;
      this.mData=mData;


    }




    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mcontext).inflate(R.layout.layout_listitem,parent,false);

        MyViewHolder vholder = new MyViewHolder(v);

        return vholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.Category.setText(mData.get(position).getCategory());
        holder.Activity.setText(mData.get(position).getActivity());
        holder.Startdate.setText(mData.get(position).getDescription());
        holder.ActivityDes.setText(mData.get(position).getStart());
        holder.Colour.setBackgroundColor(Integer.parseInt(mData.get(position).getColour()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView Colour;
        TextView Category;
        TextView Activity;
        TextView ActivityDes;
        TextView Startdate;
        RelativeLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            Colour = itemView.findViewById(R.id.textView8);
            Category = itemView.findViewById(R.id.textView9);
            Activity = itemView.findViewById(R.id.textView3);
            ActivityDes = itemView.findViewById(R.id.textView);
            Startdate = itemView.findViewById(R.id.textView7);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }


}
