package com.example.jonathanmitchell.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jonathanmitchell on 11/19/16.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment getFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, this.getFragment())
                .commit()
                ;
    }
}
