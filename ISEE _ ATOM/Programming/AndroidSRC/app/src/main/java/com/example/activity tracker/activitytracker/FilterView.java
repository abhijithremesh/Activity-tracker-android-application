package com.example.manish.activitytracker;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FilterView extends AppCompatActivity {

    EditText dateText;
    Button search;
    List<Activityy> mData;
    List<String> actAutoComplete;
    String date_filter;
    Spinner filterCategory;
    DatabaseHelper myDB;
    String mCat;
    String mAct;
    AutoCompleteTextView mActTV;
    CategorySpinner categorySpinner;
    LinearLayout container;
    RelativeLayout rl;
    RecyclerView rv;
    AnimationDrawable anim1,anim2, anim3, anim4;
    ScrollView sv;

    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_filter_view);

        filterCategory = findViewById(R.id.filter_category);
        mActTV=findViewById(R.id.filter_activity);
        myDB = new DatabaseHelper(this);
        ArrayList<CategorySpinner> categories1 = myDB.getAll1categories1();
        filterCategory.setAdapter(new CategoryAdapter1(this,R.layout.spinner_layout, categories1));

        actAutoComplete=new ArrayList<>();
        actAutoComplete=myDB.getActivities();
        String[] actArray= new String[actAutoComplete.size()];
        actArray= actAutoComplete.toArray(actArray);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, actArray);
        mActTV.setThreshold(1);
        mActTV.setAdapter(adapter);


        search = this.findViewById(R.id.filter_button);
        dateText = findViewById(R.id.filter_date);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        FilterView.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dateText.setText(date);
            }



        };

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDb;
                date_filter=dateText.getText().toString();
                mAct= mActTV.getText().toString();
                    categorySpinner= (CategorySpinner) filterCategory.getSelectedItem();
                    mCat=categorySpinner.getCategory();
                    myDb = new DatabaseHelper(FilterView.this);
                    mData = new ArrayList<>();
                    mData = myDb.getContactsByDate(date_filter,mAct,mCat);

                    RecyclerView recyclerView = findViewById(R.id.filter_view_rec);
                    RecyclerViewAdapter recycleradapter = new RecyclerViewAdapter(getApplicationContext(), mData);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(recycleradapter);

            }
        });

        container = (LinearLayout) findViewById(R.id.container_Logs);
//        anim1 = (AnimationDrawable) container.getBackground();
//        anim1.setEnterFadeDuration(6000);
//        anim1.setExitFadeDuration(2000);
//
//        sv = (ScrollView)findViewById(R.id.container_scroll);
//        anim2 = (AnimationDrawable) sv.getBackground();
//        anim2.setEnterFadeDuration(6000);
//        anim2.setExitFadeDuration(2000);

//        rv = (RecyclerView)findViewById(R.id.filter_view_rec);
//        anim3 = (AnimationDrawable) rv.getBackground();
//        anim3.setEnterFadeDuration(6000);
//        anim3.setExitFadeDuration(2000);

//        rl = (RelativeLayout)findViewById(R.id.parent_layout);
//        anim4 = (AnimationDrawable) rl.getBackground();
//        anim4.setEnterFadeDuration(6000);
//        anim4.setExitFadeDuration(2000);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim1 != null && !anim1.isRunning())
            anim1.start();
        if (anim2 != null && !anim2.isRunning())
            anim2.start();
        if (anim3 != null && !anim2.isRunning())
            anim3.start();
        if (anim4 != null && !anim2.isRunning())
            anim4.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim1 != null && anim1.isRunning())
            anim1.stop();
        if (anim2 != null && anim2.isRunning())
            anim2.stop();
        if (anim3 != null && anim2.isRunning())
            anim3.stop();
        if (anim4 != null && anim2.isRunning())
            anim4.stop();

    }


}
