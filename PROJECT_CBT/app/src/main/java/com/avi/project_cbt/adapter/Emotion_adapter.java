package com.avi.project_cbt.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.EmotionsActivity;
import com.avi.project_cbt.fragment.AddEmotionFragment;
import com.avi.project_cbt.fragment.AddEventFragment;

import java.util.ArrayList;

/**
 * Created by Женя on 11.09.2016.
 */
public class Emotion_adapter extends RecyclerView.Adapter<Emotion_adapter.Params> {
    public ArrayList<Items> itemses;
    AddEmotionFragment addEmotionFragment;

    public Emotion_adapter(ArrayList<Items> itemses) {
        this.itemses = itemses;
    }

    @Override
    public Params onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emotion_layout, parent, false);
        Params params = new Params(view);
        return params;
    }

    @Override
    public void onBindViewHolder(Params holder, final int position) {
        holder.txtEmoName1.setText(itemses.get(position).name1);
        holder.txtEmoPercent1.setText(itemses.get(position).percent1 + "%");
        holder.btnImgEmo1.setBackground(EmotionsActivity.drawBackgroundArc(itemses.get(position).percent1));

        //holder.btnImgEmo.setImageDrawable(itemses.get(position).background2);

        if (itemses.get(position).name2 != null) {

            holder.txtEmoName2.setText(itemses.get(position).name2);
            holder.txtEmoPercent2.setText(itemses.get(position).percent2  + "%");
            holder.btnImgEmo2.setBackground(EmotionsActivity.drawBackgroundArc(itemses.get(position).percent2));
            Log.d("myLog", "!=null");
        } else {
            holder.linLayoutEmo.setVisibility(View.INVISIBLE);
        }

        holder.btnImgEmo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotionFragment = AddEmotionFragment.newInstanse(position, 1);
                addEmotionFragment.show(EmotionsActivity.fragmentManager, itemses.get(position).name1);
            }
        });

        holder.btnImgEmo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotionFragment = AddEmotionFragment.newInstanse(position, 2);
                addEmotionFragment.show(EmotionsActivity.fragmentManager, itemses.get(position).name2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemses.size();
    }

    public class Params extends RecyclerView.ViewHolder {
        TextView txtEmoName2;
        TextView txtEmoPercent2;
        ImageView btnImgEmo2;
        TextView txtEmoName1;
        TextView txtEmoPercent1;
        ImageView btnImgEmo1;
        LinearLayout linLayoutEmo;


        public Params(View itemView) {
            super(itemView);
            txtEmoName1 = (TextView) itemView.findViewById(R.id.txtEmoName1);
            txtEmoPercent1 = (TextView) itemView.findViewById(R.id.txtEmoPercent1);
            btnImgEmo1 = (ImageView) itemView.findViewById(R.id.btnImgEmo1);
            txtEmoName2 = (TextView) itemView.findViewById(R.id.txtEmoName2);
            txtEmoPercent2 = (TextView) itemView.findViewById(R.id.txtEmoPercent2);
            btnImgEmo2 = (ImageView) itemView.findViewById(R.id.btnImgEmo2);
            linLayoutEmo = (LinearLayout) itemView.findViewById(R.id.linLayoutEmo);

        }
    }



    public static class Items {
        public String name1;
        public int percent1;
        public String name2;
       public int percent2;


        public Items(String name, int percent,  String name2, int percent2) {
            this.name1 = name;
            this.percent1 = percent;
            this.name2 = name2;
            this.percent2 = percent2;
        }
    }
}
