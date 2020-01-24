package com.example.manish.activitytracker;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageButton;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class Category extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;

    private Button btnAdd, btnViewData, btnback;
    private ImageButton imagebtn;
    private EditText editText;
    private EditText editcolour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        editcolour = (EditText) findViewById(R.id.editColour1);


        btnback = findViewById(R.id.btnback);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();

                 Integer colorCode = ((ColorDrawable)editcolour.getBackground()).getColor();

                String color = colorCode.toString() ;
                if (editText.length() != 0) {
                    AddData(newEntry,color);
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        imagebtn=findViewById(R.id.imageButton1);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opencolourpicker1();

            }
        });




        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Category.this, MainActivity.class);
                startActivity(j);
            }
        });



    }

    public void AddData(String newEntry, String color) {
        boolean insertData = mDatabaseHelper.addData1(newEntry,color);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


    public void opencolourpicker1()
    {



        final ColorPicker colorPicker = new ColorPicker(this);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#258174");
        colors.add("#3C8D2F");
        colors.add("#20724f");
        colors.add("#6a3ab2");
        colors.add("#323299");
        colors.add("#800080");
        colors.add("#b77231");

        colorPicker.setColors(colors);
        colorPicker.setColumns(5);
        colorPicker.setRoundColorButton(true);
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {

                editcolour.setBackgroundColor(color);

                editcolour.setFocusable(false);

            }

            @Override
            public void onCancel() {

            }
        }).show();


    }



}

