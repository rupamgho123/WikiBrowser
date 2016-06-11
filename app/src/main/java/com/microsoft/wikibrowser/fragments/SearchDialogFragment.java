package com.microsoft.wikibrowser.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.microsoft.wikibrowser.events.SearchQueryRequestedEvent;
import com.microsoft.wikibrowser.providers.BusProvider;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchDialogFragment extends BaseDialogFragment {
  EditText input = null;
  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    input = new EditText(getContext());
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT);
    input.setLayoutParams(lp);
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle("Enter search query");
    builder.setView(input);
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        if(input.getText().toString().length() == 0){
          Toast.makeText(getContext(),"Please enter search query",Toast.LENGTH_SHORT).show();
        }else {
          BusProvider.getInstance().post(new SearchQueryRequestedEvent(input.getText().toString()));
        }
        dialog.dismiss();
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
       dialog.dismiss();
      }
    });
    return builder.create();
  }
}
