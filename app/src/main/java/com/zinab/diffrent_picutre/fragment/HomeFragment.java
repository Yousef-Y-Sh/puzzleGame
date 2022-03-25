package com.zinab.diffrent_picutre.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zinab.diffrent_picutre.R;

import java.util.logging.Level;

public class HomeFragment extends Fragment {


    private TextView textView;
    private ImageButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        button = (ImageButton) v.findViewById(R.id.button);
        button.setOnClickListener(view -> {
            // هذه الاربع اسطر لتشغيل الموسيقى المحددة و وفي حال عدم وجود الدالتين السفليتين سوف يوجد مشكلة في الصوت لذلك يلزم وجودها
            MediaPlayer play = MediaPlayer.create(getActivity(), R.raw.play);
            play.setAudioStreamType(AudioManager.STREAM_MUSIC);
            play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    play.release();
                }
            });
            play.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            // لتشغيل fragment التالية
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Framlayout,new Level1());
            ft.commit();
        });
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        Level1.score = 0;
    }
}