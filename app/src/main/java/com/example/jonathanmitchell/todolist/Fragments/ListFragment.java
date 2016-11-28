package com.example.jonathanmitchell.todolist.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jonathanmitchell.todolist.Activities.TodoDetailsActivity;
import com.example.jonathanmitchell.todolist.Adapters.ToDoListAdapter;
import com.example.jonathanmitchell.todolist.DialogFragments.AddDialogFragment;
import com.example.jonathanmitchell.todolist.Objects.TodoItem;
import com.example.jonathanmitchell.todolist.R;
import com.example.jonathanmitchell.todolist.Stores.TodoStore;

/**
 * Created by jonathanmitchell on 11/19/16.
 */

public class ListFragment extends Fragment implements View.OnClickListener,
        DialogInterface.OnDismissListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private static final String TAG="LIST_FRAG";

    private ListView todoListView;
    private ToDoListAdapter adapter;
    private FloatingActionButton addButton;

    private TodoStore todoStore;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment frag = new ListFragment();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todo_list, container, false);

        this.todoStore = TodoStore.get(getActivity());

        if(this.todoListView == null){
            this.todoListView = (ListView) v.findViewById(R.id.list_view_todo);
            this.todoListView.setOnItemClickListener(this);
            this.todoListView.setOnItemLongClickListener(this);
        }

        if(this.adapter == null) {
            this.adapter = new ToDoListAdapter(getContext(),R.layout.list_item_todo, TodoStore.get(getContext()).getTodoItems());
            this.todoListView.setAdapter(this.adapter);
        }

        if(this.addButton == null){
            this.addButton = (FloatingActionButton) v.findViewById(R.id.button_add);
            this.addButton.setOnClickListener(this);
        }

        return v;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.button_add: {
                AddDialogFragment frag = AddDialogFragment.newInstance();
                frag.setOnDismissListener(this);
                frag.show(getActivity().getSupportFragmentManager(),TAG);
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Log.i(TAG, "DIALOG DISMISSED!");
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TodoItem todoItem = todoStore.getTodoItems().get(i);
        Intent intent = new Intent(getActivity(), TodoDetailsActivity.class);

        Bundle b = new Bundle();
        b.putString(TodoDetailsFragment.XTRA_TODO_ITEM, todoItem.toJSON().toString());
        intent.putExtras(b);

        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        TodoItem todoItem = todoStore.getTodoItems().get(i);
        return false;
    }
}
