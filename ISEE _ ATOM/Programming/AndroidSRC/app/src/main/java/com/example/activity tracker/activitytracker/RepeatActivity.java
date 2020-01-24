package com.example.manish.activitytracker;
import java.util.Arrays;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RepeatActivity extends AppCompatActivity {


    /*
    improvements from 4
    optimising code

    yet to test.............................
    */
    public String item1;
    public String item2;
    public String item3;
    public String item4;
    public String item5;
    public String item6;
    public String item7;
    public String item9;

    public void main() {

        MainActivity obj = new MainActivity();

        int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        item1 = obj.activity.getText().toString();
        item2 = obj.cat.getText().toString();
        item3 = obj.description.getText().toString();
        item4 = obj.startdate.getText().toString();//needed
        item5 = obj.time.getText().toString();
        item6 = obj.duration.getText().toString();
        item7 = obj.enddate.getText().toString();//needed
        Integer item8 = ((ColorDrawable) obj.cat.getBackground()).getColor();
        item9 = item8.toString();

        //String repeation = "weekly";
        String startDateArray = item4;
        String endDateArray = item6;
        int[] startDateNumber;
        startDateNumber = convert(startDateArray);
        for (int x : startDateNumber) {
            System.out.print(x);
            System.out.print(",");
        }
        System.out.print(" to ");
        int[] endDateNumber;
        endDateNumber = convert(endDateArray);
        for (int x : endDateNumber) {
            System.out.print(x);
            System.out.print(",");
        }

            int radioid = obj.radiogroup.getCheckedRadioButtonId();

            switch (radioid) {
                case R.id.radio_one:
                    System.out.println(" daily");
                    repeatDaily(startDateNumber[0], startDateNumber[1], startDateNumber[2], endDateNumber[0], endDateNumber[1], endDateNumber[2], days);
                    break; // optional

                case R.id.radio_two:
                    System.out.println(" weekly");
                    repeatWeekly(startDateNumber[0], startDateNumber[1], startDateNumber[2], endDateNumber[0], endDateNumber[1], endDateNumber[2], days);
                    break; // optional

                case R.id.radio_three:
                    System.out.println(" monthly");
                    repeatMonthly(startDateNumber[0], startDateNumber[1], startDateNumber[2], endDateNumber[0], endDateNumber[1], endDateNumber[2], days);
                    break; // optional

                // You can have any number of case statements.
                default: // Optional
                    System.out.println(" daily");
                    repeatDaily(startDateNumber[0], startDateNumber[1], startDateNumber[2], endDateNumber[0], endDateNumber[1], endDateNumber[2], days);
                    System.out.println("do nothing");
            }

        }



    public int[] convert(String numbersArray) {
        // date string to intiger conversion

        String[] tokens = numbersArray.split("/");
        int[] numbers = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = Integer.parseInt(tokens[i]);
        }
        return numbers;


    }


    public void repeatDaily(int d, int m, int y, int endD, int endM, int endY, int[] days) {
        // Function to generate dates for repeat daily option
        entertodDB(d, m, y);
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

                    entertodDB(d, m, y);
                    String dS = Integer.toString(d);
                    String mS = Integer.toString(m);
                    String yS = Integer.toString(y);
                    String slash = "/";
                    String currentDate = mS + slash + dS + slash + yS;
                    AddData(currentDate);
                }
            }//while(m<=12)
            if ((d == endD) && (m == (endM))) {
                //System.out.print("Break");
                break;

            }
        }//while (y<=endY)
        System.out.println("");
    }//repeatDaily

    public void repeatWeekly(int d, int m, int y, int endD, int endM, int endY, int[] days) {
        // Function to generate dates for repeat weekly option
        entertodDB(d, m, y);
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

                    entertodDB(d, m, y);
                    String dS = Integer.toString(d);
                    String mS = Integer.toString(m);
                    String yS = Integer.toString(y);
                    String slash = "/";
                    String currentDate = mS + slash + dS + slash + yS;
                    AddData(currentDate);
                }

            }
            if (((d + 7) >= endD) && (m == (endM)) && (y == endY)) {
                //System.out.print("Break");
                break;

            }
        }
        System.out.println("");
    }

    public void repeatMonthly(int d, int m, int y, int endD, int endM, int endY, int[] days) {
        // Function to generate dates for repeat Monlthy option
        entertodDB(d, m, y);
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
                        entertodDB(29, m, y);
                    } else if ((m == 2) && (ly == false) && ((d > 28))) {
                        entertodDB(28, m, y);

                    } else if (((m == 9) || (m == 4) || (m == 6) || (m == 11)) && (d > 30)) {
                        entertodDB(30, m, y);
                    } else {
                        entertodDB(d, m, y);
                        String dS = Integer.toString(d);
                        String mS = Integer.toString(m);
                        String yS = Integer.toString(y);
                        String slash = "/";
                        String currentDate = mS + slash + dS + slash + yS;
                        AddData(currentDate);
                    }
                }

            }
            if ((m == (endM)) && (y == endY)) {
                //System.out.print("Break");
                break;

            }

        }

        System.out.println("");
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

    public void AddData(String item3) {

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


    }
}

