package com.avi.project_cbt.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.avi.project_cbt.R;
import com.avi.project_cbt.activity.AutomaticThougntsActivity;
import com.avi.project_cbt.activity.EmotionsActivity;
import com.avi.project_cbt.adapter.Emotion_adapter;

import org.w3c.dom.Text;

import static com.avi.project_cbt.activity.EmotionsActivity.*;

public class AddEmotionFragment extends DialogFragment implements View.OnTouchListener {
    public static Button btnBackTitlePanic, btnOkTitlePanic;
    public static ImageButton imgTitlePanic1;
    public static  TextView txtTitlePanic,txtNamePanic;
    static int prev = 0;
    int position, number;
    static long result = 0; //это результат в %.


    Context context;


    public static AddEmotionFragment newInstanse(int position, int number){
        AddEmotionFragment aef = new AddEmotionFragment();
        aef.position = position;
        aef.number = number;
        return aef;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String tag = this.getTag();
        float dp = getResources().getDisplayMetrics().density;
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.verticalMargin = 15 * dp;
        final View rootView = inflater.inflate(R.layout.fragment_add_emotion, container, false);
        getDialog().getWindow().setAttributes(p);
        btnBackTitlePanic = (Button) rootView.findViewById(R.id.btnBackTitlePanic);
        btnOkTitlePanic = (Button) rootView.findViewById(R.id.btnOkTitlePanic);

        imgTitlePanic1 = (ImageButton ) rootView.findViewById(R.id.imgTitlePanic1);
        btnBackTitlePanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                Log.d("lol", "cancel");
            }
        });

        btnOkTitlePanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawBackgroundArc(270,25,Color.argb(0,0,0,0),4);

                EmotionsActivity.changeParamses(position, number, (int) result);
                getDialog().cancel();
            }
          /*  private Bitmap drawBackgroundArc(int side, int degrees, int color, int ResId){
                Bitmap result = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setColor(color);
                paint.setStyle(Paint.Style.FILL);
                paint.setAntiAlias(true);
                RectF oval = new RectF();
                oval.set(0,0,side,side);
                canvas.drawArc(oval,270, degrees, true,paint);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ResId);
                 canvas.drawCircle(side/2, side/2, bitmap.getWidth(), paint);
                canvas.drawBitmap(bitmap, side/2-bitmap.getWidth()/2, side/2-bitmap.getHeight()/2,paint);
                return result;
            }

            private Bitmap drawBackgroundCircle(int side, int green, int ResId){
                Bitmap result = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setAntiAlias(true);
                paint.setColor(Color.argb(green,0,green, 0));
                canvas.drawCircle(side/2, side/2, side/2, paint);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ResId);
                paint.setColor(Color.rgb(255-green,green, 0));
                canvas.drawCircle(side/2, side/2, bitmap.getWidth(), paint);
                canvas.drawBitmap(bitmap, side/2-bitmap.getWidth()/2, side/2-bitmap.getHeight()/2,paint);
                return result;
            }
*/

          /*  public boolean onTouch(View v, MotionEvent event) {
                int side = imgTitlePanic1.getHeight();
                int action = event.getAction();
                boolean b = true;

                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        double x = event.getX()-side/2;
                        double y = (event.getY()-side/2)*(-1);
                        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                        x = x / r;
                        y = y / r;
                        double degrees = Math.toDegrees(Math.asin(x));
                        int resId=0;
                        if (x>=0 && y>=0) {
                            resId = R.drawable.smile_big_4;
                            if(prev==4) {
                                b = false;
                            }else{
                                prev = 1;
                            }
                        }
                        else if(x>=0 && y<0) {
                            degrees = 180 - degrees;
                            resId = R.drawable.smile_big_3;
                            prev = 2;
                            b = true;
                        }
                        else if(x<0 && y<0) {
                            degrees = 180 - degrees;
                            resId = R.drawable.smile_big_2;
                            prev = 3;
                            b = true;
                        }
                        else if(x<0 && y>=0) {
                            degrees = 360 + degrees;
                            resId = R.drawable.smile_big_1;
                            if(prev==1) {
                                b = false;
                            }else{
                                prev = 4;
                            }
                        }
                        Drawable drawable;
                        long result = 0; //это результат в %.
                        if(b) {
                            result = Math.round(degrees / 3.6);
                            int green = (int) ((degrees / 360) * 255);
                            int red = 255 - green;
                            int color = Color.rgb(red, green, 0);
                            txtTitlePanic.setTextColor(color);
                            Bitmap bitmap = drawBackgroundArc(side,  (int) degrees, color, resId);
                            drawable = new BitmapDrawable(getResources(), bitmap);
                            imgTitlePanic1.setBackground(drawable);
                        }else{
                            if(prev==4){
                                resId = R.drawable.smile_big_1;
                                Bitmap bitmap = drawBackgroundCircle(side, 255, resId);
                                drawable = new BitmapDrawable(getResources(), bitmap);
                                imgTitlePanic1.setBackground(drawable);
                                txtTitlePanic.setTextColor(Color.rgb(0,255,0));
                                result = 100;
                            }
                            if(prev==1){
                                resId = R.drawable.smile_big_4;
                                Bitmap bitmap = drawBackgroundCircle(side, 0, resId);
                                drawable = new BitmapDrawable(getResources(), bitmap);
                                imgTitlePanic1.setBackground(drawable);
                                txtTitlePanic.setTextColor(Color.rgb(255,0,0));
                                result = 0;
                            }
                        }
                        txtTitlePanic.setText(result + "%");
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }

                return false;
            }*/
        });


        txtTitlePanic = (TextView)rootView.findViewById(R.id.txtTitlePanic);
        txtNamePanic =  (TextView) rootView.findViewById(R.id.txtEmotionTitle);
        txtNamePanic.setText(tag);
        imgTitlePanic1.setOnTouchListener(this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emo_2);
        int side = bitmap.getHeight();
        Bitmap result = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.smile_big_1);
        canvas.drawBitmap(bitmap, side/2-bitmap.getWidth()/2, side/2-bitmap.getHeight()/2,paint);
        Drawable drawable = new BitmapDrawable(getResources(), result);
        imgTitlePanic1.setBackground(drawable);
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


    @Override
    public void onStart() {

        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }


    private Bitmap drawBackgroundArc(int side, int degrees, int color, int ResId){
        Bitmap result = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        RectF oval = new RectF();
        oval.set(0,0,side,side);
        canvas.drawArc(oval,270, degrees, true,paint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ResId);
        canvas.drawCircle(side/2, side/2, bitmap.getWidth(), paint);
        canvas.drawBitmap(bitmap, side/2-bitmap.getWidth()/2, side/2-bitmap.getHeight()/2,paint);
        return result;
    }
    private Bitmap drawBackgroundCircle(int side, int green, int ResId){
        Bitmap result = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(green,0,green, 0));
        canvas.drawCircle(side/2, side/2, side/2, paint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ResId);
        paint.setColor(Color.rgb(255-green,green, 0));
        canvas.drawCircle(side/2, side/2, bitmap.getWidth(), paint);
        canvas.drawBitmap(bitmap, side/2-bitmap.getWidth()/2, side/2-bitmap.getHeight()/2,paint);
        return result;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int side = imgTitlePanic1.getHeight();
        int action = event.getAction();

        boolean b = true;
        switch (action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                double x = event.getX()-side/2;
                double y = (event.getY()-side/2)*(-1);
                double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                x = x / r;
                y = y / r;
                double degrees = Math.toDegrees(Math.asin(x));
                int resId=0;
                if (x>=0 && y>=0) {
                    resId = R.drawable.smile_big_4;
                    if(prev==4) {
                        b = false;
                    }else{
                        prev = 1;
                    }
                }
                else if(x>=0 && y<0) {
                    degrees = 180 - degrees;
                    resId = R.drawable.smile_big_3;
                    prev = 2;
                    b = true;
                }
                else if(x<0 && y<0) {
                    degrees = 180 - degrees;
                    resId = R.drawable.smile_big_2;
                    prev = 3;
                    b = true;
                }
                else if(x<0 && y>=0) {
                    degrees = 360 + degrees;
                    resId = R.drawable.smile_big_1;
                    if(prev==1) {
                        b = false;
                    }else{
                        prev = 4;
                    }
                }
                Drawable drawable;

                if(b) {
                    result = Math.round(degrees / 3.6);
                    int green = (int) ((degrees / 360) * 255);
                    int red = 255 - green;
                    int color = Color.rgb(red, green, 0);
                    txtTitlePanic.setTextColor(color);
                    Bitmap bitmap = drawBackgroundArc(side, (int) degrees,color,resId);
                    drawable = new BitmapDrawable(getResources(), bitmap);
                    imgTitlePanic1.setBackground(drawable);
                }else{
                    if(prev==4){
                        resId = R.drawable.smile_big_1;
                        Bitmap bitmap = drawBackgroundCircle(side, 255, resId);
                        drawable = new BitmapDrawable(getResources(), bitmap);
                        imgTitlePanic1.setBackground(drawable);
                        txtTitlePanic.setTextColor(Color.rgb(0,255,0));
                        result = 100;
                    }
                    if(prev==1){
                        resId = R.drawable.smile_big_4;
                        Bitmap bitmap = drawBackgroundCircle(side, 0, resId);
                        drawable = new BitmapDrawable(getResources(), bitmap);
                        imgTitlePanic1.setBackground(drawable);
                        txtTitlePanic.setTextColor(Color.rgb(255,0,0));
                        result = 0;
                    }
                }
                txtTitlePanic.setText(result + "%");
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return false;
    }


}
