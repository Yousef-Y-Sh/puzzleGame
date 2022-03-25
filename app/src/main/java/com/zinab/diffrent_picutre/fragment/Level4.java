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
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.zinab.diffrent_picutre.R;

import java.util.concurrent.TimeUnit;

public class Level4 extends Fragment {
    private View c1;
    private View c1Copy;
    private View c2;
    private View c2Copy;
    private View c3;
    private View c3Copy;
    private View c4;
    private View c4Copy;
    private View c5;
    private View c5Copy;
    private View c6;
    private View c6Copy;
    private View c7;
    private View c7Copy;
    private View c8;
    private View c8Copy;
    private View c9;
    private View c9Copy;
    private View c10;
    private View c10Copy;
    private TextView tvPoints4;
    private TextView yourPoints4;
    private TextView timer;

    MediaPlayer mp;
    private int point = 0;
    private CountDownTimer counterDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_level4, container, false);
        inflate(v);
        c1.setOnClickListener(view -> {
            btnClick(c1, c1Copy);
        });
        c1Copy.setOnClickListener(view -> {
            btnClick(c1, c1Copy);
        });
        c2.setOnClickListener(view -> {
            btnClick(c2, c2Copy);
        });
        c2Copy.setOnClickListener(view -> {
            btnClick(c2, c2Copy);
        });
        c3.setOnClickListener(view -> {
            btnClick(c3, c3Copy);
        });
        c3Copy.setOnClickListener(view -> {
            btnClick(c3, c3Copy);
        });
        c4.setOnClickListener(view -> {
            btnClick(c4, c4Copy);
        });
        c4Copy.setOnClickListener(view -> {
            btnClick(c4, c4Copy);
        });
        c5.setOnClickListener(view -> {
            btnClick(c5, c5Copy);
        });
        c5Copy.setOnClickListener(view -> {
            btnClick(c5, c5Copy);
        });
        c6.setOnClickListener(view -> {
            btnClick(c6, c6Copy);
        });
        c6Copy.setOnClickListener(view -> {
            btnClick(c6, c6Copy);
        });
        c7.setOnClickListener(view -> {
            btnClick(c7, c7Copy);
        });
        c7Copy.setOnClickListener(view -> {
            btnClick(c7, c7Copy);
        });
        c8.setOnClickListener(view -> {
            btnClick(c8, c8Copy);
        });
        c8Copy.setOnClickListener(view -> {
            btnClick(c8, c8Copy);
        });
        c9.setOnClickListener(view -> {
            btnClick(c9, c9Copy);
        });
        c9Copy.setOnClickListener(view -> {
            btnClick(c9, c9Copy);
        });
        c10.setOnClickListener(view -> {
            btnClick(c10, c10Copy);
        });
        c10Copy.setOnClickListener(view -> {
            btnClick(c10, c10Copy);
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
        tvPoints4.setText(String.valueOf(point));
        yourPoints4.setText("Your scroe: " + Level1.score);
        if (point == 10) {
            mp = MediaPlayer.create(getActivity(), R.raw.next);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });                FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Framlayout, new Level5());
            ft.commit();
        } else {
            mp = MediaPlayer.create(getActivity(), R.raw.sucess);
            mp.start();
        }
    }

    void inflate(View v) {
        timer = (TextView) v.findViewById(R.id.timer);

        c1 = (View) v.findViewById(R.id.c1);
        c1Copy = (View) v.findViewById(R.id.c1Copy);
        c2 = (View) v.findViewById(R.id.c2);
        c2Copy = (View) v.findViewById(R.id.c2Copy);
        c3 = (View) v.findViewById(R.id.c3);
        c3Copy = (View) v.findViewById(R.id.c3Copy);
        c4 = (View) v.findViewById(R.id.c4);
        c4Copy = (View) v.findViewById(R.id.c4Copy);
        c5 = (View) v.findViewById(R.id.c5);
        c5Copy = (View) v.findViewById(R.id.c5Copy);
        c6 = (View) v.findViewById(R.id.c6);
        c6Copy = (View) v.findViewById(R.id.c6Copy);
        c7 = (View) v.findViewById(R.id.c7);
        c7Copy = (View) v.findViewById(R.id.c7Copy);
        c8 = (View) v.findViewById(R.id.c8);
        c8Copy = (View) v.findViewById(R.id.c8Copy);
        c9 = (View) v.findViewById(R.id.c9);
        c9Copy = (View) v.findViewById(R.id.c9Copy);
        c10 = (View) v.findViewById(R.id.c10);
        c10Copy = (View) v.findViewById(R.id.c10Copy);
        tvPoints4 = (TextView) v.findViewById(R.id.tvPoints4);
        yourPoints4 = (TextView) v.findViewById(R.id.yourPoints4);
    }

    void startTimer() {
        final String FORMAT = "%02d";
        counterDownTimer = new  CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {

                timer.setText("time: " + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                mp = MediaPlayer.create(getActivity(), R.raw.gameover);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });                    showDialog();
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
        Button sub = (Button) itemView.findViewById(R.id.button2);
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
        counterDownTimer.cancel();
    }
}