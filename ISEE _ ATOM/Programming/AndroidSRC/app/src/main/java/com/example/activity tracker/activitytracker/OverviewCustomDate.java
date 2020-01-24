package com.example.manish.activitytracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class OverviewCustomDate extends Activity {


    EditText fromdate, todate;
    private DatePickerDialog.OnDateSetListener toDateSetListener,fromDateSetListener ;
    String from_date,to_date;
    Button piechart;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Scene Contra","katta scene 2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_custom_date);

        fromdate = findViewById(R.id.edit_from_date);
        todate= findViewById(R.id.edit_to_date);


        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        OverviewCustomDate.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        fromDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        OverviewCustomDate.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        toDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        fromDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                fromdate.setText(date);
                from_date=date;
            }
        };

        toDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                todate.setText(date);
                to_date=date;
            }
        };

        piechart = findViewById(R.id.button_piechart);

        piechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(from_date!=null && to_date!=null) {
                    Log.d("Scene Contra", "katta scene after click");
                    Intent j = new Intent(OverviewCustomDate.this, OverviewCustom.class);
                    Log.d("Scene Contra", "katta scene after intent instance");
                    j.putExtra("from date", from_date);
                    j.putExtra("to date", to_date);
                    Log.d("Scene Contra", "katta scene 2 after putextra");
                    startActivity(j);
                    Log.d("Scene Contra", "katta scene 2");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter the dates.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }



}
