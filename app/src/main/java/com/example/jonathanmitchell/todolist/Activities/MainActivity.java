package com.example.jonathanmitchell.todolist.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jonathanmitchell.todolist.Fragments.ListFragment;
import com.example.jonathanmitchell.todolist.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {

    @Override
    public Fragment getFragment() {
        return ListFragment.newInstance();
    }
}
