package com.example.manish.activitytracker;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.ImageView;

import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.homescreen);



        LinearLayout first = (LinearLayout )findViewById(R.id.homeoverview);

        first.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent j = new Intent(Home.this, FilterView.class);

                startActivity(j);


            }

        });



        LinearLayout second = (LinearLayout )findViewById(R.id.accadd);

        second.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent j = new Intent(Home.this, MainActivity.class);

                startActivity(j);

            }

        });





        LinearLayout third = (LinearLayout )findViewById(R.id.acclogs);

        third.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent j = new Intent(Home.this, RecycleViewContents.class);

                startActivity(j);

            }

        });





        LinearLayout fourth = (LinearLayout )findViewById(R.id.profilehome);

        fourth.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {



            }

        });


    }

}
