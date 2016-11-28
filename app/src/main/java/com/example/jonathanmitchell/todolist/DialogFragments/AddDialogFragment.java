package com.example.jonathanmitchell.todolist.DialogFragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jonathanmitchell.todolist.Objects.TodoItem;
import com.example.jonathanmitchell.todolist.R;
import com.example.jonathanmitchell.todolist.Stores.TodoStore;

/**
 * Created by jonathanmitchell on 11/19/16.
 */

public class AddDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    private DialogInterface.OnDismissListener listener;
    private EditText todoEditText;

    public AddDialogFragment() {

    }

    public static AddDialogFragment newInstance() {
        AddDialogFragment frag = new AddDialogFragment();
        return frag;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_add, null);

        if(this.todoEditText == null) {
            this.todoEditText = (EditText) v.findViewById(R.id.edit_text_todo);
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle("Add TODO")
                .setView(v)
                .setPositiveButton("Add!",this)
                .setNegativeButton("Cancel", this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch(i) {
            case DialogInterface.BUTTON_POSITIVE: {
                String todoText = this.todoEditText.getText().toString();
                TodoStore.get(getActivity())
                        .add(new TodoItem(todoText));
                if(listener != null) {
                    listener.onDismiss(dialogInterface);
                }
                dialogInterface.dismiss();
            }
            case DialogInterface.BUTTON_NEGATIVE: {
                dialogInterface.dismiss();
            }
        }
    }
}
