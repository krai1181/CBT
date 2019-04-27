package com.avi.project_cbt.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
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

public class AutomaticThougntsActivity extends AppCompatActivity implements View.OnClickListener {
   public static Button btnBackThoughts;
   public static ImageButton btnEdit2,btnPlus2;
    Intent intent;
    RecyclerView recyclerViewAutomatic;
    AddEventFragment addEventFragment;
    static Dialog_adapter dialog_adapter;
    static ArrayList<String> arrayList2;
    public static boolean isPushButton2 = true;
    public static Context context;

    SQLiteDatabase liteDatabase;
    DatabaseCBT databaseCBT;

    String TAG = "myLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_thougnts);

        btnBackThoughts = (Button)findViewById(R.id.btnBackThoughts);
        btnEdit2 = (ImageButton)findViewById(R.id.btnEdit2);
        btnPlus2 = (ImageButton)findViewById(R.id.btnPlus2);

        recyclerViewAutomatic = (RecyclerView)findViewById(R.id.recyclerViewAutomatic);

        databaseCBT = new DatabaseCBT(this);
        liteDatabase = databaseCBT.getWritableDatabase();

        arrayList2 = new ArrayList<String>();
        Cursor cursor = liteDatabase.query(DatabaseTables.AUTOTHOUGHTS+EventActivity.numberActivity, null, null, null, null, null, null, null);

        // read database
        Log.d(TAG, "read database AuthomaticThought");
        if (cursor.moveToFirst()) {
           // int id_cursor = cursor.getColumnIndex(databaseCBT.KEY_Id);
            Log.d(TAG, "key index="+cursor.getColumnIndex(DatabaseTables.columnKEY)+", text index="+cursor.getColumnIndex(DatabaseTables.columnSTR));
            int string_cursor = cursor.getColumnIndex(DatabaseTables.columnSTR);
            do {
                Log.d(TAG, "name = " + cursor.getString(string_cursor));
                arrayList2.add(cursor.getString(string_cursor));
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();


        dialog_adapter = new Dialog_adapter(arrayList2,"AutomaticThougntsActivity");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerViewAutomatic.setLayoutManager(llm);
        recyclerViewAutomatic.setAdapter(dialog_adapter);



        btnBackThoughts.setOnClickListener(this);
        btnEdit2.setOnClickListener(this);
        btnPlus2.setOnClickListener(this);

        context = this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlus2:
                addEventFragment = AddEventFragment.newInstats("Add Automatic Thought",this,1);
                btnPlus2.setImageResource(R.drawable.plus_d);
                addEventFragment.show(getFragmentManager(), "Dialog");
                break;
            case R.id.btnBackThoughts:
                intent = new Intent(getApplicationContext(),PanicActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
            break;
            case R.id.btnEdit2:
                if(!isPushButton2)
                    btnEdit2.setImageResource(R.drawable.edit_green_u);
                else
                    btnEdit2.setImageResource(R.drawable.edit_blue_u);
                dialog_adapter.notifyDataSetChanged();
                isPushButton2 = !isPushButton2;
                break;
        }

    }
    public static void addbtn(String string){
        arrayList2.add(string);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTables.columnSTR, string);
        EventActivity.sqLiteDatabase.insert(DatabaseTables.AUTOTHOUGHTS + EventActivity.numberActivity, null, cv);
        dialog_adapter.notifyDataSetChanged();
    }
}
