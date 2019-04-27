package com.avi.project_cbt.adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.AutomaticThougntsActivity;
import com.avi.project_cbt.activity.BehaviorActivity;
import com.avi.project_cbt.activity.EventActivity;
import com.avi.project_cbt.activity.HelpfulThoughtsActivity;
import com.avi.project_cbt.activity.IneffectiveBehaviorActivity;
import com.avi.project_cbt.activity.PanicActivity;
import com.avi.project_cbt.activity.PersonalNotesActivity;
import com.avi.project_cbt.activity.TextNotesActivity;
import com.avi.project_cbt.fragment.AddEditFragment;
import com.avi.project_cbt.fragment.AddEventFragment;

import java.util.ArrayList;

/**
 * Created by еку on 09.06.2016.
 */
public class Dialog_adapter extends RecyclerView.Adapter<Dialog_adapter.ButtonDialog> {

    public ArrayList<String> arrayList1;
    boolean b = true;
    LinearLayout.LayoutParams params;
    LinearLayout linearLayout;
    String string;
    String TAG  = "myLog";


    public Dialog_adapter(ArrayList<String> arrayList1, String string) {
        this.arrayList1 = arrayList1;
        this.string = string;
        Log.d("aaa", " butadapt");
    }

    public static class ButtonDialog extends RecyclerView.ViewHolder {
        ImageButton imagebtnDelete,imageBtnPlay;
        public static TextView textLayout;
        FrameLayout frameLay;
        boolean textiew = false;

        public ButtonDialog(View itemView, String string) {
            super(itemView);
            imagebtnDelete = (ImageButton)itemView.findViewById(R.id.imageBtnDelete);
            imageBtnPlay = (ImageButton)itemView.findViewById(R.id.imageBtnPlay);
            textLayout = (TextView) itemView.findViewById(R.id.txtLeyout);
            frameLay = (FrameLayout)itemView.findViewById(R.id.frameLay);
            if(string.equals("PersonalNotesActivity")){
                textLayout.setGravity(Gravity.CENTER);
            }
        }
    }

    @Override
    public ButtonDialog onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dialog, parent, false);
        Log.d("aaa", " oncrVH");
        ButtonDialog buttonDialog = new ButtonDialog(view, string);
        return buttonDialog;
    }

    @Override
    public void onBindViewHolder(final ButtonDialog holder, final int position) {

        holder.imagebtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!holder.textiew) {
                arrayList1.remove(position);
                notifyDataSetChanged();
                //delete from BD
                int delCount = EventActivity.sqLiteDatabase.delete(EventActivity.databaseCBT.Table_CBT,
                        EventActivity.databaseCBT.KEY_Id + "=" + position,null);

            }
            }
        });

        holder.imageBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.textiew){
                    holder.imageBtnPlay.setImageResource(R.drawable.pause_u);}
                    else
                    holder.imageBtnPlay.setImageResource(R.drawable.play_u);
            }
        });


        holder.textLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (string) {
                    case "EventActivity":
                        EventActivity.numberActivity  = position;
                        Log.d(TAG, "onClick: "+ position);
                        EventActivity.activity.goAct();
                        break;
                    case "HelpfulThoughtsActivity":
                        if(holder.textiew){
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                            holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                        }else{
                      holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_green);
                            holder.imagebtnDelete.setVisibility(View.VISIBLE);
                            holder.imagebtnDelete.setImageResource(R.drawable.check);
                        }holder.textiew = !holder.textiew;
                        break;
                    case "AutomaticThougntsActivity":
                        if(holder.textiew){
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                            holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                        }else{
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_green);
                            holder.imagebtnDelete.setVisibility(View.VISIBLE);
                            holder.imagebtnDelete.setImageResource(R.drawable.check);
                        }holder.textiew = !holder.textiew;
                        break;
                    case "BehaviorActivity":
                        if(holder.textiew){
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                            holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                        }else{
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_green);
                            holder.imagebtnDelete.setVisibility(View.VISIBLE);
                            holder.imagebtnDelete.setImageResource(R.drawable.check);
                        }holder.textiew = !holder.textiew;
                        break;
                    case "PersonalNotesActivity":
                        if(holder.textiew){
                            holder.imageBtnPlay.setBackgroundResource(R.drawable.play_u);
                        }else {
                            holder.frameLay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (60*PersonalNotesActivity.dp)));
                            holder.imageBtnPlay.setVisibility(View.VISIBLE);
                            PersonalNotesActivity.Play();
                        }holder.textiew = !holder.textiew;

                          break;
                    case "TextNotesActivity":
                        if(holder.textiew){
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                            holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                            TextNotesActivity.textScroll.setText(null);
                        }else{
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_green);
                            holder.imagebtnDelete.setVisibility(View.VISIBLE);
                            holder.imagebtnDelete.setImageResource(R.drawable.check);
                            for(String s: AddEditFragment.stringArrayList){
                               TextNotesActivity.textScroll.setText(s);
                            }
                        }holder.textiew = !holder.textiew;
                        break;
                    case "IneffectiveBehaviorActivity":
                        if(holder.textiew){
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                            holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                        }else{
                            holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_green);
                            holder.imagebtnDelete.setVisibility(View.VISIBLE);
                            holder.imagebtnDelete.setImageResource(R.drawable.check);
                        }holder.textiew = !holder.textiew;
                        break;
                }
            }
        });


        holder.textLayout.setText(arrayList1.get(position));
        switch (string) {
            case "EventActivity":
                if (EventActivity.isPushButton) {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.textLayout.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                    holder.textLayout.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.textLayout.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.imagebtnDelete.setImageResource(R.drawable.button_delete_1);
                    holder.imagebtnDelete.setVisibility(View.VISIBLE);
                    holder.textLayout.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.button_delete_1, 0);
                    holder.textLayout.setPadding(20, 0, 40, 0);
                }
                break;
            case "HelpfulThoughtsActivity":
                if (HelpfulThoughtsActivity.isPushButton1) {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                } else {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.imagebtnDelete.setImageResource(R.drawable.button_delete_1);
                    holder.imagebtnDelete.setVisibility(View.VISIBLE);
                }
                break;
            case "AutomaticThougntsActivity":
                if (AutomaticThougntsActivity.isPushButton2) {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                } else {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.imagebtnDelete.setImageResource(R.drawable.button_delete_1);
                    holder.imagebtnDelete.setVisibility(View.VISIBLE);
                }
                break;
            case "BehaviorActivity":
                if (BehaviorActivity.isPushButton3) {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                } else {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.imagebtnDelete.setImageResource(R.drawable.button_delete_1);
                    holder.imagebtnDelete.setVisibility(View.VISIBLE);
                }
                break;
            case "PersonalNotesActivity":
                if (PersonalNotesActivity.isPushButton) {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                } else {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.imagebtnDelete.setImageResource(R.drawable.button_delete_1);
                    holder.imagebtnDelete.setVisibility(View.VISIBLE);
                    holder.textLayout.setGravity(Gravity.TOP);
                }
                break;
            case "TextNotesActivity":
                if (TextNotesActivity.isPushButton5) {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                } else {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.imagebtnDelete.setImageResource(R.drawable.button_delete_1);
                    holder.imagebtnDelete.setVisibility(View.VISIBLE);
                }
                break;
            case "IneffectiveBehaviorActivity":
                if (IneffectiveBehaviorActivity.isPushButton7) {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.imagebtnDelete.setVisibility(View.INVISIBLE);
                } else {
                    holder.frameLay.setBackgroundResource(R.drawable.gradient_button_info_blue);
                    holder.imagebtnDelete.setImageResource(R.drawable.button_delete_1);
                    holder.imagebtnDelete.setVisibility(View.VISIBLE);
                }
                break;
        }

        Log.d("aaa", " onBVH");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }
}
