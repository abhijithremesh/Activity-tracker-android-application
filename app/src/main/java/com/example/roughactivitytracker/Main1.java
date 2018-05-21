package com.example.roughactivitytracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;import android.widget.ListView;

public class Main1 extends Activity
{
    // Array of strings...
    ListView simpleList;
    String countryList[] = {"Lecture", "Exercise", "Tutorial", "Exam", "Library", "NewZealand"};

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      setContentView(R.layout.activity_main);
        simpleList = (ListView)findViewById(android.R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_filter_display, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }
}

