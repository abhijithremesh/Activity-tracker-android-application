package com.example.manish.activitytracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;



public class Homescreen extends Activity {

//    Button Profile;
//    Button About;
//    Button AddActivity;
//    Button Logs;
//    Button Overview;


    LinearLayout ViewLog,About,AddActivity,Logs,Overview;
    public static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onClick: before oncreatehome");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen2);
        addListenerOnButton();
        Log.d(TAG, "onClick: befghore oncreatehome");


    }

    public void addListenerOnButton() {

        final Context context = this;



        ViewLog = (LinearLayout) findViewById(R.id.ViewLog);
        About = (LinearLayout) findViewById(R.id.About);
        AddActivity=(LinearLayout) findViewById(R.id.AddActivity) ;
        Overview = (LinearLayout) findViewById(R.id.Overview);
        Logs = (LinearLayout) findViewById(R.id.Log);


        ViewLog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,RecycleViewContents.class);
                startActivity(intent);

            }

        });


        About.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,AboutUs.class);
                startActivity(intent);

            }

        });

        AddActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);

            }

        });

        Logs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, FilterView.class);
                startActivity(intent);

            }

        });

        Overview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, OverviewMain.class);
                startActivity(intent);

            }

        });

    }

}

