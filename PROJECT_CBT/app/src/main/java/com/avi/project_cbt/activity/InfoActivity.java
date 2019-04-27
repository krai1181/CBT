package com.avi.project_cbt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.EventActivity;
import com.avi.project_cbt.adapter.Button_adapter;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnBack;
    LinearLayout.LayoutParams params;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    boolean b = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        String[] stringArray1 = getResources().getStringArray(R.array.button_info);
        String[] stringArray2 = getResources().getStringArray(R.array.text_info);



        Button_adapter button_adapter = new Button_adapter(stringArray1,stringArray2);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerVInfo);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(button_adapter);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(getApplicationContext(),EventActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.r,R.anim.l);
                break;

        }

    }
}
