package com.example.manish.activitytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.nfc.Tag;
import android.text.format.DateUtils;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

import java.util.List;


import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "mylimm.db";
    public static final String TABLE_NAME = "mylist_data";

    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";
    public static final String COL3 = "ITEM2";
    public static final String COL4 = "ITEM3";
    public static final String COL5 = "ITEM4";
    public static final String COL6 = "ITEM5";
    public static final String COL7 = "ITEM6";
    public static final String COL8 = "ITEM7";
    public static final String COL9 = "ITEM8";

    public static final String category_id = "CATEGORYID";
    public static final String category = "CATEGORY";
    public static final String TABLE_NAME2 = "mylist_catgeory";

    public static final String category1_id = "CATEGORY1ID";
    public static final String category1 = "CATEGORY1";
    public static final String colour1 = "COLOUR";
    public static final String TABLE_NAME3 = "mylist_category1";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ITEM1 TEXT, ITEM2 TEXT, ITEM3 TEXT, ITEM4 TEXT, ITEM5 TEXT, ITEM6 TEXT, ITEM7 TEXT, ITEM8 TEXT)";
        db.execSQL(createTable);

        String createTable1 = "CREATE TABLE " + TABLE_NAME2 + " (" + category_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + category + " STRING " + "); ";
        db.execSQL(createTable1);


        String createTable2 = "CREATE TABLE " + TABLE_NAME3 + " (" + category1_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + category1 + " STRING, " + colour1 + " STRING " + "); ";
        db.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public boolean addData(String item1, String item2, String item3, String item4, String item5, String item6, String item7, String item8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3, item2);
        contentValues.put(COL4, item3);
        contentValues.put(COL5, item4);
        contentValues.put(COL6, item5);
        contentValues.put(COL7, item6);
        contentValues.put(COL8, item7);
        contentValues.put(COL9, item8);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }


    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(category, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME2);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getListContents1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        return data;
    }


    public ArrayList<String> getAll1categories() {

        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT DISTINCT category1 FROM " + TABLE_NAME3, null);
        if (data.getCount() > 0) {
            while (data.moveToNext()) {

                String category = data.getString(data.getColumnIndex("CATEGORY1"));
                list.add(category);
            }
        }

        return list;

    }

    public ArrayList<CategorySpinner> getAll1categories1() {

        ArrayList<CategorySpinner> list1 = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Integer temp=Color.parseColor("#80FFFFFF");
        list1.add(new CategorySpinner("Select Category",temp.toString() ));
        Cursor data = db.rawQuery("SELECT  DISTINCT CATEGORY1,COLOUR FROM " + TABLE_NAME3, null);
        if (data.getCount() > 0) {
            while (data.moveToNext()) {

                String category = data.getString(data.getColumnIndex("CATEGORY1"));
                String color = data.getString(data.getColumnIndex("COLOUR"));
                list1.add(new CategorySpinner(category, color));
            }
        }

        return list1;

    }




    public boolean addData1(String item, String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(category1, item);
        contentValues.put(colour1, item2);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME3);

        long result = db.insert(TABLE_NAME3, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Activityy> getContactsByDate(String mDate, String mAct, String mCat) {
        List<Activityy> ActivityList = new ArrayList<>();
        //  Select All Query
        if (mCat=="Select Category"){mCat="";}
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COL5 + " LIKE " + "'%" + mDate + "'"
               + " AND "  + COL3 + " LIKE " + "'%" + mCat + "%'" + " AND "
               + COL2 + " LIKE " + "'%" + mAct + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Activityy activity;
                activity=new Activityy();
                activity.setActivity(cursor.getString(1));
                activity.setCategory(cursor.getString(2));
                activity.setStart(cursor.getString(3));
                activity.setDescription(cursor.getString(4));
                activity.setColour(cursor.getString(8));
                activity.setDuration(cursor.getString(7));
                // Adding contact to list
                ActivityList.add(activity);
            } while (cursor.moveToNext());
        }
        // return contact list
        return ActivityList;
    }


    public List<String> getActivities() {
        List<String> ActivityList = new ArrayList<>();
        //  Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String act=cursor.getString(1);

                // Adding contact to list
                ActivityList.add(act);
            } while (cursor.moveToNext());
        }
        // return contact list
        return ActivityList;
    }


    /*
    public Cursor getlistcontentsdate(String mDate)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COL5 + " LIKE " + "'%" + mDate + "'";
        Cursor data = db.rawQuery(selectQuery, null);
        return data;

        }

*/

    public String getDuration (String mCat,String mdate) {

        ArrayList<Activityy> DurationList = new ArrayList<>();
        int sum=0;
        String result = "";

        //  Select All Query
//        if (mCat=="Select Category"){mCat="";}
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COL3 + " LIKE " + "'%" + mCat + "%'"+ " AND "  + COL5 + " LIKE " + "'%" + mdate + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer dur=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
               /* Activityy activity;
                activity=new Activityy();
//                activity.setCategory(cursor.getString(2));
                activity.setDuration(cursor.getString(6));
                // Adding contact to list
                DurationList.add(activity);
                int i;

                for (i=1 ; i<DurationList.size(); i++)
                {
                    int j = Integer.parseInt((DurationList.get(i)).getDuration());
                    sum = sum + j;

                }

                if (sum!=0){sum = 100/sum;}
                result = Integer.toString(sum);*/

               String duration =cursor.getString(6);
               Integer temp = Integer.parseInt(duration);
               dur=dur+temp;


            } while (cursor.moveToNext());
        }
        // return contact list
        if (dur!=0){dur=((dur*100)/24);}
        result= Integer.toString(dur);
        return result;
    }

    public ArrayList<String> getAllcategories(String mdate) {

        ArrayList<String> list = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT  ITEM2 FROM " + TABLE_NAME+ " WHERE " + COL5 + " LIKE " + "'%" + mdate + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(selectQuery, null);
        if (data.getCount() > 0) {
            while (data.moveToNext()) {
                String category = data.getString(data.getColumnIndex("ITEM2"));
                list.add(category);
            }
        }

        return list;

    }

    public List<Activityy> getActivitiesByDate(String mDate) {
        List<Activityy> ActivityList = new ArrayList<>();
        //  Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COL5 + " LIKE " + "'%" + mDate + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Activityy activity;
                activity=new Activityy();
                activity.setCategory(cursor.getString(2));
                activity.setDuration(cursor.getString(6));
                Log.d("Scene Contra","DB Helper: "+cursor.getString(6)+cursor.getString(2) );

                // Adding contact to list
                ActivityList.add(activity);
            } while (cursor.moveToNext());
        }
        // return contact list
        return ActivityList;
    }

}






