package com.avi.project_cbt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.EventActivity;
import com.avi.project_cbt.adapter.SimpleEMail;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
Button btnBack2, btnSend;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnBack2 = (Button) findViewById(R.id.btnBack2);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnBack2.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack2:
                intent = new Intent(getApplicationContext(),EventActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.r,R.anim.l);
                break;
            case R.id.btnSend:
                intent = new Intent(getApplicationContext(),SimpleEMail.class);
                startActivity(intent);
                overridePendingTransition(R.anim.r,R.anim.l);
                break;

        }
    }
}
