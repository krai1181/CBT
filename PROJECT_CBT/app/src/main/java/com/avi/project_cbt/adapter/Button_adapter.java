package com.avi.project_cbt.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avi.project_cbt.R;

import java.util.ArrayList;

/**
 * Created by еку on 09.06.2016.
 */
public class Button_adapter extends RecyclerView.Adapter<Button_adapter.ButtonText> {

    String[] arrayList1;
    String[] arrayList2;
    boolean b = true, a = true;
    LinearLayout.LayoutParams params;
    LinearLayout linearLayout;


    public Button_adapter( String[] arrayList1,String[] arrayList2) {
        this.arrayList2 = arrayList2;
        this.arrayList1 = arrayList1;
        Log.d("aaa", " butadapt");
    }

    public static class ButtonText extends RecyclerView.ViewHolder{
        ImageButton imageButton;
        TextView textView1,textView2;

        public ButtonText(View itemView) {
            super(itemView);
            imageButton = (ImageButton)itemView.findViewById(R.id.imageBtn);
            textView1 = (TextView)itemView.findViewById(R.id.txt1);
            textView2 = (TextView)itemView.findViewById(R.id.txt2);
            Log.d("aaa", " buttext");

        }
    }
    @Override
    public ButtonText onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);
        ButtonText buttonText = new ButtonText(view);
        Log.d("aaa", " oncrVH");
        return buttonText;
    }

    @Override
    public void onBindViewHolder(final ButtonText holder, int position) {
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b) {
                    if (a) {

                        holder.imageButton .setImageResource(R.drawable.info_gray_u);
                    } else {
                        holder.imageButton.setImageResource(R.drawable.info_green_u);
                    }
                    b = false;
                    params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(16,5,16,5);
                    holder.textView2.setLayoutParams(params);
                }else {
                    if (a) {
                        holder.imageButton.setImageResource(R.drawable.info_gray_d);
                    } else {
                        holder.imageButton.setImageResource(R.drawable.info_green_d);
                    }
                    b = true;
                    params = new LinearLayout.LayoutParams(0,0);
                    holder.textView2.setLayoutParams(params);
                }
            }

        });
        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a) {
                    if (b) {
                        holder.imageButton.setImageResource(R.drawable.info_green_d);
                    } else {
                        holder.imageButton.setImageResource(R.drawable.info_green_u);
                    }
                   linearLayout = (LinearLayout) v.getParent();
                    linearLayout.setBackgroundResource(R.drawable.gradient_button_info_green);
                    holder.textView1.setTypeface(Typeface.DEFAULT_BOLD);
                    a = false;
                }else {
                    if (b) {
                        holder.imageButton.setImageResource(R.drawable.info_gray_d);
                    } else {
                        holder.imageButton.setImageResource(R.drawable.info_gray_u);
                    }
                    linearLayout = (LinearLayout) v.getParent();
                    linearLayout.setBackgroundResource(R.drawable.gradient_button_info_grey);
                    holder.textView1.setTypeface(Typeface.DEFAULT);
                    a = true;
                }
            }
        });
        holder.textView1.setText(arrayList1[position]);
        holder.textView2.setText(arrayList2[position]);

        Log.d("aaa", " onBVH");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return arrayList1.length;
    }
}
