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

import java.util.concurrent.TimeUnit;


public class Level3 extends Fragment {

    private View b1;
    private View b1Copy;
    private View b2;
    private View b2Copy;
    private View b3;
    private View b3Copy;
    private View b4;
    private View b4Copy;
    private View b5;
    private View b5Copy;
    private View b6;
    private View b6Copy;
    private View b7;
    private View b7Copy;
    private View b8;
    private View b8Copy;
    private TextView tvPoints3;
    private TextView yourPoints3;
    private TextView timer;

    MediaPlayer mp;
    int point = 0;
    private CountDownTimer countDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_level3, container, false);
        inflate(v);
        yourPoints3.setText("Your score: " + Level1.score + "");
        b1.setOnClickListener(view -> {
            btnClick(b1, b1Copy);
        });
        b1Copy.setOnClickListener(view -> {
            btnClick(b1, b1Copy);
        });
        b2.setOnClickListener(view -> {
            btnClick(b2, b2Copy);
        });
        b2Copy.setOnClickListener(view -> {
            btnClick(b2, b2Copy);
        });
        b3.setOnClickListener(view -> {
            btnClick(b3, b3Copy);
        });
        b3Copy.setOnClickListener(view -> {
            btnClick(b3, b3Copy);
        });
        b4.setOnClickListener(view -> {
            btnClick(b4, b4Copy);
        });
        b4Copy.setOnClickListener(view -> {
            btnClick(b4, b4Copy);
        });
        b5.setOnClickListener(view -> {
            btnClick(b5, b5Copy);
        });
        b5Copy.setOnClickListener(view -> {
            btnClick(b5, b5Copy);
        });
        b6.setOnClickListener(view -> {
            btnClick(b6, b6Copy);
        });
        b6Copy.setOnClickListener(view -> {
            btnClick(b6, b6Copy);
        });
        b7.setOnClickListener(view -> {
            btnClick(b7, b7Copy);
        });
        b7Copy.setOnClickListener(view -> {
            btnClick(b7, b7Copy);
        });
        b8.setOnClickListener(view -> {
            btnClick(b8, b8Copy);
        });
        b8Copy.setOnClickListener(view -> {
            btnClick(b8, b8Copy);
        });
        startTimer();
        return v;
    }

    void btnClick(View btn, View btnCopy) {
        btn.setEnabled(false);
        btnCopy.setEnabled(false);
        btn.setBackgroundResource(R.drawable.btn_select);
        btnCopy.setBackgroundResource(R.drawable.btn_select);
        Level1.score += 2;
        point += 1;
        tvPoints3.setText(String.valueOf(point));
        yourPoints3.setText("Your scroe: " + Level1.score);
        if (point == 8) {
            mp = MediaPlayer.create(getActivity(), R.raw.next);
            mp.start();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Framlayout, new Level4());
            ft.commit();
        } else {
            mp = MediaPlayer.create(getActivity(), R.raw.sucess);
            mp.start();
        }
    }

    void inflate(View v) {
        timer = (TextView) v.findViewById(R.id.timer);
        b1 = (View) v.findViewById(R.id.b1);
        b1Copy = (View) v.findViewById(R.id.b1Copy);
        b2 = (View) v.findViewById(R.id.b2);
        b2Copy = (View) v.findViewById(R.id.b2Copy);
        b3 = (View) v.findViewById(R.id.b3);
        b3Copy = (View) v.findViewById(R.id.b3Copy);
        b4 = (View) v.findViewById(R.id.b4);
        b4Copy = (View) v.findViewById(R.id.b4Copy);
        b5 = (View) v.findViewById(R.id.b5);
        b5Copy = (View) v.findViewById(R.id.b5Copy);
        b6 = (View) v.findViewById(R.id.b6);
        b6Copy = (View) v.findViewById(R.id.b6Copy);
        b7 = (View) v.findViewById(R.id.b7);
        b7Copy = (View) v.findViewById(R.id.b7Copy);
        b8 = (View) v.findViewById(R.id.b8);
        b8Copy = (View) v.findViewById(R.id.b8Copy);
        tvPoints3 = (TextView) v.findViewById(R.id.tvPoints3);
        yourPoints3 = (TextView) v.findViewById(R.id.yourPoints3);
    }

    void startTimer() {
        final String FORMAT = "%02d";
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {

                timer.setText("time: " + String.format(FORMAT,
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