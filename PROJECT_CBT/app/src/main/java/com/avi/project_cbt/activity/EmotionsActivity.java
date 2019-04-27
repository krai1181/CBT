package com.avi.project_cbt.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
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

import com.avi.project_cbt.adapter.Emotion_adapter;
import com.avi.project_cbt.database_cbt.DatabaseCBT;
import com.avi.project_cbt.database_cbt.DatabaseTables;
import com.avi.project_cbt.fragment.AddEmotionFragment;
import com.avi.project_cbt.fragment.AddEventFragment;

import java.util.ArrayList;

public class EmotionsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLog";
    Button btnBackEmotions;
    public static ImageButton btnPlusEmotions, btnSadEmo, btnEditEmotions;
    Intent intent;
    AddEmotionFragment addEmotionFragment;
    AddEventFragment addEventFragment;

    Dialog_adapter dialog_adapter;
    static Emotion_adapter emotion_adapter;
    RecyclerView recyclerViewEmo;
    public static ArrayList<Emotion_adapter.Items> paramses;

    public static boolean isPushButton3 = true;
    public static Context context;
    public static android.app.FragmentManager fragmentManager;
    static Resources res;
    ArrayList<String> strings;
    ArrayList<Integer> integers;

    DatabaseCBT databaseCBT;
    SQLiteDatabase liteDatabase;
    static String string;
    static int perscent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);
        res = getResources();
        btnBackEmotions = (Button) findViewById(R.id.btnBackEmotions);
        btnPlusEmotions = (ImageButton) findViewById(R.id.btnPlusEmotions);
        btnEditEmotions = (ImageButton) findViewById(R.id.btnEditEmotions);

        context = this;
        fragmentManager = getFragmentManager();

        paramses = new ArrayList<Emotion_adapter.Items>();
        Emotion_adapter.Items item1 = new Emotion_adapter.Items("excited", 20, "fan", 45);
        Emotion_adapter.Items item2 = new Emotion_adapter.Items("happy", 74, "sad", 100);
        paramses.add(item1);
        paramses.add(item2);



        emotion_adapter = new Emotion_adapter(paramses);
        recyclerViewEmo = (RecyclerView) findViewById(R.id.recyclerViewEmo);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerViewEmo.setLayoutManager(manager);
        recyclerViewEmo.setAdapter(emotion_adapter);
        emotion_adapter.notifyDataSetChanged();

        strings  = new ArrayList<String>();
        integers = new ArrayList<Integer>();

        databaseCBT = new DatabaseCBT(this);
        liteDatabase = databaseCBT.getWritableDatabase();

        Cursor cursor = liteDatabase.query(DatabaseTables.EMOTIONS + EventActivity.numberActivity,null,null,null,null,null,null,null);

        // read database TextNotesActivity
        Log.d(TAG, "read database TextNotesActivity");
        if (cursor.moveToFirst()) {
            Log.d(TAG, "key index="+cursor.getColumnIndex(DatabaseTables.columnKEY)+
                    ", text index="+cursor.getColumnIndex(DatabaseTables.columnSTR));
            int string_cursor = cursor.getColumnIndex(DatabaseTables.columnSTR);
            int int_cursor = cursor.getColumnIndex(DatabaseTables.columnINT);
            do {
                Log.d(TAG, "name = " + cursor.getString(string_cursor));
                strings.add(cursor.getString(string_cursor));
                integers.add(cursor.getInt(int_cursor));
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();
        btnBackEmotions.setOnClickListener(this);
        btnPlusEmotions.setOnClickListener(this);
        btnEditEmotions.setOnClickListener(this);
    }
    

    public  static void changeParamses(int position, int number, int percent){
        if(number==1){
            emotion_adapter.itemses.get(position).percent1 = percent ;
        }else if(number==2){
            emotion_adapter.itemses.get(position).percent2 = percent;
        }
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTables.columnSTR, string);
        cv.put(DatabaseTables.columnINT,  percent);
        EventActivity.sqLiteDatabase.insert(DatabaseTables.EMOTIONS + EventActivity.numberActivity, null, cv);

        emotion_adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackEmotions:
                intent = new Intent(getApplicationContext(), PanicActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPlusEmotions:

                btnPlusEmotions.setImageResource(R.drawable.plus_d);
                addEventFragment = AddEventFragment.newInstats(getResources().getString(R.string.add_emotion), this, 4);
                addEventFragment.show(getFragmentManager(), "Dialog");


                break;

            case R.id.btnEditEmotions:
                btnEditEmotions.setImageResource(R.drawable.edit_green_d);
                break;

        }

    }

    public static Drawable drawBackgroundArc(int percent) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(res, R.drawable.emo_1);
        int side = bitmap1.getHeight();
        float degrees = (float) (percent * 3.6);
        int color = Color.rgb((int) (255 - (percent * 2.55)), (int) (percent * 2.55), 0);
        Log.d("myLog", "red="+(int) (255 - (percent * 2.55))+"green"+(int) (percent * 2.55)+"deg="+degrees);
        int ResId = 0;

        if (percent >= 0 && percent < 25) {
            ResId = R.drawable.smile_4;
        } else if (percent >= 25 && percent < 50) {
            ResId = R.drawable.smile_3;
        } else if (percent >= 50 && percent < 75) {
            ResId = R.drawable.smile_2;
        } else if (percent >= 75 && percent <= 100) {
            ResId = R.drawable.smile_1;
        }

        Bitmap result = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        RectF oval = new RectF();
        oval.set(0, 0, side, side);
        canvas.drawArc(oval, 270, degrees, true, paint);
        Log.d("myLog", "drawBackgroundArc: " + ResId);
        Bitmap bitmap = BitmapFactory.decodeResource(res, ResId);
        canvas.drawCircle(side / 2, side / 2, bitmap.getWidth(), paint);
        canvas.drawBitmap(bitmap, side / 2 - bitmap.getWidth() / 2, side / 2 - bitmap.getHeight() / 2, paint);
        Drawable drawable;
        drawable = new BitmapDrawable(res, result);
        return drawable;
    }

    public static void addbtnEmotion() {
        emotion_adapter.notifyDataSetChanged();
    }

}
