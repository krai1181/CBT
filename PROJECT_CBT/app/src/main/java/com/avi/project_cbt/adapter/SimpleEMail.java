package com.avi.project_cbt.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avi.project_cbt.R;

public class SimpleEMail extends AppCompatActivity {

        TextView send;
        EditText address, subject, emailtext;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_simple_email);

            // Наши поля и кнопка
            send = (TextView) findViewById(R.id.emailsendbutton);
            address = (EditText) findViewById(R.id.emailaddress);
            subject = (EditText) findViewById(R.id.emailsubject);
            emailtext = (EditText) findViewById(R.id.emailtext);

            send.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                    emailIntent.setType("plain/text");
                    // Кому
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                            new String[] { address.getText().toString() });
                    // Зачем
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                            subject.getText().toString());
                    // О чём
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                            emailtext.getText().toString());
                    // С чем
                    emailIntent.putExtra(
                            android.content.Intent.EXTRA_STREAM,
                            Uri.parse("file://"
                                    + Environment.getExternalStorageDirectory()
                                    + "/Клипы/SOTY_ATHD.mp4"));

                    emailIntent.setType("text/video");
                    // Поехали!
                    SimpleEMail.this.startActivity(Intent.createChooser(emailIntent,
                            "Отправка письма..."));
                }
            });
        }
    }
