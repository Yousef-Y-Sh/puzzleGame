package com.zinab.diffrent_picutre.fragment;

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

public class HomeFragment extends Fragment {


    MediaPlayer mp ;
    private TextView textView;
    private ImageButton button;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        mp = MediaPlayer.create(getActivity(), R.raw.play);
        button = (ImageButton) v.findViewById(R.id.button);
        button.setOnClickListener(view -> {
                mp.start();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Framlayout,new Level1());
            ft.commit();
        });
        return v;
    }
}