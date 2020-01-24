package com.example.manish.activitytracker;

import android.graphics.Color;

import java.sql.Time;

public class Activityy {

    private String Category;
    private String Activity;
    private String Description;
    private String Start;
    private String Time;
    private String Duration;
    private String End;
    private String Colour;






    public Activityy(String Cate,String Acte, String Strte,String Des, String Col){
        Category = Cate;
        Activity= Acte;
        Start = Strte;

        Description = Des;
        Colour = Col;
    }


    public Activityy (String Cate,String Acte,String Strte,String Dur)
    {
        Category=Cate;
        Activity= Acte;
        Start = Strte;

       Duration = Dur;





    }

    public Activityy()
    {}

    public Activityy(String Strt)
    {

    Start = Strt;

    }


    public Activityy(String Cat,String Act, String Strt, String Dur ,String Des, String Ti, String en, String col){
        Category = Cat;
        Activity= Act;
        Start = Strt;
        Duration = Dur;
        Description =Des;
        Time = Ti;
        End = en;
        Colour = col;
    }


    public String getColour() {
        return Colour;
    }

    public void setColour(String col) {
        Colour = col;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String ca) {
        Category = ca;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }




    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }


    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String desc) {
        Description= desc;
    }


    public String getTime() {
        return Time;
    }

    public void setTime(String ti) {
        Time = ti;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End= end;
    }



}
