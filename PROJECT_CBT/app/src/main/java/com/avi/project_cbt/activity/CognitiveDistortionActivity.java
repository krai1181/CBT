package com.avi.project_cbt.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avi.project_cbt.R;
import com.avi.project_cbt.adapter.Button_adapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CognitiveDistortionActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBack3;
    LinearLayout.LayoutParams params;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    boolean b = true, a = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cognitive_distortion);

        btnBack3 = (Button) findViewById(R.id.btnBack3);

        String[] stringArray1 = getResources().getStringArray(R.array.text_button);
        String[] stringArray2 = getResources().getStringArray(R.array.text_view);



        Button_adapter button_adapter = new Button_adapter(stringArray1,stringArray2);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerV);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(button_adapter);

        btnBack3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack3:
                Intent intent = new Intent(getApplicationContext(),PanicActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;

        }
        }
    }

