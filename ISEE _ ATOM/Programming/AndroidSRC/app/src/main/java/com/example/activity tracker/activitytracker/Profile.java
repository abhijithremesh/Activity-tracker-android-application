package com.example.manish.activitytracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;


public class Profile extends Activity {


    Button Continue1;
    TextView SecurityQn;
    Context context = this;

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Continue1 = (Button) findViewById(R.id.Continue_Profile);

        addListenerOnButton();

        SecurityQn=(TextView) findViewById(R.id.Security);

        SecurityQn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, setsecurityqn.class);
                startActivity(intent1);
            }
        });



    }

    public void addListenerOnButton() {

        final Context context = this;

        Continue1 = (Button) findViewById(R.id.Continue_Profile);
        Continue1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent1 = new Intent(context, CreatePIN.class);
                startActivity(intent1);

            }

        });

    }



}
