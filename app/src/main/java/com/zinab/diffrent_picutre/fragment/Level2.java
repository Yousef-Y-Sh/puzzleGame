package com.zinab.diffrent_picutre.fragment;

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

import com.google.android.material.button.MaterialButton;
import com.zinab.diffrent_picutre.R;

import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class Level2 extends Fragment {
    private View a1;
    private View a1Copy;
    private View a2;
    private View a2Copy;
    private View a3;
    private View a3Copy;
    private View a4;
    private View a4Copy;
    private View a5;
    private View a5Copy;
    private View a6;
    private View a6Copy;
    private View a7;
    private View a7Copy;
    private View a8;
    private View a8Copy;
    private TextView tvPoints;
    private TextView yourPoints;
    int point = 0;
    MediaPlayer mp;
    private TextView timer;
    private CountDownTimer countDownTimer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_level2, container, false);
        inflate(v);
        yourPoints.setText("Youe score: " + Level1.score + "");
        a1.setOnClickListener(view -> {
            btnClick(a1, a1Copy);
        });
        a1Copy.setOnClickListener(view -> {
            btnClick(a1, a1Copy);
        });
        a2.setOnClickListener(view -> {
            btnClick(a2, a2Copy);
        });
        a2Copy.setOnClickListener(view -> {
            btnClick(a2, a2Copy);
        });
        a3.setOnClickListener(view -> {
            btnClick(a3, a3Copy);
        });
        a3Copy.setOnClickListener(view -> {
            btnClick(a3, a3Copy);
        });
        a4.setOnClickListener(view -> {
            btnClick(a4, a4Copy);
        });
        a4Copy.setOnClickListener(view -> {
            btnClick(a4, a4Copy);
        });
        a5.setOnClickListener(view -> {
            btnClick(a5, a5Copy);
        });
        a5Copy.setOnClickListener(view -> {
            btnClick(a5, a5Copy);
        });
        a6.setOnClickListener(view -> {
            btnClick(a6, a6Copy);
        });
        a6Copy.setOnClickListener(view -> {
            btnClick(a6, a6Copy);
        });
        a7.setOnClickListener(view -> {
            btnClick(a7, a7Copy);
        });
        a7Copy.setOnClickListener(view -> {
            btnClick(a7, a7Copy);
        });
        a8.setOnClickListener(view -> {
            btnClick(a8, a8Copy);
        });
        a8Copy.setOnClickListener(view -> {
            btnClick(a8, a8Copy);
        });
        startTimer();
        return v;
    }

    void inflate(View v) {
        timer = (TextView) v.findViewById(R.id.timer);
        a1 = (View) v.findViewById(R.id.a1);
        a1Copy = (View) v.findViewById(R.id.a1Copy);
        a2 = (View) v.findViewById(R.id.a2);
        a2Copy = (View) v.findViewById(R.id.a2Copy);
        a3 = (View) v.findViewById(R.id.a3);
        a3Copy = (View) v.findViewById(R.id.a3Copy);
        a4 = (View) v.findViewById(R.id.a4);
        a4Copy = (View) v.findViewById(R.id.a4Copy);
        a5 = (View) v.findViewById(R.id.a5);
        a5Copy = (View) v.findViewById(R.id.a5Copy);
        a6 = (View) v.findViewById(R.id.a6);
        a6Copy = (View) v.findViewById(R.id.a6Copy);
        a7 = (View) v.findViewById(R.id.a7);
        a7Copy = (View) v.findViewById(R.id.a7Copy);
        a8 = (View) v.findViewById(R.id.a8);
        a8Copy = (View) v.findViewById(R.id.a8Copy);
        tvPoints = (TextView) v.findViewById(R.id.tvPoints2);
        yourPoints = (TextView) v.findViewById(R.id.yourPoints2);


    }

    void btnClick(View btn, View btnCopy) {
        btn.setEnabled(false);
        btnCopy.setEnabled(false);
        btn.setBackgroundResource(R.drawable.btn_select);
        btnCopy.setBackgroundResource(R.drawable.btn_select);
        Level1.score += 2;
        point += 1;
        tvPoints.setText(String.valueOf(point));
        yourPoints.setText("Your scroe: " + Level1.score);
        if (point == 8) {
            mp = MediaPlayer.create(getActivity(), R.raw.next);
            mp.start();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Framlayout, new Level3());
            ft.commit();
        } else {
            mp = MediaPlayer.create(getActivity(), R.raw.sucess);
            mp.start();
        }
    }

    void startTimer() {
        countDownTimer = new  CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {

                timer.setText("time: " + String.format(Level1.FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                mp = MediaPlayer.create(getActivity(), R.raw.gameover);
                mp.start();
                showDialog();
            }
        }.start();

    }

    void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.gameover_dialog, null);

        builder.setView(itemView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        TextView tv = itemView.findViewById(R.id.textView2);
        MaterialButton sub = itemView.findViewById(R.id.button2);
        // استدعاء نقاط اللاعب من المتغير الموجوود في level1
        tv.setText("Your score : " + Level1.score);
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
        countDownTimer.cancel();
    }
}