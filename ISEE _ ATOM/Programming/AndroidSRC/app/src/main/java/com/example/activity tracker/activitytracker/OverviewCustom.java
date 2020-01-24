package com.example.manish.activitytracker;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

public class OverviewCustom extends Activity implements OnChartValueSelectedListener{

    int[] startDateNumber;
    int[] endDateNumber;
    int[] days={31,28,31,30,31,30,31,31,30,31,30,31};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Scene Contra","katta scene before create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_custom);



        Log.d("Scene Contra","katta scene 1");

        PieChart pieChart = (PieChart) findViewById(R.id.piechart2);
        pieChart.setUsePercentValues(true);

        Log.d("Scene Contra","katta scene 2");

        String fromdate =getIntent().getExtras().getString("from date");
        Log.d("Scene Contra","katta scene 2" + fromdate);
        String todate =getIntent().getExtras().getString("to date");
        Log.d("Scene Contra","katta scene 2"+todate);

        Log.d("Scene Contra","katta scene 3");



        List<String> result = new ArrayList<String>();

        int d,i,e,j,f,k=0,sum=0;


        Log.d("Scene Contra","katta scene before convert");
        startDateNumber=convert(fromdate);
        endDateNumber=convert(todate);

        Log.d("Scene Contra","katta scene after cpnvert");
        result = repeatDaily(startDateNumber[1],startDateNumber[0],startDateNumber[2],endDateNumber[1],endDateNumber[0],endDateNumber[2],days);
        if(result.size()==0){
            Toast.makeText(this, "No dates selected",
                    Toast.LENGTH_LONG).show();
        }

        Log.d("Scene Contra","katta scene after result"+ result);
        /*TextView x = (TextView)findViewById(R.id.date1);
        TextView y = (TextView)findViewById(R.id.date2);
        TextView z = (TextView)findViewById(R.id.text_overview);*/

        Log.d("Scene Contra","katta scene 4");
        ArrayList<Entry> yvalues = new ArrayList<>();
        ArrayList<String> CategoryList = new ArrayList<>();

        List<PieDataCat> PieDataList = getPieDataSet(result);
        if(PieDataList.size()!=0) {

            for (i = 0; i < PieDataList.size(); i++) {
                CategoryList.add(PieDataList.get(i).getCat());
                yvalues.add(new Entry(PieDataList.get(i).getDur(), i));
            }

            PieDataSet dataSet = new PieDataSet(yvalues, "Daily Overview");
            Log.d("Scene Contra", "katta scene 7");


            Log.d("Scene Contra", "katta scene 8");


            Log.d("Scene Contra", "katta scene 13");
            PieData data = new PieData(CategoryList, dataSet);
            Log.d("Scene Contra", "katta scene 14");
            data.setValueFormatter(new PercentFormatter());
            pieChart.setData(data);
            pieChart.setDescription("This is Pie Chart");
            pieChart.setDrawHoleEnabled(true);
            pieChart.setTransparentCircleRadius(25f);
            pieChart.setHoleRadius(25f);
            pieChart.setHoleColor(Color.alpha(1));
            Log.d("Scene Contra", "katta scene 15");

            dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
            data.setValueTextSize(10f);
            data.setValueTextColor(Color.DKGRAY);
            pieChart.setOnChartValueSelectedListener(this);

            Log.d("Scene Contra", "katta scene 16");

            pieChart.animateXY(1600, 1600);

        }
        else {
            Toast.makeText(this, "No activities during selected dates.",
                    Toast.LENGTH_LONG).show();
        }




    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }



    public static int[] convert(String numbersArray) {

        // date string to intiger conversion

        String[] tokens = numbersArray.split("/");
        int[] numbers = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = Integer.parseInt(tokens[i]);
        }
        return numbers;
    }


    public static List repeatDaily(int d, int m, int y, int endD, int endM, int endY,int[] days) {
        // Function to generate dates for repeat daily option

        List<String> result = new ArrayList<String>();
//        entertodDB(d,m,y);
        String dS = Integer.toString(d);
        String mS = Integer.toString(m);
        String yS = Integer.toString(y);
        String slash = "/";
        String currentDate = mS + slash + dS + slash + yS;
        //result = String.join(",", currentDate);
        //result = append(result, currentDate);
        result.add(currentDate);
        boolean ly;
        ly=checkLYear(y);
        if(ly==true)
        {
            days[1]=29;
        }
        else
        {
            days[1]=28;
        }
        while (y<=endY){
            while(m<=12){
                if((d==endD)&&(m==(endM))){
                    //System.out.print("Break");
                    break;

                }
                else{
                    if(d<days[m-1]){
                        d=d+1;
                    }
                    else if(d>=days[m-1]){
                        d=1;
                        if(m<12)
                        {
                            m=m+1;
                        }
                        else
                        {
                            m=1;
                            d=1;
                            y=y+1;
                            ly=checkLYear(y);
                            if(ly==true)
                            {
                                days[1]=29;
                            }
                            else
                            {
                                days[1]=28;
                            }
                        }
                    }

//                    entertodDB(d,m,y);
                    dS = Integer.toString(d);
                    mS = Integer.toString(m);
                    yS = Integer.toString(y);
                    slash = "/";
                    currentDate = mS + slash + dS + slash + yS;
                    result.add(currentDate);
                }
            }//while(m<=12)
            if((d==endD)&&(m==(endM))){
                //System.out.print("Break");
                break;

            }
        }//while (y<=endY)
        System.out.println("");
        return result;
    }//repeatDaily

    public static boolean checkLYear(int y) {
        // function to check leap year

        int year = y;
        boolean leap = false;
        if(year % 4 == 0)
        {
            if( year % 100 == 0)
            {
                // year is divisible by 400, hence the year is a leap year
                if ( year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }
            else
                leap = true;
        }
        else
            leap = false;
        return leap;
    }

    public List<PieDataCat> getPieDataSet(List<String> result1){

        DatabaseHelper myDB = new DatabaseHelper(this);
        int i;
        float tempdur;
        int p;


        List<Activityy> mActList = new ArrayList<>();
        List<PieDataCat> mPiedDataCat = new ArrayList<>();
        List<String> tempCat = new ArrayList<>();
        List<Activityy> tempList = new ArrayList<>();

        for(i=0;i<result1.size();i++){
            tempList.clear();
            Log.d("Scene Contra","GetCAt by date");

            tempList=myDB.getActivitiesByDate((result1.get(i)));
            Log.d("Scene Contra","aftergetactivity: "+ tempList.size());
            //Log.d("Scene Contra","Temp List"+ tempList.get(i).getDuration());
            if (tempList.size()!=0) {
                for (int x = 0; x < tempList.size(); x++) {
                    mActList.add(tempList.get(x));
                    // Log.d("Scene Contra","Cat Add: "+ mActList.get(i).getDuration());

                }
            }

            //Log.d("Scene Contra","GetCAt by date"+ mActList.get(i).getDuration());
        }


        tempCat.clear();
        for(i=0;i<mActList.size();i++) {
            tempdur=0;
            if(!tempCat.contains( mActList.get(i).getCategory())){
                tempCat.add(mActList.get(i).getCategory());
                for(p=0;p<mActList.size();p++){
                    if(mActList.get(i).getCategory()==mActList.get(p).getCategory()){
                        Log.d("Scene Contra","Preshnam: "+ mActList.get(p).getDuration());
                        tempdur=tempdur+Float.parseFloat(mActList.get(p).getDuration());
                    }}
                    mPiedDataCat.add(new PieDataCat(mActList.get(i).getCategory(),tempdur));


            }
        }
        return mPiedDataCat;
    }


}


