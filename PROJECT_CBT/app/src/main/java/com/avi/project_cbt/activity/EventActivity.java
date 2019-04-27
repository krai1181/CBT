package com.avi.project_cbt.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.avi.project_cbt.R;
import com.avi.project_cbt.adapter.Dialog_adapter;
import com.avi.project_cbt.database_cbt.DatabaseCBT;
import com.avi.project_cbt.database_cbt.DatabaseTables;
import com.avi.project_cbt.fragment.AddEventFragment;


import java.util.ArrayList;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {
    public static Button btnInfo, btnHistory, btnPanic;
    public static ImageButton btnClean, btnAddTitle;
    Intent intent;
    DialogFragment addEvent;
    RecyclerView recyclerView;
    public static Dialog_adapter dialog_adapter;
    public static ArrayList<String> arrayList;
    public static String TAG = "myLog";
    public static boolean isPushButton = true;
    public static Resources resources;
    public static DatabaseCBT databaseCBT;
    public static SQLiteDatabase sqLiteDatabase;
    public static EventActivity activity;
    public static int numberActivity = 0;
    public static String nameOfEvent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        activity = this;
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnHistory = (Button) findViewById(R.id.btnHistory);

        btnClean = (ImageButton) findViewById(R.id.Cleanbtn);
        btnAddTitle = (ImageButton) findViewById(R.id.AddTitlebtn);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerDialog);


        LinearLayoutManager llm = new LinearLayoutManager(this);

        addEvent = new AddEventFragment();
        btnInfo.setOnClickListener(this);
        btnHistory.setOnClickListener(this);

        btnClean.setOnClickListener(this);
        btnAddTitle.setOnClickListener(this);

        resources = getResources();
        databaseCBT = new DatabaseCBT(this);
        sqLiteDatabase = databaseCBT.getWritableDatabase();
        arrayList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(databaseCBT.Table_CBT, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int string_cursor = cursor.getColumnIndex(databaseCBT.EditTextString);
            do {
                arrayList.add(cursor.getString(string_cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();


        //create table 0 "Panic attack"
       // DatabaseTables.addEvent(databaseCBT,sqLiteDatabase,"event event",1,getResources());


        dialog_adapter = new Dialog_adapter(arrayList, "EventActivity");
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(dialog_adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInfo:
                intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l, R.anim.r);
                break;
            case R.id.btnHistory:
                intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l, R.anim.r);
                break;
            case R.id.Cleanbtn:
                if (!isPushButton)
                    btnClean.setImageResource(R.drawable.edit_green_u);
                else
                    btnClean.setImageResource(R.drawable.edit_blue_u);
                isPushButton = !isPushButton;
                dialog_adapter.notifyDataSetChanged();
                break;
            case R.id.AddTitlebtn:
                btnAddTitle.setImageResource(R.drawable.plus_d);
                addEvent = AddEventFragment.newInstats("Title for New Event", this, 0);
                addEvent.show(getFragmentManager(), "Dialog");
                break;
        }

    }

    public void goAct() {
        intent = new Intent(getApplicationContext(), PanicActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.l, R.anim.r);
    }

    public static void addbtn(String string) {
        arrayList.add(string);
        dialog_adapter.notifyDataSetChanged();

        //if (!string.equals(R.string.panic_attack_example)) {
//
        DatabaseTables.addEvent(databaseCBT,sqLiteDatabase,string,arrayList.size() - 1,resources);
            /*ContentValues contentValues = new ContentValues();
            contentValues.put(databaseCBT.EditTextString, string);
            contentValues.put(databaseCBT.KEY_Id, arrayList.size() - 1);
            int position = arrayList.size() - 1;
            sqLiteDatabase.insert(databaseCBT.Table_CBT, null, contentValues);*/

//         PanicActivity.panicEx.setText(string);

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");

       /* ContentValues contentValues = new ContentValues();
        for(int i=0;i<arrayList.size();i++){
            contentValues.put(databaseCBT.EditTextString, arrayList.get(i));
            sqLiteDatabase.insert(databaseCBT.Table_CBT, null, contentValues);
        }
*/
        Cursor cursor = sqLiteDatabase.query(databaseCBT.Table_CBT, null, null, null, null, null, null, null);

        // read database
        Log.d(TAG, "read database");
        if (cursor.moveToFirst()) {
            int id_cursor = cursor.getColumnIndex(databaseCBT.KEY_Id);
            int string_cursor = cursor.getColumnIndex(databaseCBT.EditTextString);
            do {
                Log.d(TAG, "Id table = " + cursor.getInt(id_cursor)
                        + "name = " + cursor.getString(string_cursor));
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();
        super.onStop();
    }
}
