package com.example.jonathanmitchell.todolist.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jonathanmitchell.todolist.Fragments.ListFragment;
import com.example.jonathanmitchell.todolist.Fragments.SplashScreenFragment;
import com.example.jonathanmitchell.todolist.Fragments.TodoDetailsFragment;
import com.example.jonathanmitchell.todolist.Objects.TodoItem;
import com.example.jonathanmitchell.todolist.R;
import com.example.jonathanmitchell.todolist.SingleFragmentActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.ListCallbacks, TodoDetailsFragment.Callbacks {

    private static final String TAG="MAIN_ACT";
    private boolean isPhone = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.potential_tablet_layout);

        this.isPhone = findViewById(R.id.linear_layout_phone) != null;

        ListFragment listFragment = ListFragment.newInstance();
        listFragment.setListCallbacks(this);


        if(this.isPhone) {
            Log.i(TAG,"We are on a PHONE!");
            //This means we are on a phone layout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, listFragment)
                    .commit();
        } else {
            Log.i(TAG, "We are on a TABLET");
            //Otherwise, we are on a tablet

            Fragment rightFragment = SplashScreenFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.leftFragmentContainer, listFragment)
                    .replace(R.id.rightFragmentContainer, rightFragment)
                    .commit();

        }
    }

    @Override
    public void onListItemSelected(TodoItem todoItem) {

        Bundle b = new Bundle();
        b.putString(TodoDetailsFragment.XTRA_TODO_ITEM, todoItem.toJSON().toString());

        if(this.isPhone) {
            //This means we are on phone layout
        Intent intent = new Intent(this, TodoDetailsActivity.class);
        intent.putExtras(b);

        startActivity(intent);
        } else {
            //Otherwise, we are on tablet
            TodoDetailsFragment todoDetailsFragment = TodoDetailsFragment.newInstance(b);
            todoDetailsFragment.setCallbacks(this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rightFragmentContainer, todoDetailsFragment)
                    .commit();

        }
    }

    @Override
    public void onItemSaved() {
        if(this.isPhone) {
            this.onBackPressed();
        }
    }
}
