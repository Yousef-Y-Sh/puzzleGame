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

public class Level5 extends Fragment {
    private View d1;
    private View d1Copy;
    private View d2;
    private View d2Copy;
    private View d3;
    private View d3Copy;
    private TextView tvPoints;
    private TextView yourPoints;
    private TextView timer;


    private int point = 0;
    private MediaPlayer mp;
    private CountDownTimer countDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_level5, container, false);
        inflate(v);
        yourPoints.setText("Your scroe: " + Level1.score);
        d1.setOnClickListener(view -> {
            btnClick(d1, d1Copy);
        });
        d1Copy.setOnClickListener(view -> {
            btnClick(d1, d1Copy);
        });
        d2.setOnClickListener(view -> {
            btnClick(d2, d2Copy);
        });
        d2Copy.setOnClickListener(view -> {
            btnClick(d2, d2Copy);
        });
        d3.setOnClickListener(view -> {
            btnClick(d3, d3Copy);
        });
        d3Copy.setOnClickListener(view -> {
            btnClick(d3, d3Copy);
        });
        startTimer();
        return v;
    }

    void inflate(View v) {
        timer = (TextView) v.findViewById(R.id.timer);
        d1 = (View) v.findViewById(R.id.d1);
        d1Copy = (View) v.findViewById(R.id.d1Copy);
        d2 = (View) v.findViewById(R.id.d2);
        d2Copy = (View) v.findViewById(R.id.d2Copy);
        d3 = (View) v.findViewById(R.id.d3);
        d3Copy = (View) v.findViewById(R.id.d3Copy);
        tvPoints = (TextView) v.findViewById(R.id.tvPoints5);
        yourPoints = (TextView) v.findViewById(R.id.yourPoints5);
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
        if (point == 3) {
            mp = MediaPlayer.create(getActivity(), R.raw.wins);
            mp.start();
            showDialog(true);
        } else {
            mp = MediaPlayer.create(getActivity(), R.raw.sucess);
            mp.start();
        }
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
                showDialog(false);
            }
        }.start();

    }

    void showDialog(boolean isWin) {
        countDownTimer.cancel();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.gameover_dialog, null);

        builder.setView(itemView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        TextView title = itemView.findViewById(R.id.textView);
        TextView tv = itemView.findViewById(R.id.textView2);
        MaterialButton sub = itemView.findViewById(R.id.button2);
        tv.setText("Your score : " + Level1.score);
        if (isWin){title.setText("Congratulation");}else{title.setText("Game Over");}
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