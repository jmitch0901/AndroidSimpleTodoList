package com.example.jonathanmitchell.todolist.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanmitchell.todolist.Activities.TodoDetailsActivity;
import com.example.jonathanmitchell.todolist.Objects.TodoItem;
import com.example.jonathanmitchell.todolist.R;
import com.example.jonathanmitchell.todolist.Stores.TodoStore;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jonathanmitchell on 11/28/16.
 */

public class TodoDetailsFragment extends Fragment {

    private static final String TAG = "TODO_DET_FRAG";
    private static final String SIS_BUNDLE = "SIS_BUNDLE";
    private static final String SIS_TODO_TEXT = "SIS_TODO_TEXT";
    private static final String SIS_TODO_DETAILS_TEXT = "SIS_TODO_DETAILS_TEXT";

    public static final String XTRA_TODO_ITEM = "XTRA_TODO_ITEM";

    private TodoItem todoItem;

    private TextView dateTextView;
    private EditText titleEditText;
    private EditText detailsEditText;
    private Button saveChangesButton;

    private Bundle savedState;


    public static TodoDetailsFragment newInstance(Bundle b) {
        TodoDetailsFragment frag = new TodoDetailsFragment();
        frag.setArguments(b);
        return frag;
    }

    public TodoDetailsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_todo_details, container, false);

        Bundle args = getArguments();


        dateTextView = (TextView)v.findViewById(R.id.text_view_todo_date);
        titleEditText = (EditText)v.findViewById(R.id.edit_text_todo_title);
        detailsEditText = (EditText)v.findViewById(R.id.edit_text_todo_details);
        saveChangesButton = (Button)v.findViewById(R.id.button_save_changes);


        try {
            JSONObject jo = new JSONObject(args.getString(XTRA_TODO_ITEM));
            init(jo);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Couldn't parse the JSON object: " + args.toString());
            getActivity().onBackPressed();
        }


        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoItem.setTodoText(titleEditText.getText().toString());
                todoItem.setTodoDetailsText(detailsEditText.getText().toString());
                TodoStore.get(getActivity()).update(todoItem);
                Toast.makeText(getActivity(),"Updated Todo Item!", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
            }
        });

        return v;
    }

    private void init(JSONObject jo) throws JSONException{

        try {
            todoItem = new TodoItem(jo);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON Object into TODO item: " + e.toString());
            throw e;
        }


        if(savedState == null || savedState.getBundle(SIS_BUNDLE) == null) {
            Log.i(TAG, "NOT USING SIS");
            titleEditText.setText(todoItem.getTodoText());
            detailsEditText.setText(todoItem.getTodoDetailsText());
        } else {
            Bundle b = savedState.getBundle(SIS_BUNDLE);
            Log.i(TAG, "YOUR BUNDLE: " + b.toString());
            Log.i(TAG,"YOUR BUNDLE TITLE TEXT: " + b.getString(SIS_TODO_TEXT));
            titleEditText.setText(b.getString(SIS_TODO_TEXT));
            detailsEditText.setText(b.getString(SIS_TODO_DETAILS_TEXT));
        }

        dateTextView.setText(todoItem.getFormattedDate());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null)
            this.savedState = savedInstanceState.getBundle(SIS_BUNDLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "SAVING INSTANCE STATE");
        super.onSaveInstanceState(outState);
        Bundle b = new Bundle();
        b.putString(SIS_TODO_TEXT, titleEditText.getText().toString());
        b.putString(SIS_TODO_DETAILS_TEXT, detailsEditText.getText().toString());
        outState.putBundle(SIS_BUNDLE, b);
    }

}
