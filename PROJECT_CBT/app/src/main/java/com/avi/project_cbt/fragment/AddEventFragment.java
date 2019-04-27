package com.avi.project_cbt.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.AutomaticThougntsActivity;
import com.avi.project_cbt.activity.BehaviorActivity;
import com.avi.project_cbt.activity.EmotionsActivity;
import com.avi.project_cbt.activity.EventActivity;
import com.avi.project_cbt.activity.HelpfulThoughtsActivity;
import com.avi.project_cbt.activity.IneffectiveBehaviorActivity;
import com.avi.project_cbt.activity.PanicActivity;
import com.avi.project_cbt.activity.TextNotesActivity;
import com.avi.project_cbt.adapter.Button_adapter;
import com.avi.project_cbt.adapter.Dialog_adapter;
import com.avi.project_cbt.database_cbt.DatabaseCBT;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddEventFragment extends DialogFragment {
    public static Button btnBackTitleEvent, btnOkTitleEvent;
    public static EditText editTextTitleEvent;
    Context context;
    String stringTextView;
    DialogFragment addEditFragment;
    TextView txtFragment;


    int i;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        float dp = getResources().getDisplayMetrics().density;
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.verticalMargin = 15 * dp;

        final View rootView = inflater.inflate(R.layout.fragment_add_event, container, false);
        getDialog().getWindow().setAttributes(p);
        btnBackTitleEvent = (Button) rootView.findViewById(R.id.btnBackTitleEvent);
        btnOkTitleEvent = (Button) rootView.findViewById(R.id.btnOkTitleEvent);
        txtFragment = (TextView) rootView.findViewById(R.id.txtFragment);
        txtFragment.setText(stringTextView);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        addEditFragment = new AddEditFragment();


        editTextTitleEvent = (EditText) rootView.findViewById(R.id.editTextTitleEvent);
        btnBackTitleEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                Log.d("lol", "cancel");

            }
        });

        btnOkTitleEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextTitleEvent.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Name must contain at least two characters", Toast.LENGTH_SHORT).show();
                } else {
                    switch (i) {
                        case 0:
                            EventActivity.addbtn(AddEventFragment.editTextTitleEvent.getText().toString());
                            EventActivity.btnAddTitle.setImageResource(R.drawable.plus_u);
                            EventActivity.dialog_adapter.notifyDataSetChanged();
                            break;
                        case 1:
                            AutomaticThougntsActivity.addbtn(AddEventFragment.editTextTitleEvent.getText().toString());
                            AutomaticThougntsActivity.btnPlus2.setImageResource(R.drawable.plus_u);
                            break;
                        case 2:
                            BehaviorActivity.addbtn(AddEventFragment.editTextTitleEvent.getText().toString());
                            BehaviorActivity.btnPlusBehavior.setImageResource(R.drawable.plus_u);
                            break;
                        case 3:
                            HelpfulThoughtsActivity.addbtn(AddEventFragment.editTextTitleEvent.getText().toString());
                            HelpfulThoughtsActivity.btnPlusHelp.setImageResource(R.drawable.plus_u);
                            break;
                        case 4:
                            EmotionsActivity.btnPlusEmotions.setImageResource(R.drawable.plus_u);
                            int size = EmotionsActivity.paramses.size();
                            if (EmotionsActivity.paramses.get(size - 1).name2 == null) {
                                AddEmotionFragment.newInstanse(size - 1, 2);
                            } else
                                AddEmotionFragment.newInstanse(size, 1);

                            break;
                        case 5:
                            addEditFragment = AddEditFragment.newInstats("Title for New Event", context);
                            addEditFragment.show(getFragmentManager(), "Dialog");
                            TextNotesActivity.addbtn(AddEventFragment.editTextTitleEvent.getText().toString());
                            TextNotesActivity.btnAddTextNotes.setImageResource(R.drawable.plus_u);
                            break;
                        case 6:
                            IneffectiveBehaviorActivity.addbtn(AddEventFragment.editTextTitleEvent.getText().toString());
                            IneffectiveBehaviorActivity.btnAddInBehavior.setImageResource(R.drawable.plus_u);
                            break;
                    }
                    getDialog().cancel();
                }


            }
        });

        return rootView;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);


    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public static AddEventFragment newInstats(String stringTextView, Context context, int i) {
        AddEventFragment fragment = new AddEventFragment();
        fragment.context = context;
        fragment.stringTextView = stringTextView;
        fragment.i = i;
        return fragment;
    }

    @Override
    public void onStart() {

        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
