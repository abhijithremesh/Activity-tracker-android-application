package com.example.manish.activitytracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ImageButton;
import java.util.List;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.app.TimePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.widget.DatePicker;

import java.util.Calendar;
import android.util.Log;
import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Date;
import petrov.kristiyan.colorpicker.ColorPicker;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd,btnView;
    ImageButton btnre,btnn;
    EditText activity;

    TextView cat;
    EditText description;
    EditText startdate;
    EditText time;
    EditText duration;
    EditText enddate;
    TextView category;//asssigning variables to respective edit fields
    RadioGroup radiogroup;
    RadioButton radiobutton;
    //    Abhijith
    private String mDate;
    EditText btnj;
    private static final String TAG = "MainActivity";
    private EditText mDisplayDate;
    public EditText mDisplay2Date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDate2SetListener;
    private  Spinner spinner;
    //DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    //Calendar dateTime = Calendar.getInstance();
    //private Button btn_date;
    Calendar currenttime;
    int hour , minute ;
    String format;

    private EditText tee;

    boolean flag=false;
    public String item1;
    public String item2;
    public String item3;
    public String item4;
    public String item5;
    public String item6;
    public String item7;
    public String item9;
    public int[] startDateNumber;
    public int[] endDateNumber;
    public int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        BottomNavigationView bottomNavigationView = findViewById(R.id.btnnvg);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.menu_home:
                        Intent intent1 = new Intent(MainActivity.this,Homescreen.class);
                        startActivity(intent1);
                        break;

                    case R.id.menu_search:
                        Intent intent2 = new Intent(MainActivity.this,FilterView.class);
                        startActivity(intent2);
                        break;

                    case R.id.menu_overview:
                        Intent intent3 = new Intent(MainActivity.this,OverviewDate.class);
                        startActivity(intent3);
                        break;

                }



            }
        });




        //btn_date = (Button) findViewById(R.id.btntime);

        mDisplayDate = findViewById(R.id.editText7);
        mDisplay2Date = findViewById(R.id.editText14);


        currenttime = Calendar.getInstance();


        tee = findViewById(R.id.editText8);

        hour = currenttime.get(Calendar.HOUR_OF_DAY);
        minute = currenttime.get(Calendar.MINUTE);

        selectedtimeformat(hour);

        //tee.setText(hour + " : " + minute + " " + format);

        tee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timepickerdialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedtimeformat(hourOfDay);
                        tee.setText(hourOfDay + " : " + minute + " " + format);
                    }
                },hour , minute, true);
                timepickerdialog.show();
            }
        });






        //btn_date.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //   public void onClick(View v) {
        //       updateDate();
        //   }
        // });

        //updateTextLabel();



        btnre = findViewById(R.id.button222);
        btnre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent j = new Intent(MainActivity.this, Category.class);
                startActivity(j);
            }
        });


/*
        btnn=findViewById(R.id.btnChoose);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencolourpicker();
            }
        });
        */

        radiogroup = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.spinner1);




        myDB = new DatabaseHelper(this);

        ArrayList<String>categories = myDB.getAll1categories();


        ArrayList<CategorySpinner> categories1 = myDB.getAll1categories1();

        // categories1.add(0,new CategorySpinner("Choose Category",""));
        categories.add(0, "Choose Category");


        ArrayAdapter<String> dataAdapter;



        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(new CategoryAdapter1(this,R.layout.spinner_layout, categories1));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose Category")) {
                } else {

                    CategorySpinner cat ;
                    cat=     (CategorySpinner) parent.getItemAtPosition(position);
                    category.setBackgroundColor(Integer.parseInt(cat.getColor()));
                    category.setText(cat.getCategory());

                    Toast.makeText(parent.getContext(), "Selected : " + cat.getCategory(), Toast.LENGTH_SHORT).show();

                    //btnj.setBackgroundColor(Integer.parseInt(cat.getColor()));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        mDisplay2Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog1 = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDate2SetListener,
                        year, month, day);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();


            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }



        };

        mDate2SetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplay2Date.setText(date);
            }



        };

        activity = (EditText) findViewById(R.id.editText);
        cat = (TextView) findViewById(R.id.editText20);
        description = (EditText) findViewById(R.id.editText5);
        startdate = (EditText) findViewById(R.id.editText7);
        time = (EditText) findViewById(R.id.editText8);
        duration = (EditText) findViewById(R.id.editText9);
        enddate = (EditText) findViewById(R.id.editText14);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);
        btnj= (EditText) findViewById(R.id.editcolour);
        category = findViewById(R.id.editText20); //giving drop down selected to selected category edit box
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1 = activity.getText().toString();
                item2 = cat.getText().toString();
                item3 = description.getText().toString();
                item4 = startdate.getText().toString();
                item5 = time.getText().toString();
                item6 = duration.getText().toString();
                if ((enddate.length() == 0)) {
                    item7 = item4;
                } else {
                    item7 = enddate.getText().toString();
                }
                Integer item8 = ((ColorDrawable) cat.getBackground()).getColor();
                item9 = item8.toString();


                String startDateArray = item4;
                String endDateArray = item7;

                if (startdate.length() != 0) {

                    startDateNumber = convert(startDateArray);
                    Log.d("TEST", Integer.toString(startDateNumber[0] + startDateNumber[1] + startDateNumber[2]));
                    System.out.print(" to ");
                    endDateNumber = convert(endDateArray);


                    flag = true;
                    checkbutton(getWindow().getDecorView().getRootView());
                    flag = false;
                }

                else{
                    Toast.makeText(MainActivity.this, "You must put something in the empty field!", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RecycleViewContents.class);
                startActivity(intent);

            }
        });

//Abhijith
        // setAdapter(mDate);

    }



    public void selectedtimeformat(int hour)
    {

        if (hour == 0){

            hour += 12;
            format = "AM";
        } else if (hour == 12)
        {
            format = "PM";
        }else if (hour > 12) {

            hour -= 12;
            format = "PM";

        } else {
            format = "AM";

        }





    }

    //private void updateDate(){
    // new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    //}


    //DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
    // @Override
    // public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    //  dateTime.set(Calendar.YEAR, year);
    //  dateTime.set(Calendar.MONTH, monthOfYear);
    // dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    // updateTextLabel();
    //}
    // };


    //private void updateTextLabel(){
    //  mDisplayDate.setText(formatDateTime.format(dateTime.getTime()));
    //}

    public void AddData(String item1, String item2, String item3, String item4, String item5, String item6, String item7, String item9) {

        boolean insertData = myDB.addData(item1,item2,item3,item4,item5,item6,item7,item9);

        if(insertData==true){
            //Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }




    public void opencolourpicker()
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

                btnj.setBackgroundColor(color);

                btnj.setFocusable(false);

            }

            @Override
            public void onCancel() {

            }
        }).show();


    }














    public void checkbutton(View v)
    {

        int radioid=radiogroup.getCheckedRadioButtonId();

        radiobutton=findViewById(radioid);

        //Toast.makeText(this,"Selected : " + radiobutton.getText(),Toast.LENGTH_SHORT).show();

        String item;
        List<String> result = new ArrayList<String>();
        int cnt;
        int i;
        switch (radioid) {
            case R.id.radio_one:
                mDisplay2Date.setEnabled(true);
                Toast.makeText(this,"Daily",Toast.LENGTH_SHORT).show();
                if(flag==true) {
                    if(validateDate(startDateNumber[0],startDateNumber[1],startDateNumber[2],endDateNumber[0],endDateNumber[1],endDateNumber[2])==true) {
                        result = repeatDaily(startDateNumber[0], startDateNumber[1], startDateNumber[2], endDateNumber[0], endDateNumber[1], endDateNumber[2], days);
                        i = 0;
                        //Toast.makeText(this, "daily entered", Toast.LENGTH_LONG).show();
                        while (i < result.size()) {

                            item = (String) result.get(i);
                            if ((activity.length() != 0) && (cat.length() != 0) && (description.length() != 0) && (startdate.length() != 0) && (time.length() != 0) && (duration.length() != 0) && (enddate.length() != 0) && (((ColorDrawable) cat.getBackground()).getColor() != 0)) {


                                AddData(item1, item2, item3, item, item5, item6, item7, item9);
//                                Intent j = new Intent(MainActivity.this, MainActivity.class);
//                                startActivity(j);
                                //}
                            } else {
                                Toast.makeText(MainActivity.this, "You must put something in the empty field!", Toast.LENGTH_LONG).show();
                            }
                            //if (i == ((result.size() - 1))) {

                            //}

                            i = i + 1;

                        }
                        Toast.makeText(MainActivity.this, "Data entered succesfully", Toast.LENGTH_LONG).show();
                        Intent j = new Intent(MainActivity.this, Homescreen.class);
                        startActivity(j);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "End date must be greater than start date", Toast.LENGTH_LONG).show();
                    }


                }
                flag=false;
                break;
            case R.id.radio_two:
                mDisplay2Date.setEnabled(true);
//                Toast.makeText(this,"Weekly",Toast.LENGTH_SHORT).show();
                if(flag==true) {
                    if(validateDate(startDateNumber[0],startDateNumber[1],startDateNumber[2],endDateNumber[0],endDateNumber[1],endDateNumber[2])==true) {
                        result = repeatWeekly(startDateNumber[0], startDateNumber[1], startDateNumber[2], endDateNumber[0], endDateNumber[1], endDateNumber[2], days);
                        i = 0;
                        while (i < result.size()) {

                            item = (String) result.get(i);
                            if ((activity.length() != 0) && (cat.length() != 0) && (description.length() != 0) && (startdate.length() != 0) && (time.length() != 0) && (duration.length() != 0) && (enddate.length() != 0) && (((ColorDrawable) cat.getBackground()).getColor() != 0)) {


                                AddData(item1, item2, item3, item, item5, item6, item7, item9);
//                                Intent j = new Intent(MainActivity.this, MainActivity.class);
//                                startActivity(j);
                                //}
                            } else {
                                Toast.makeText(MainActivity.this, "You must put something in the empty field!", Toast.LENGTH_LONG).show();
                            }
                            i = i + 1;

                        }
                        Toast.makeText(MainActivity.this, "Data entered succesfully", Toast.LENGTH_LONG).show();
                        Intent j = new Intent(MainActivity.this, Homescreen.class);
                        startActivity(j);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "End date must be greater than start date", Toast.LENGTH_LONG).show();
                    }

                }
                flag=false;
                break;
            case R.id.radio_three:
                mDisplay2Date.setEnabled(true);
//                Toast.makeText(this,"Monthly",Toast.LENGTH_SHORT).show();
                if(flag==true) {
                    if(validateDate(startDateNumber[0],startDateNumber[1],startDateNumber[2],endDateNumber[0],endDateNumber[1],endDateNumber[2])==true) {
                        result = repeatMonthly(startDateNumber[0], startDateNumber[1], startDateNumber[2], endDateNumber[0], endDateNumber[1], endDateNumber[2], days);
                        i = 0;
                        while (i < result.size()) {

                            item = (String) result.get(i);
                            if ((activity.length() != 0) && (cat.length() != 0) && (description.length() != 0) && (startdate.length() != 0) && (time.length() != 0) && (duration.length() != 0) && (enddate.length() != 0) && (((ColorDrawable) cat.getBackground()).getColor() != 0)) {


                                AddData(item1, item2, item3, item, item5, item6, item7, item9);
//                                Intent j = new Intent(MainActivity.this, MainActivity.class);
//                                startActivity(j);
                                //}
                            } else {
                                Toast.makeText(MainActivity.this, "You must put something in the empty field!", Toast.LENGTH_LONG).show();
                            }

                            i = i + 1;

                        }
                        Toast.makeText(MainActivity.this, "Data entered succesfully", Toast.LENGTH_LONG).show();
                        Intent j = new Intent(MainActivity.this, Homescreen.class);
                        startActivity(j);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "End date must be greater than start date", Toast.LENGTH_LONG).show();
                    }


                }
                flag=false;
                break;


            default:
                mDisplay2Date.setEnabled(true);
                if(flag==true) {
                    if((enddate.length()==0)) {
                        item7=item4;

                    }

                    if((activity.length()!= 0) && (cat.length()!=0) && (description.length()!=0) && (startdate.length()!=0) && (time.length()!=0) && (duration.length()!=0) && (((ColorDrawable) cat.getBackground()).getColor()!=0) ){


                        AddData(item1, item2, item3, item4, item5, item6, item7, item9);
//                        Intent j = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(j);
                        //}
                    }else{
                        Toast.makeText(MainActivity.this, "You must put something in the empty field!", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(MainActivity.this, "Data entered succesfully", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(MainActivity.this, Homescreen.class);
                    startActivity(j);

                }
                flag=false;

                break;
        }


    }

    public int[] convert(String numbersArray) {
        // date string to intiger conversion

        String[] tokens = numbersArray.split("/");
        int[] numbers = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = Integer.parseInt(tokens[i]);
            Log.d("TEST","Convert "+numbers[i]);
        }
        return numbers;


    }

    public boolean validateDate(int m, int d, int y, int endM, int endD, int endY) {
        boolean op;

        if(endY>y)
        {
            op=true;
        }
        else if(endY==y)
        {
            if(endM>m)
            {
                op=true;
            }
            else if(endM==m)
            {
                if(endD>d)
                {
                    op=true;

                }
                else
                {
                    op=false;
                }

            }
            else{
                op=false;
            }

        }
        else
        {
            op=false;
        }

        return op;

    }

    public List repeatDaily(int m, int d, int y, int endM, int endD, int endY, int[] days) {
        // Function to generate dates for repeat daily option
        List<String> result = new ArrayList<String>();
        //entertodDB(d, m, y);
        String dS = Integer.toString(d);
        String mS = Integer.toString(m);
        String yS = Integer.toString(y);
        String slash = "/";
        String currentDate = mS + slash + dS + slash + yS;
        result.add(currentDate);
        boolean ly;
        ly = checkLYear(y);
        if (ly == true) {
            days[1] = 29;
        } else {
            days[1] = 28;
        }
        while (y <= endY) {
            while (m <= 12) {
                if ((d == endD) && (m == (endM))) {
                    //System.out.print("Break");
                    break;

                } else {
                    if (d < days[m - 1]) {
                        d = d + 1;
                    } else if (d >= days[m - 1]) {
                        d = 1;
                        if (m < 12) {
                            m = m + 1;
                        } else {
                            m = 1;
                            d = 1;
                            y = y + 1;
                            ly = checkLYear(y);
                            if (ly == true) {
                                days[1] = 29;
                            } else {
                                days[1] = 28;
                            }
                        }
                    }

                    //entertodDB(d, m, y);
                    dS = Integer.toString(d);
                    mS = Integer.toString(m);
                    yS = Integer.toString(y);
                    slash = "/";
                    currentDate = mS + slash + dS + slash + yS;
                    result.add(currentDate);
                }
            }//while(m<=12)
            if ((d == endD) && (m == (endM))) {
                //System.out.print("Break");
                break;

            }
        }//while (y<=endY)
        System.out.println("");
        return result;
    }//repeatDaily

    public List repeatWeekly(int m, int d, int y, int endM, int endD, int endY, int[] days) {
        // Function to generate dates for repeat weekly option
        List<String> result = new ArrayList<String>();
        entertodDB(d, m, y);
        String dS = Integer.toString(d);
        String mS = Integer.toString(m);
        String yS = Integer.toString(y);
        String slash = "/";
        String currentDate = mS + slash + dS + slash + yS;
        //result.add(currentDate);
        boolean ly;
        ly = checkLYear(y);
        if (ly == true) {
            days[1] = 29;
        } else {
            days[1] = 28;
        }
        while (y <= endY) {
            while (m <= 12) {
                if (((d + 7) > endD) && (m == (endM)) && (y == endY)) {
                    //System.out.print("Break");
                    break;

                } else {
                    if ((d + 7) < days[m - 1]) {
                        d = d + 7;
                    } else {
                        int value = (days[m - 1]) - d;
                        d = 7 - value;
                        if (m < 12) {
                            m = m + 1;
                        } else {
                            m = 1;
                            y = y + 1;
                            ly = checkLYear(y);
                            if (ly == true) {
                                days[1] = 29;
                            } else {
                                days[1] = 28;
                            }
                        }
                        if ((d > endD) && (m == endM)) {
                            break;
                        }
                    }

                    //entertodDB(d, m, y);
                    dS = Integer.toString(d);
                    mS = Integer.toString(m);
                    yS = Integer.toString(y);
                    slash = "/";
                    currentDate = mS + slash + dS + slash + yS;
                    result.add(currentDate);
                }

            }
            if (((d + 7) >= endD) && (m == (endM)) && (y == endY)) {
                //System.out.print("Break");
                break;

            }
        }
        System.out.println("");
        return result;
    }

    public List repeatMonthly(int m, int d, int y, int endM, int endD, int endY, int[] days) {
        // Function to generate dates for repeat Monlthy option
        List<String> result = new ArrayList<String>();
        //entertodDB(d, m, y);
        String dS = Integer.toString(d);
        String mS = Integer.toString(m);
        String yS = Integer.toString(y);
        String slash = "/";
        String currentDate = mS + slash + dS + slash + yS;
        result.add(currentDate);
        boolean ly;
        ly = checkLYear(y);
        if (ly == true) {
            days[1] = 29;
        } else {
            days[1] = 28;
        }
        if (d > endD) {        //##1
            endM = endM - 1;    //end month may become zero, which is invalid
            if (endM == 0) {
                endM = 12;
                endY = endY - 1;    //##2
            }
        }
        while (y <= endY) {
            while (m <= 12) {
                if ((m == (endM)) && (y == endY)) {
                    //System.out.print("Break");
                    break;

                } else {
                    if (m < 12) {
                        m = m + 1;
                    } else {
                        m = 1;
                        //d=1;
                        y = y + 1;
                        ly = checkLYear(y);
                        if (ly == true) {
                            days[1] = 29;
                        } else {
                            days[1] = 28;
                        }
                    }
                    if ((m == 2) && (ly == true) && ((d > 29))) {
                        //entertodDB(29, m, y);
                        dS = Integer.toString(29);
                        mS = Integer.toString(m);
                        yS = Integer.toString(y);
                        slash = "/";
                        currentDate = mS + slash + dS + slash + yS;
                        //AddData(item1,item2,item3,currentDate,item5,item6,item7,item9);
                        result.add(currentDate);
                    } else if ((m == 2) && (ly == false) && ((d > 28))) {
                        //entertodDB(28, m, y);
                        dS = Integer.toString(28);
                        mS = Integer.toString(m);
                        yS = Integer.toString(y);
                        slash = "/";
                        currentDate = mS + slash + dS + slash + yS;
                        //AddData(item1,item2,item3,currentDate,item5,item6,item7,item9);
                        result.add(currentDate);

                    } else if (((m == 9) || (m == 4) || (m == 6) || (m == 11)) && (d > 30)) {
                        //entertodDB(30, m, y);
                        dS = Integer.toString(30);
                        mS = Integer.toString(m);
                        yS = Integer.toString(y);
                        slash = "/";
                        currentDate = mS + slash + dS + slash + yS;
                        //AddData(item1,item2,item3,currentDate,item5,item6,item7,item9);
                        result.add(currentDate);
                    } else {
                        //entertodDB(d, m, y);
                        dS = Integer.toString(d);
                        mS = Integer.toString(m);
                        yS = Integer.toString(y);
                        slash = "/";
                        currentDate = mS + slash + dS + slash + yS;
                        //AddData(item1,item2,item3,currentDate,item5,item6,item7,item9);
                        result.add(currentDate);
                    }
                }

            }
            if ((m == (endM)) && (y == endY)) {
                //System.out.print("Break");
                break;

            }

        }

        System.out.println("");
        return result;
    }

    public boolean checkLYear(int y) {
        // function to check leap year

        int year = y;
        boolean leap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                // year is divisible by 400, hence the year is a leap year
                if (year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            } else
                leap = true;
        } else
            leap = false;
        return leap;
    }

    public void entertodDB(int d, int m, int y) {

        System.out.println("");
        System.out.print(d);
        System.out.print("/");
        System.out.print(m);
        System.out.print("/");
        System.out.print(y);
    }

    /*public void AddData(String item3) {
        MainActivity obj = new MainActivity();
        item1 = obj.activity.getText().toString();
        item2 = obj.cat.getText().toString();
        item4 = obj.startdate.getText().toString();//needed
        item5 = obj.time.getText().toString();
        item6 = obj.duration.getText().toString();
        item7 = obj.enddate.getText().toString();//needed
        Integer item8 = ((ColorDrawable) obj.cat.getBackground()).getColor();
        item9 = item8.toString();
        DatabaseHelper myDB;
        myDB = new DatabaseHelper(this);
        boolean insertData = myDB.addData(item1, item2, item3, item4, item5, item6, item7, item9);
        if (insertData == true) {
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
            //}else{
            //    Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
            //}
        }
//Abhijith
    /*
    private void setAdapter(Date newDate) {
//       String date = DateUtilities.dateToString(newDate); // Convert date to string
        myDB = new DatabaseHelper(this);
        List<Activity> activityList = myDB.getContactsByDate(newDate); // filter by string
        RecyclerViewAdapter ra = new RecyclerViewAdapter(this, 0, activityList);
        listView.setAdapter(ra);
        txtDate.setText(DateFormat.format("MMM dd", newDate));
    }*/

}