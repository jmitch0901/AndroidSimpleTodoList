package com.example.jonathanmitchell.todolist.Objects;

import android.text.format.DateFormat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by jonathanmitchell on 11/19/16.
 */

public class TodoItem implements Comparable<TodoItem> {

    private static final String TAG = "TODO_ITM";

    private static final String TODO_TEXT_TAG = "todoText";
    private static final String TODO_DATE_TAG = "todoDate";
    private static final String TODO_DETAILS_TEXT_TAG = "todoDetailsText";

    private String todoText;
    private String todoDetailsText;
    private Date todoDate;

    public TodoItem(String todoText) {
        this.todoText = todoText;
        this.todoDate = new Date();
    }

    public TodoItem(String todoText, String todoDetailsText) {
        this(todoText);
        this.todoDetailsText = todoDetailsText;
    }

    public TodoItem(JSONObject jo) throws JSONException {
        this.todoText = jo.getString(TODO_TEXT_TAG);
        this.todoDate = new Date(jo.getLong(TODO_DATE_TAG));
        this.todoDetailsText = jo.has(TODO_DETAILS_TEXT_TAG) ? jo.getString(TODO_DETAILS_TEXT_TAG) : "";
    }

    public String getTodoText() {
        return todoText;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public String getTodoDetailsText() {
        return todoDetailsText;
    }

    public void setTodoDetailsText(String text) {
        this.todoDetailsText = text;
    }

    public void setTodoText(String text) {
        this.todoText = text;
    }

    public String getFormattedDate() {
        return DateFormat.format("MM/dd/yyyy @ hh:mm a",this.todoDate).toString();
    }

    @Override
    public int compareTo(TodoItem todoItem) {
        return this.todoDate.compareTo(todoItem.todoDate) * -1;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "todoText='" + todoText + '\'' +
                ", todoDetailsText='" + todoDetailsText + '\'' +
                ", todoDate=" + todoDate +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jo = new JSONObject();
        try {
            jo.put(TODO_TEXT_TAG, this.todoText);
            jo.put(TODO_DATE_TAG, this.todoDate.getTime());
            jo.put(TODO_DETAILS_TEXT_TAG, this.todoDetailsText);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error creating JSON Object for todo item: " + this.toString());
        }

        return jo;
    }

}
