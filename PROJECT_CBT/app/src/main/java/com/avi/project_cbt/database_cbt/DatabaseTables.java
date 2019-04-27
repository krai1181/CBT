package com.avi.project_cbt.database_cbt;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.PanicActivity;
import com.avi.project_cbt.adapter.Dialog_adapter;

import java.security.PublicKey;

/**
 * Created by Женя on 10.10.2016.
 */
public class DatabaseTables {
    public static String columnSTR = "TextString";
    public static String columnSTR2 = "TextString2";
    public static String columnINT = "TextInt";
    public static String columnKEY = "_Key_ID";

    public static String AUTOTHOUGHTS = "autothoughts";
    public static String HELPTHOUGHTS = "helpthoughts";
    public static String EMOTIONS = "emotions";
    public static String PERSRECORD = "persrecord";
    public static String PERSTEXT = "perstext";
    public static String HELPBEHAVIOR = "helpbehavior";
    public static String INEFFBEHAVIOR = "ineffbehavior";
    public static String nameOfEvent = null;


    public static void addEvent(DatabaseCBT databaseCBT, SQLiteDatabase sqLiteDatabase, String nameOfEvent, int key, Resources res)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseCBT.EditTextString, nameOfEvent);
        contentValues.put(databaseCBT.KEY_Id, key);
        sqLiteDatabase.insert(databaseCBT.Table_CBT, null, contentValues);

        sqLiteDatabase.execSQL("create table " + AUTOTHOUGHTS + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + HELPTHOUGHTS + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + EMOTIONS + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + columnINT + " integer" + ")");
        sqLiteDatabase.execSQL("create table " + PERSRECORD + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + PERSTEXT + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + columnSTR2 + " text" + ")");
        sqLiteDatabase.execSQL("create table " + HELPBEHAVIOR + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + INEFFBEHAVIOR + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");

    }



    public static void addEventPanic(DatabaseCBT databaseCBT, SQLiteDatabase sqLiteDatabase, Resources res)
    {
        nameOfEvent = res.getString(R.string.panic_attack_example);
        int key = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseCBT.EditTextString, nameOfEvent);
        contentValues.put(databaseCBT.KEY_Id, key);
        sqLiteDatabase.insert(databaseCBT.Table_CBT, null, contentValues);

        sqLiteDatabase.execSQL("create table " + AUTOTHOUGHTS + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + HELPTHOUGHTS + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + EMOTIONS + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + columnINT + " integer" + ")");
        sqLiteDatabase.execSQL("create table " + PERSRECORD + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + PERSTEXT + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + columnSTR2 + " text" + ")");
        sqLiteDatabase.execSQL("create table " + HELPBEHAVIOR + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");
        sqLiteDatabase.execSQL("create table " + INEFFBEHAVIOR + key + "(" + columnKEY + " integer primary key,"
                + columnSTR + " text" + ")");


        // add string in DB AUTOTHOUGHTS
        String array[] = res.getStringArray(R.array.authomatic);
        for (int i = 0; i < array.length; i++) {
            contentValues = new ContentValues();
            contentValues.put(columnSTR, array[i]);
            sqLiteDatabase.insert(AUTOTHOUGHTS + key, null, contentValues);
        }
        // add strings in DB HELPTHOUGHTS
        String array1[] = res.getStringArray(R.array.helpful_thuog);
        for (int i = 0; i < array1.length; i++) {
            contentValues = new ContentValues();
            contentValues.put(columnSTR, array1[i]);
            sqLiteDatabase.insert(HELPTHOUGHTS + key, null, contentValues);
        }

        // add strings in DB PERSTEXT
        String array4[] = res.getStringArray(R.array.text_not);
        String array41[] = res.getStringArray(R.array.letter_to_my_self);
        for (int i = 0; i < array4.length; i++) {
            contentValues = new ContentValues();
            contentValues.put(columnSTR, array4[i]);
            contentValues.put(columnSTR2, array41[i]);
            sqLiteDatabase.insert(PERSTEXT + key,null,contentValues);
        }

        // add strings in DB HELPBEHAVIOR
        String array5[] = res.getStringArray(R.array.helpful_behr);
        for (int i = 0; i < array5.length; i++) {
            contentValues = new ContentValues();
            contentValues.put(columnSTR, array5[i]);
            sqLiteDatabase.insert(HELPBEHAVIOR + key, null, contentValues);
        }
        // add strings in DB INEFFBEHAVIOR
        String array6[] = res.getStringArray(R.array.ineffective_beh);
        for (int i = 0; i < array6.length; i++) {
            contentValues = new ContentValues();
            contentValues.put(columnSTR, array6[i]);
            sqLiteDatabase.insert(INEFFBEHAVIOR + key, null, contentValues);
        }
        // add strings in DB ENOTIONS
        String array7[] = res.getStringArray(R.array.emotions);
        for(int i=0;i<array7.length;i++){
            contentValues = new ContentValues();
            contentValues.put(columnSTR, array7[i]);
            contentValues.put(columnINT, 100);
            sqLiteDatabase.insert(INEFFBEHAVIOR + key, null, contentValues);
        }


    }



}
