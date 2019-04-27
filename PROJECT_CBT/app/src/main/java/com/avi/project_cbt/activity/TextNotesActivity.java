package com.avi.project_cbt.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.avi.project_cbt.R;
import com.avi.project_cbt.adapter.Button_adapter;
import com.avi.project_cbt.adapter.Dialog_adapter;
import com.avi.project_cbt.database_cbt.DatabaseCBT;
import com.avi.project_cbt.database_cbt.DatabaseTables;
import com.avi.project_cbt.fragment.AddEventFragment;

import java.util.ArrayList;

public class TextNotesActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG ="myLog" ;
    public static ImageButton btnEditTextNotes, btnAddTextNotes;
    RecyclerView recyclerNotes;
    public static TextView textScroll;

    public static ScrollView scrollViewNote;

    static Dialog_adapter dialog_adapter;
    static ArrayList<String> arrayList5, arrayList51;

    AddEventFragment addEventFragment;
    Intent intent;
    Button btnBackTextNotes,btnRecordings;
    public static boolean isPushButton5 = false;

    DatabaseCBT databaseCBT;
    SQLiteDatabase  liteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_notes);

        btnBackTextNotes = (Button)findViewById(R.id.btnBackTextNotes);
        btnRecordings = (Button)findViewById(R.id.btnRecordings);
        btnEditTextNotes = (ImageButton) findViewById(R.id.btnEditTextNotes);
        btnAddTextNotes = (ImageButton) findViewById(R.id.btnAddTextNotes);
        scrollViewNote = (ScrollView) findViewById(R.id.scrollViewNote);
        textScroll = (TextView)findViewById(R.id.textScroll);

        btnBackTextNotes.setOnClickListener(this);
        btnEditTextNotes.setOnClickListener(this);
        btnAddTextNotes.setOnClickListener(this);
        btnRecordings.setOnClickListener(this);

        recyclerNotes = (RecyclerView)findViewById(R.id.recyclerNotes) ;
        arrayList5 = new ArrayList<String>();
        arrayList51 = new ArrayList<String>();
        dialog_adapter = new Dialog_adapter(arrayList5,"TextNotesActivity");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerNotes.setLayoutManager(llm);
        recyclerNotes.setAdapter(dialog_adapter);

        databaseCBT = new DatabaseCBT(this);
        liteDatabase = databaseCBT.getWritableDatabase();

        Cursor cursor = liteDatabase.query(DatabaseTables.PERSTEXT + EventActivity.numberActivity, null, null, null, null, null, null, null);

        // read database TextNotesActivity
        Log.d(TAG, "read database TextNotesActivity");
        if (cursor.moveToFirst()) {
            Log.d(TAG, "key index="+cursor.getColumnIndex(DatabaseTables.columnKEY)+
                    ", text index="+cursor.getColumnIndex(DatabaseTables.columnSTR)+", text ="+cursor.getColumnIndex(DatabaseTables.columnSTR2));
            int string_cursor = cursor.getColumnIndex(DatabaseTables.columnSTR);
            int string_cursor2 = cursor.getColumnIndex(DatabaseTables.columnSTR2);
            do {
                Log.d(TAG, "name = " + cursor.getString(string_cursor) + "text = " + cursor.getString(string_cursor2));
                arrayList5.add(cursor.getString(string_cursor));
                arrayList51.add(cursor.getString(string_cursor2));
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackTextNotes:
                intent = new Intent(getApplicationContext(), PanicActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l, R.anim.r);
                break;
            case R.id.btnEditTextNotes:
                if(!isPushButton5)
                    btnEditTextNotes.setImageResource(R.drawable.edit_green_u);
                else
                    btnEditTextNotes.setImageResource(R.drawable.edit_blue_u);
                dialog_adapter.notifyDataSetChanged();
                isPushButton5 = !isPushButton5;
                break;
            case R.id.btnAddTextNotes:
                addEventFragment = AddEventFragment.newInstats(getResources().getString(R.string.add_text_notes),this,5);
                btnAddTextNotes.setImageResource(R.drawable.plus_d);
                addEventFragment.show(getFragmentManager(), "Dialog");
                break;
            case R.id.btnRecordings:
                intent = new Intent(getApplicationContext(),PersonalNotesActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
        }
    }
    public static void addbtn(String string){
        arrayList5.add(string);
        arrayList51.add(string);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTables.columnSTR + DatabaseTables.columnSTR2, string);
        EventActivity.sqLiteDatabase.insert(DatabaseTables.PERSTEXT + EventActivity.numberActivity, null, cv);
        dialog_adapter.notifyDataSetChanged();
    }

    public static void deletebtn(String sting)
    {
        arrayList5.remove(sting);
        dialog_adapter.notifyDataSetChanged();
    }

}
