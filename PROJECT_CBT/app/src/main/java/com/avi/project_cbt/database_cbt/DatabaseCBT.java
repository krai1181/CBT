package com.avi.project_cbt.database_cbt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.avi.project_cbt.activity.EventActivity;

public class DatabaseCBT extends SQLiteOpenHelper {


    public DatabaseCBT(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CBT";
    public static final String Table_CBT = "CBTwithyou";

    public static final String KEY_Id = "_id";
    public static final String EditTextString = "edit";

    public DatabaseCBT(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table 0 "Panic attack"
    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!doesTableExist(db, Table_CBT)) {
            db.execSQL("create table " + Table_CBT + "(" + KEY_Id + " integer primary key,"
                    + EditTextString + " text" + ")");
            DatabaseTables.addEventPanic(this, db, EventActivity.resources);
        }
        Log.d("myLog", "onCreate");

    }

    public boolean doesTableExist(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist " + Table_CBT);

        onCreate(db);
    }


}


