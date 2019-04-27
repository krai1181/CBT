package com.avi.project_cbt.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.avi.project_cbt.R;
import com.avi.project_cbt.adapter.Dialog_adapter;
import com.avi.project_cbt.database_cbt.DatabaseCBT;
import com.avi.project_cbt.database_cbt.DatabaseTables;
import com.avi.project_cbt.fragment.AddEventFragment;

import java.util.ArrayList;

public class HelpfulThoughtsActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBackHelp;
    public static ImageButton btnEditHelp,btnPlusHelp;
    Intent intent;
    AddEventFragment addEventFragment;
    RecyclerView recyclerViewHelpful;
    static Dialog_adapter dialog_adapter;
    static ArrayList<String> arrayList1;
    public static boolean isPushButton1 = true;
    String TAG = "my Log";

    SQLiteDatabase liteDatabase;
    DatabaseCBT  databaseCBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpful_thoughts);

        btnBackHelp = (Button)findViewById(R.id.btnBackHelp);
        btnEditHelp = (ImageButton)findViewById(R.id.btnEditHelp);
        btnPlusHelp = (ImageButton)findViewById(R.id.btnPlusHelp);

        databaseCBT = new DatabaseCBT(this);
        liteDatabase = databaseCBT.getWritableDatabase();

        recyclerViewHelpful = (RecyclerView)findViewById(R.id.recyclerViewHelpful) ;

        arrayList1 = new ArrayList<String>();
        Cursor cursor = liteDatabase.query(DatabaseTables.HELPTHOUGHTS+EventActivity.numberActivity, null, null, null, null, null, null, null);

        // read database
        Log.d(TAG, "read database  HelpfulThoughts");
        if (cursor.moveToFirst()) {
           // Log.d(TAG, "key index="+cursor.getColumnIndex(DatabaseTables.columnKEY)+", text index="+cursor.getColumnIndex(DatabaseTables.columnSTR));
            int string_cursor = cursor.getColumnIndex(DatabaseTables.columnSTR);
            do {
                Log.d(TAG, "name = " + cursor.getString(string_cursor));
                arrayList1.add(cursor.getString(string_cursor));
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();

        dialog_adapter = new Dialog_adapter(arrayList1,"HelpfulThoughtsActivity");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerViewHelpful.setLayoutManager(llm);
        recyclerViewHelpful.setAdapter(dialog_adapter);






        btnEditHelp.setOnClickListener(this);
        btnPlusHelp.setOnClickListener(this);
        btnBackHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBackHelp:
                intent = new Intent(getApplicationContext(),PanicActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnPlusHelp:
                addEventFragment = AddEventFragment.newInstats(getResources().getString(R.string.add_helpful_throught),this, 3);
                btnPlusHelp.setImageResource(R.drawable.plus_d);
                addEventFragment.show(getFragmentManager(), "Dialog");
                break;
            case R.id.btnEditHelp:
                if(!isPushButton1)
                    btnEditHelp.setImageResource(R.drawable.edit_green_u);
                else
                    btnEditHelp.setImageResource(R.drawable.edit_blue_u);
                    isPushButton1 = !isPushButton1;
                    dialog_adapter.notifyDataSetChanged();
                break;
        }

    }
    public static void addbtn(String string){
        arrayList1.add(string);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTables.columnSTR, string);
        EventActivity.sqLiteDatabase.insert(DatabaseTables.HELPTHOUGHTS + EventActivity.numberActivity, null, cv);
        dialog_adapter.notifyDataSetChanged();
    }
}
