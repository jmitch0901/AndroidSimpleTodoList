package com.example.jonathanmitchell.todolist.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jonathanmitchell.todolist.Objects.TodoItem;
import com.example.jonathanmitchell.todolist.R;


import java.util.List;

/**
 * Created by jonathanmitchell on 11/19/16.
 */

public class ToDoListAdapter extends ArrayAdapter<TodoItem> {

    private LayoutInflater inflater;


    public ToDoListAdapter(Context context, int resource, List<TodoItem> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_todo, parent, false);
        }

        TextView todoTextView = (TextView) convertView.findViewById(R.id.text_view_todo_text);
        TextView todoDateTextView = (TextView) convertView.findViewById(R.id.text_view_todo_date);

        TodoItem todoItem = this.getItem(position);


        todoTextView.setText(todoItem.getTodoText());
        todoDateTextView.setText(todoItem.getFormattedDate());


        return convertView;
    }
}
