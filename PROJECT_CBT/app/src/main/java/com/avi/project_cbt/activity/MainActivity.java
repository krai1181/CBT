package com.avi.project_cbt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.EventActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   ImageButton centerBtn, settingsBtn;
    ImageView imgCbt;
    Intent intent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centerBtn = (ImageButton)findViewById(R.id.CenterBtn);
        settingsBtn = (ImageButton)findViewById(R.id.SettingsBtn);
        imgCbt = (ImageView)findViewById(R.id.imgCbt);


        centerBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        imgCbt.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CenterBtn:
                centerBtn.setImageResource(R.drawable.logo_button_dw);
                intent = new Intent(getApplicationContext(),EventActivity.class);
                startActivityForResult(intent,0);
                overridePendingTransition(R.anim.l,R.anim.r);
                finish();
                break;
            case R.id.SettingsBtn:
                settingsBtn.setImageResource(R.drawable.settings_d);
                break;
            case R.id.imgCbt:
                intent = new Intent(getApplicationContext(),EventActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                finish();

        }
    }
}
