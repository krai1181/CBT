package com.avi.project_cbt.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avi.project_cbt.R;
import com.avi.project_cbt.adapter.Dialog_adapter;
import com.avi.project_cbt.adapter.VisualizerView;
import com.avi.project_cbt.database_cbt.DatabaseTables;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PersonalNotesActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRecor, btnTxtNotes, btnBackNotes;
    ImageButton btnEditNotes, imgRecordings;
    Intent intent;
    public static boolean pushButton = false, pushButton2 = false, isPushButton = true;
    public static float dp;
    public static String TAG = "myLog";


    public static final String DIRECTORY_NAME_TEMP = "AudioTemp";
    public static final int REPEAT_INTERVAL = 40;
    private TextView txtRecord;

    public static VisualizerView visualizerView;

    private boolean isRecording = false;

    public static Handler handler; // Handler for updating the visualizer

    public static String mFileName = null;
    public static MediaRecorder mRecorder = null;
    public static MediaPlayer mPlayer = null;

    public static long date;

    RecyclerView recyclerRecord;
    static Dialog_adapter dialog_adapter;
    static ArrayList<String> arrayList;
    File folder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_notes);
        dp = getResources().getDisplayMetrics().density;

        btnRecor = (Button) findViewById(R.id.btnRecor);
        btnTxtNotes = (Button) findViewById(R.id.btnTxtNotes);
        btnBackNotes = (Button) findViewById(R.id.btnBackNotes);
        btnEditNotes = (ImageButton) findViewById(R.id.btnEditNotes);
        imgRecordings = (ImageButton) findViewById(R.id.imgRecordings);
        recyclerRecord = (RecyclerView) findViewById(R.id.recyclerRecord);
        visualizerView = (VisualizerView) findViewById(R.id.visualizerView);

        btnRecor.setOnClickListener(this);
        btnTxtNotes.setOnClickListener(this);
        btnBackNotes.setOnClickListener(this);
        btnEditNotes.setOnClickListener(this);
        imgRecordings.setOnClickListener(this);


        arrayList = new ArrayList<String>();
        dialog_adapter = new Dialog_adapter(arrayList, "PersonalNotesActivity");
        Log.d(TAG, "onCreate: " + getClass());
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerRecord.setLayoutManager(llm);
        recyclerRecord.setAdapter(dialog_adapter);

        handler = new Handler();
    }


    public static void startPlaying() {
        Log.d(TAG, "startPlaying: " + dialog_adapter.arrayList1.toString());
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    public static void stopPlaying() {
        Log.d(TAG, "stopPlaying: " + dialog_adapter.arrayList1.toString());
        mPlayer.release();
        mPlayer = null;
    }

    public static void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        MediaRecorder.OnErrorListener errorListener = null;
        mRecorder.setOnErrorListener(errorListener);
        MediaRecorder.OnInfoListener infoListener = null;
        mRecorder.setOnInfoListener(infoListener);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        mRecorder.start();

    }

    public static void Play() {
        Log.d(TAG, "Play: ");
        if (false) {
            stopPlaying();
            Log.d(TAG, "Play: stop");
        } else {
            startPlaying();
            Log.d(TAG, "Play: start");
        }
        pushButton2 = !pushButton2;
    }

    public static void stopRecording() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;

        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


    public  PersonalNotesActivity() {
        folder = new File(Environment.getExternalStorageDirectory() + "/CBTwithyou");
        boolean haveDirectory = true;

        if (!folder.exists()) {
              folder.mkdir();
            folder.mkdirs();
        } else {
            deleteFilesInDir(folder);
        }
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }


    private void releaseRecorder() {
        if (mRecorder != null) {
            //isRecording = false; // stop recording
            pushButton = false; // stop recording
            handler.removeCallbacks(updateVisualizer);
            visualizerView.clear();
            mRecorder.stop();
            mRecorder.reset();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public static boolean deleteFilesInDir(File path) {

        if (path.exists()) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for (int i = 0; i < files.length; i++) {

                if (files[i].isDirectory()) {

                } else {
                    files[i].delete();
                }
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseRecorder();
    }

    // updates the visualizer every 50 milliseconds
    static Runnable updateVisualizer = new Runnable() {
        @Override
        public void run() {
            if (pushButton) // if we are already recording
            {
                // get the current amplitude
                int x = mRecorder.getMaxAmplitude();
                visualizerView.addAmplitude(x); // update the VisualizeView
                visualizerView.invalidate(); // refresh the VisualizerView

                // update in 40 milliseconds
                handler.postDelayed(this, REPEAT_INTERVAL);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTxtNotes:
                intent = new Intent(getApplicationContext(), TextNotesActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l, R.anim.r);
                break;
            case R.id.btnBackNotes:
                intent = new Intent(getApplicationContext(), PanicActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.l, R.anim.r);
                break;
            case R.id.btnEditNotes:
                if (!isPushButton)
                    btnEditNotes.setImageResource(R.drawable.edit_green_u);
                else
                    btnEditNotes.setImageResource(R.drawable.edit_blue_u);
                dialog_adapter.notifyDataSetChanged();
                isPushButton = !isPushButton;
                break;
            case R.id.imgRecordings:
                date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String dateString = sdf.format(date);
                if (pushButton) {
                    imgRecordings.setImageResource(R.drawable.microphone_u);
                    stopRecording();
                     releaseRecorder();
                    addbtn(dateString);
                    visualizerView.setLayoutParams(new LinearLayout.LayoutParams((int) (400 * PersonalNotesActivity.dp), (int) (0 * PersonalNotesActivity.dp)));

                } else {
                    imgRecordings.setImageResource(R.drawable.microphone_alt_d);
                    startRecording();
                    visualizerView.setLayoutParams(new LinearLayout.LayoutParams((int) (400 * PersonalNotesActivity.dp), (int) (75 * PersonalNotesActivity.dp)));

                    handler.post(updateVisualizer);
                }
                pushButton = !pushButton;

               /* if (!isRecording) {
                    // isRecording = true;
                    visualizerView.setLayoutParams(new LinearLayout.LayoutParams((int) (220 * PersonalNotesActivity.dp), (int) (75 * PersonalNotesActivity.dp)));

                    mRecorder = new MediaRecorder();

                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mRecorder.setOutputFile(mFileName);


                    MediaRecorder.OnErrorListener errorListener = null;
                    mRecorder.setOnErrorListener(errorListener);
                    MediaRecorder.OnInfoListener infoListener = null;
                    mRecorder.setOnInfoListener(infoListener);

                    try {
                        mRecorder.prepare();
                        mRecorder.start();
                        isRecording = true; // we are currently recording
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    handler.post(updateVisualizer);

                } else {
                    visualizerView.setLayoutParams(new LinearLayout.LayoutParams((int) (220 * PersonalNotesActivity.dp), (int) (0 * PersonalNotesActivity.dp)));
                    releaseRecorder();
                }
                isRecording = !isRecording;*/
        }
    }


    public static void addbtn(String string) {
        arrayList.add(string);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTables.columnSTR, string);
        EventActivity.sqLiteDatabase.insert(DatabaseTables.PERSRECORD + EventActivity.numberActivity, null, cv);
        dialog_adapter.notifyDataSetChanged();
    }

    public static void deletebtn(String sting) {
        arrayList.remove(sting);
        dialog_adapter.notifyDataSetChanged();
    }

}
