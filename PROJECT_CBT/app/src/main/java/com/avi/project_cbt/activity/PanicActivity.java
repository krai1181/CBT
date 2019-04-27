package com.avi.project_cbt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avi.project_cbt.R;

public class  PanicActivity extends AppCompatActivity implements View.OnClickListener {
Button btnAutomatic,btnDistortion,btnHelpful,btnEmotion,btnBehavior,btnNotes,btnEnd,btnSend;
   public static TextView panicEx;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        btnAutomatic = (Button)findViewById(R.id.btnAutomatic);
        btnDistortion = (Button)findViewById(R.id.btnDistortion);
        btnHelpful = (Button)findViewById(R.id.btnHelpful);
        btnEmotion = (Button)findViewById(R.id.btnEmotion);
        btnBehavior = (Button)findViewById(R.id.btnBehavior);
        btnNotes = (Button)findViewById(R.id.btnNotes);
        btnEnd = (Button)findViewById(R.id.btnEnd);
        btnSend = (Button)findViewById(R.id.btnSend);
        panicEx = (TextView)findViewById(R.id.PanicEx);

        btnAutomatic.setOnClickListener(this);
        btnDistortion.setOnClickListener(this);
        btnHelpful.setOnClickListener(this);
        btnEmotion.setOnClickListener(this);
        btnBehavior.setOnClickListener(this);
        btnNotes.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAutomatic:
                intent = new Intent(getApplicationContext(),AutomaticThougntsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnDistortion:
                intent = new Intent(getApplicationContext(),CognitiveDistortionActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnHelpful:
                btnHelpful.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.button_new_d,0,0);
                intent = new Intent(getApplicationContext(),HelpfulThoughtsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnEmotion:
                btnEmotion.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.button_emotion_d,0,0);
                intent = new Intent(getApplicationContext(),EmotionsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnBehavior:
                btnBehavior.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.button_behavior_d,0,0);
                intent = new Intent(getApplicationContext(),BehaviorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnNotes:
                btnNotes.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.button_note_d,0,0);
                intent = new Intent(getApplicationContext(),PersonalNotesActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnEnd:
                intent = new Intent(getApplicationContext(),EventActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l,R.anim.r);
                break;
            case R.id.btnSend:
                break;
        }

    }
}
