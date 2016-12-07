package com.example.jonathanmitchell.todolist.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanmitchell.todolist.R;

/**
 * Created by jonathanmitchell on 12/5/16.
 */

public class SplashScreenFragment extends Fragment {

    public SplashScreenFragment(){

    }

    public static SplashScreenFragment newInstance() {
        SplashScreenFragment frag = new SplashScreenFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_screen_select_item, container, false);

        return v;
    }
}
