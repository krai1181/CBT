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

public class BehaviorActivity extends AppCompatActivity implements View.OnClickListener {

   public static Button btnBackBehavior,btnIneffective;
  public static   ImageButton btnEditBehavior,btnPlusBehavior;
    Intent intent;
    RecyclerView recyclerViewBehavior;
    AddEventFragment addEventFragment;
    static Dialog_adapter dialog_adapter;
    static ArrayList<String> arrayList3;
    public static boolean isPushButton3 = true;

    SQLiteDatabase liteDatabase;
    DatabaseCBT databaseCBT;

    String TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);

        btnBackBehavior = (Button) findViewById(R.id.btnBackBehavior);
        btnIneffective = (Button) findViewById(R.id.btnIneffective);
        btnEditBehavior = (ImageButton) findViewById(R.id.btnEditBehavior);
        btnPlusBehavior = (ImageButton) findViewById(R.id.btnPlusBehavior);

        recyclerViewBehavior = (RecyclerView) findViewById(R.id.recyclerViewBehavior);

        arrayList3 = new ArrayList<String>();
        dialog_adapter = new Dialog_adapter(arrayList3,"BehaviorActivity");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerViewBehavior.setLayoutManager(llm);
        recyclerViewBehavior.setAdapter(dialog_adapter);

        databaseCBT = new DatabaseCBT(this);
        liteDatabase = databaseCBT.getWritableDatabase();

        Cursor cursor = liteDatabase.query(DatabaseTables.HELPBEHAVIOR + EventActivity.numberActivity, null, null, null, null, null, null, null);

        // read database
        Log.d(TAG, "read database BehaviorActivity");
        if (cursor.moveToFirst()) {
            Log.d(TAG, "key index="+cursor.getColumnIndex(DatabaseTables.columnKEY)+", text index="+cursor.getColumnIndex(DatabaseTables.columnSTR));
            int string_cursor = cursor.getColumnIndex(DatabaseTables.columnSTR);
            do {
                Log.d(TAG, "name = " + cursor.getString(string_cursor));
                arrayList3.add(cursor.getString(string_cursor));
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();


        btnBackBehavior.setOnClickListener(this);
        btnIneffective.setOnClickListener(this);
        btnEditBehavior.setOnClickListener(this);
        btnPlusBehavior.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {switch (v.getId()){
        case R.id.btnBackBehavior:
            intent = new Intent(getApplicationContext(),PanicActivity.class);
            startActivityForResult(intent,0);
            overridePendingTransition(R.anim.l,R.anim.r);
            break;
        case R.id.btnIneffective:
            intent = new Intent(getApplicationContext(),IneffectiveBehaviorActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.l,R.anim.r);
            break;
        case R.id.btnEditBehavior:
            if(!isPushButton3)
                btnEditBehavior.setImageResource(R.drawable.edit_green_u);
            else
                btnEditBehavior.setImageResource(R.drawable.edit_blue_u);
            isPushButton3 = !isPushButton3;
            dialog_adapter.notifyDataSetChanged();
            break;
        case R.id.btnPlusBehavior:
            btnPlusBehavior.setImageResource(R.drawable.plus_d);
            addEventFragment = AddEventFragment.newInstats(getResources().getString(R.string.add_helpful_behavior),this,2);
            addEventFragment.show(getFragmentManager(),"Dialog");
            break;

    }

    }
    public static void addbtn(String string){
        arrayList3.add(string);
       // dialog_adapter.arrayList1.add(string);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTables.columnSTR, string);
        EventActivity.sqLiteDatabase.insert(DatabaseTables.HELPBEHAVIOR + EventActivity.numberActivity, null, cv);
        dialog_adapter.notifyDataSetChanged();
    }
}
