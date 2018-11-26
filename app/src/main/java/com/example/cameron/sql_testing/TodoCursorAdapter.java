package com.example.cameron.sql_testing;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView teamNumText = view.findViewById(R.id.TeamNumText);
        TextView telePointsText = view.findViewById(R.id.TelePointsText);
        // Extract properties from cursor
        String body = cursor.getString(cursor.getColumnIndexOrThrow("_id")).toString();
        int priority = cursor.getInt(cursor.getColumnIndexOrThrow("teleopPoints"));
        // Populate fields with extracted properties
        teamNumText.setText(body);
        telePointsText.setText(String.valueOf(priority));
    }
}
