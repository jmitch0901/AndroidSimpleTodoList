package com.example.jonathanmitchell.todolist.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.jonathanmitchell.todolist.Fragments.TodoDetailsFragment;
import com.example.jonathanmitchell.todolist.SingleFragmentActivity;

/**
 * Created by jonathanmitchell on 11/28/16.
 */

public class TodoDetailsActivity extends SingleFragmentActivity {


    @Override
    public Fragment getFragment() {
        return TodoDetailsFragment.newInstance(getIntent().getExtras());
    }
}
