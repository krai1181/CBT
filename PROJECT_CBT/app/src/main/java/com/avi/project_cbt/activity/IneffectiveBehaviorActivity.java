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

import com.avi.project_cbt.R;
import com.avi.project_cbt.adapter.Dialog_adapter;
import com.avi.project_cbt.database_cbt.DatabaseCBT;
import com.avi.project_cbt.database_cbt.DatabaseTables;
import com.avi.project_cbt.fragment.AddEventFragment;

import java.util.ArrayList;

public class IneffectiveBehaviorActivity extends AppCompatActivity implements View.OnClickListener {

    public static Button btnHelInBehavior, btnBackInBehavior;
    public static ImageButton btnEditInBehavior, btnAddInBehavior;
    RecyclerView recyclerInefViewBehavior;

    Intent intent;
    static Dialog_adapter dialog_adapter;
    AddEventFragment addEventFragment;

    ArrayList<String> list;
    String TAG = "myLog";
    public static boolean isPushButton7 = true;

    DatabaseCBT databaseCBT;
    SQLiteDatabase liteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ineffective_behavior);

        btnHelInBehavior = (Button) findViewById(R.id.btnHelInBehavior);
        btnBackInBehavior = (Button) findViewById(R.id.btnBackInBehavior);
        btnEditInBehavior = (ImageButton) findViewById(R.id.btnEditInBehavior);
        btnAddInBehavior = (ImageButton) findViewById(R.id.btnAddInBehavior);
        recyclerInefViewBehavior = (RecyclerView) findViewById(R.id.recyclerInefViewBehavior);

        list = new ArrayList<String>();
        dialog_adapter = new Dialog_adapter(list, "IneffectiveBehaviorActivity");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerInefViewBehavior.setLayoutManager(llm);
        recyclerInefViewBehavior.setAdapter(dialog_adapter);

        databaseCBT = new DatabaseCBT(this);
        liteDatabase = databaseCBT.getWritableDatabase();

        Cursor cursor = liteDatabase.query(DatabaseTables.INEFFBEHAVIOR+EventActivity.numberActivity, null, null, null, null, null, null, null);

        // read database
        Log.d(TAG, "read database INEFFBEHAVIOR");
        if (cursor.moveToFirst()) {
            Log.d(TAG, "key index="+cursor.getColumnIndex(DatabaseTables.columnKEY)+", text index="+cursor.getColumnIndex(DatabaseTables.columnSTR));
            int string_cursor = cursor.getColumnIndex(DatabaseTables.columnSTR);
            do {
                Log.d(TAG, "name = " + cursor.getString(string_cursor));
                list.add(cursor.getString(string_cursor));
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();

        btnHelInBehavior.setOnClickListener(this);
        btnBackInBehavior.setOnClickListener(this);
        btnEditInBehavior.setOnClickListener(this);
        btnAddInBehavior.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHelInBehavior:
                intent = new Intent(getApplicationContext(), BehaviorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                Log.d(TAG, "onClick: HelpfulBehaviorActivity");
                break;
            case R.id.btnBackInBehavior:
                intent = new Intent(getApplicationContext(), BehaviorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnEditInBehavior:
                if(!isPushButton7)
                    btnEditInBehavior.setImageResource(R.drawable.edit_green_u);
                else
                    btnEditInBehavior.setImageResource(R.drawable.edit_blue_u);
                isPushButton7 = !isPushButton7;
                dialog_adapter.notifyDataSetChanged();
                break;

            case R.id.btnAddInBehavior:
                addEventFragment = AddEventFragment.newInstats(getResources().getString(R.string.ineffective_bahavior),this,6);
                addEventFragment.show(getFragmentManager(),"Dialog");
                break;
        }
    }

    public static void addbtn(String string){
        dialog_adapter.arrayList1.add(string);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTables.columnSTR, string);
        EventActivity.sqLiteDatabase.insert(DatabaseTables.INEFFBEHAVIOR + EventActivity.numberActivity, null, cv);
        dialog_adapter.notifyDataSetChanged();
    }

}
