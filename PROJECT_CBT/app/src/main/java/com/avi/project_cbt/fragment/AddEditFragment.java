package com.avi.project_cbt.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.AutomaticThougntsActivity;
import com.avi.project_cbt.activity.BehaviorActivity;
import com.avi.project_cbt.activity.EmotionsActivity;
import com.avi.project_cbt.activity.EventActivity;
import com.avi.project_cbt.activity.HelpfulThoughtsActivity;
import com.avi.project_cbt.activity.TextNotesActivity;

import java.util.ArrayList;

public class AddEditFragment extends DialogFragment {
    public static Button btnDoneTitleNote, btnCancelTitleNote;
    public static EditText ETNote;
    Context context;
    public static ArrayList<String> stringArrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_edit, container, false);

        ETNote = (EditText) rootView.findViewById(R.id.ETNote);
        btnCancelTitleNote = (Button) rootView.findViewById(R.id.btnCancelTitleNote);
        btnDoneTitleNote = (Button) rootView.findViewById(R.id.btnDoneTitleNote);

        stringArrayList = new ArrayList<>();

        btnCancelTitleNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });

        btnDoneTitleNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ETNote.getText().toString().isEmpty()) {
                    getDialog().cancel();
                } else {
                    stringArrayList.add(ETNote.getText().toString());
                }
                getDialog().cancel();
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

    public static AddEditFragment newInstats(String stringTextView, Context context) {
        AddEditFragment fragment = new AddEditFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}