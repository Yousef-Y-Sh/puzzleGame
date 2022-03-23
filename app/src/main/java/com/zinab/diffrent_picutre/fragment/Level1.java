package com.zinab.diffrent_picutre.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.zinab.diffrent_picutre.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Level1 extends Fragment {
    private TextView timer;
    private View btn1;
    private View btn1Copy;
    private View btn2;
    private View btn2Copy;
    private View btn3;
    private View btn3Copy;
    private View btn4;
    private View btn4Copy;
    private View btn5;
    private View btn5Copy;
    private View btn6;
    private View btn6Copy;
    private View btn7;
    private View btn7Copy;
    private View btn8;
    private View btn8Copy;
    private TextView tvPoints;
    private TextView yourPoints;
    private View btn9;
    private View btn9Copy;
    private View btn10;
    private View btn10Copy;
    // تعريف مشغل الموسيقى
    MediaPlayer mp;
    // تعريف العداد التنازلي
    CountDownTimer countDownTimer;
    public int point = 0;
    //تعريف عداد الثواني
    public static final String FORMAT = "%02d";
    public static int score = 0;

    public Level1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_level1, container, false);

        Inflate(v);
        startTimer(getActivity());
        btn1.setOnClickListener(view -> {
            btnClick(btn1, btn1Copy);
        });
        btn1Copy.setOnClickListener(view -> {
            btnClick(btn1, btn1Copy);
        });
        btn2.setOnClickListener(view -> {
            btnClick(btn2, btn2Copy);
        });
        btn2Copy.setOnClickListener(view -> {
            btnClick(btn2, btn2Copy);
        });
        btn3.setOnClickListener(view -> {
            btnClick(btn3, btn3Copy);
        });
        btn3Copy.setOnClickListener(view -> {
            btnClick(btn3, btn3Copy);
        });
        btn4.setOnClickListener(view -> {
            btnClick(btn4, btn4Copy);
        });
        btn4Copy.setOnClickListener(view -> {
            btnClick(btn4, btn4Copy);
        });
        btn5.setOnClickListener(view -> {
            btnClick(btn5, btn5Copy);
        });
        btn5Copy.setOnClickListener(view -> {
            btnClick(btn5, btn5Copy);
        });
        btn6.setOnClickListener(view -> {
            btnClick(btn6, btn6Copy);
        });
        btn6Copy.setOnClickListener(view -> {
            btnClick(btn6, btn6Copy);
        });
        btn7.setOnClickListener(view -> {
            btnClick(btn7, btn7Copy);
        });
        btn7Copy.setOnClickListener(view -> {
            btnClick(btn7, btn7Copy);
        });
        btn8.setOnClickListener(view -> {
            btnClick(btn8, btn8Copy);
        });
        btn8Copy.setOnClickListener(view -> {
            btnClick(btn8, btn8Copy);
        });
        btn9.setOnClickListener(view -> {
            btnClick(btn9, btn9Copy);
        });
        btn9Copy.setOnClickListener(view -> {
            btnClick(btn9, btn9Copy);
        });
        btn10.setOnClickListener(view -> {
            btnClick(btn10, btn10Copy);
        });
        btn10Copy.setOnClickListener(view -> {
            btnClick(btn10, btn10Copy);
        });
        yourPoints.setText("score: " + score + "");
        return v;
    }


    void btnClick(View btn, View btnCopy) {
        btn.setEnabled(false);
        btnCopy.setEnabled(false);
        btn.setBackgroundResource(R.drawable.btn_select);
        btnCopy.setBackgroundResource(R.drawable.btn_select);
        score += 2;
        point += 1;
        tvPoints.setText(String.valueOf(point));
        yourPoints.setText("Your scroe: " + score);
        //في حال الفوز اصدار صوت مختلف والانتقال للواجهة التالية و في حال عدم الفوز اصدار صوت فقط
        if (point == 10) {
            //استدعاء الصوت الذي احتاجه من ملف raw وتشغيله
            mp = MediaPlayer.create(getActivity(), R.raw.next);
            mp.start();
            //الانتقال للواجهة التالية في حال الفوز
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Framlayout, new Level2());
            ft.commit();
        } else {
            mp = MediaPlayer.create(getActivity(), R.raw.sucess);
            mp.start();
        }
    }

    // هذه الدالة لتشغيل العداد التنازلي
    void startTimer(Activity activity) {
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("time: " + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
            public void onFinish() {
                // في حال انتهى العداد يخرج له dialog والذي يحتوي على معلومات الخسارة
                mp = MediaPlayer.create(activity, R.raw.gameover);
                mp.start();
                // دالة اظهار dialog
                showDialog();
            }
        }.start();
    }
    // دالة اظهار dialog
    void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // منع اخفاء dialog عند الضغط خارجه على الشاشة
        builder.setCancelable(false);
        View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.gameover_dialog, null);

        builder.setView(itemView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        // تعريف الازرار  في dialog
        TextView tv = itemView.findViewById(R.id.textView2);
        MaterialButton sub = itemView.findViewById(R.id.button2);
        tv.setText("Your score : " + score);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.Framlayout, new HomeFragment());
                ft.commit();
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        // ايقاف التايمر في حال توقفت الصفحة عن العرض
        countDownTimer.cancel();
    }
 // دالة استدعاء الviews
    void Inflate(View v) {
        timer = (TextView) v.findViewById(R.id.timer);
        btn1 = (View) v.findViewById(R.id.btn1);
        btn1Copy = (View) v.findViewById(R.id.btn1Copy);
        btn2 = (View) v.findViewById(R.id.btn2);
        btn2Copy = (View) v.findViewById(R.id.btn2Copy);
        btn3 = (View) v.findViewById(R.id.btn3);
        btn3Copy = (View) v.findViewById(R.id.btn3Copy);
        btn4 = (View) v.findViewById(R.id.btn4);
        btn4Copy = (View) v.findViewById(R.id.btn4Copy);
        btn5 = (View) v.findViewById(R.id.btn5);
        btn5Copy = (View) v.findViewById(R.id.btn5Copy);
        btn6 = (View) v.findViewById(R.id.btn6);
        btn6Copy = (View) v.findViewById(R.id.btn6Copy);
        btn7 = (View) v.findViewById(R.id.btn7);
        btn7Copy = (View) v.findViewById(R.id.btn7Copy);
        btn8 = (View) v.findViewById(R.id.btn8);
        btn8Copy = (View) v.findViewById(R.id.btn8Copy);
        tvPoints = (TextView) v.findViewById(R.id.tvPoints);
        yourPoints = (TextView) v.findViewById(R.id.yourPoints);
        btn9 = (View) v.findViewById(R.id.btn9);
        btn9Copy = (View) v.findViewById(R.id.btn9Copy);
        btn10 = (View) v.findViewById(R.id.btn10);
        btn10Copy = (View) v.findViewById(R.id.btn10Copy);
    }

}