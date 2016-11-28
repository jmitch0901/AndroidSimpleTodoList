package com.example.jonathanmitchell.todolist.Stores;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.jonathanmitchell.todolist.Objects.TodoItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jonathanmitchell on 11/19/16.
 */

public class TodoStore {

    private static final String TAG="TODO_STORE";
    private static final String PERSIST_ARRAY_TAG = "PERSIST_ARRAY_TAG";

    private Context context;
    private static TodoStore store;

    private List<TodoItem> todoItems;

    private TodoStore(Context context) {
        this.context = context;
        this.todoItems = new ArrayList<>();
        this.loadStore();
    }

    public synchronized static TodoStore get(Context context) {

        if(store == null) {
            store = new TodoStore(context);
        }

        return store;
    }

    public void add(TodoItem item) {
        this.todoItems.add(0,item);
        this.persistStore();
    }

    public void remove(int index){
        if(index >= this.todoItems.size() || index < 0){
            Log.e(TAG, "Tried to remove TODO item at invalid index.");
            return;
        }

        this.todoItems.remove(index);
    }

    public void update(TodoItem item) {
        for(int i = 0; i < todoItems.size(); i++){
            if(item.getTodoDate().equals(todoItems.get(i).getTodoDate())){
                todoItems.set(i,item);
                this.persistStore();
                return;
            }
        }

        Log.e(TAG, "Couldn't find date to update with date of: " + item.getTodoDate().toString());
    }

    private void loadStore() {
        String jsonArrayString = this.context.getSharedPreferences(TAG,Context.MODE_PRIVATE)
                .getString(PERSIST_ARRAY_TAG, "[]");

        try {
            JSONArray ja = new JSONArray(jsonArrayString);
            for(int i = 0; i < ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                this.todoItems.add(new TodoItem(jo));
            }

            Collections.sort(this.todoItems);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error parsing the JSON Array: " + jsonArrayString);
            Log.e(TAG, "Error: " + e.toString());
        }

    }

    public void persistStore() {

        SharedPreferences.Editor editor = this.context.getSharedPreferences(TAG,Context.MODE_PRIVATE).edit();

        JSONArray ja = new JSONArray();

        for(int i = 0; i < this.todoItems.size(); i++){
            TodoItem item = this.todoItems.get(i);
            ja.put(item.toJSON());
        }

        editor.putString(PERSIST_ARRAY_TAG, ja.toString());

        editor.apply();
    }

    public List<TodoItem> getTodoItems() {
        return this.todoItems;
    }
}
