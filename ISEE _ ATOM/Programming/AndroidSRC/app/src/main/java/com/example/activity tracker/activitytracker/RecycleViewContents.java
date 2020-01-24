package com.example.manish.activitytracker;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewContents extends AppCompatActivity {

    private static final String TAG = "RecycleViewContents";

    private RecyclerView myrecycleview ;
    private List<Activityy> lstactivities;
    Activityy user;
    private Button btnDownload;
//    RelativeLayout container;
//    RecyclerView container1;
//    AnimationDrawable anim1, anim2;



    DatabaseHelper myDB;
   View v;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle);

        Log.d(TAG, "onCreate: started.");


        Log.d(TAG, "onCreate: started.");

        myDB = new DatabaseHelper(this);

        lstactivities = new ArrayList<>();

        Date d = new Date();



        Cursor data = myDB.getListContents();

        int numRows = data.getCount();


        if(numRows == 0){
            Toast.makeText(RecycleViewContents.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                user = new Activityy(data.getString(1),data.getString(2),data.getString(3),data.getString(4),data.getString(8));
                lstactivities.add(i,user);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3)+" "+data.getString(4)+" "+data.getString(8));
                System.out.println(lstactivities.get(i).getCategory());
                i++;
            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            RecyclerViewAdapter recycleradapter = new RecyclerViewAdapter(this,lstactivities);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recycleradapter);


        }
        btnDownload=findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecycleViewContents.this,ViewListContents.class);
                startActivity(intent);

            }
        });

//        container = (RelativeLayout) findViewById(R.id.container_activity_list);
//        container1 = (RecyclerView) findViewById(R.id.recycler_view);
//
//        anim1 = (AnimationDrawable) container.getBackground();
//        anim1.setEnterFadeDuration(6000);
//        anim1.setExitFadeDuration(2000);
//
//        anim2 = (AnimationDrawable) container1.getBackground();
//        anim2.setEnterFadeDuration(6000);
//        anim2.setExitFadeDuration(2000);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (anim1 != null && !anim1.isRunning())
//            anim1.start();
//        if (anim2 != null && !anim2.isRunning())
//            anim2.start();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (anim1 != null && anim1.isRunning())
//            anim1.stop();
//        if (anim2 != null && anim2.isRunning())
//            anim2.stop();
//    }


}
