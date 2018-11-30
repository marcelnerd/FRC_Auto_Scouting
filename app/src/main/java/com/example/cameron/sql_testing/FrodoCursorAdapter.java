package com.example.cameron.sql_testing;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class FrodoCursorAdapter extends CursorAdapter {
    public FrodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.team_entry_frodo, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView teamNumText = view.findViewById(R.id.TeamNumText);
        TextView telePointsText = view.findViewById(R.id.TelePointsText);
        TextView autoPointsText = view.findViewById(R.id.AutoPointsText);
        TextView climbText = view.findViewById(R.id.ClimbText);
        TextView vaultPointsText = view.findViewById(R.id.VaultPointsText);
        TextView autoRunText = view.findViewById(R.id.AutoRunText);
        TextView matchesText = view.findViewById(R.id.MatchesPlayedText);

        // Extract properties from cursor
        String teamNum = cursor.getString(cursor.getColumnIndex("_id")).toString();
        String teleopPoints = cursor.getString(cursor.getColumnIndex("teleopPoints")).toString();
        String autoPoints = cursor.getString(cursor.getColumnIndex("autoPoints")).toString();
        String vaultPoints = cursor.getString(cursor.getColumnIndex("vaultPoints")).toString();
        String climb = cursor.getString(cursor.getColumnIndex("climb")).toString();
        String autoRun = cursor.getString(cursor.getColumnIndex("autoRun")).toString();
        String matchesPlayed = cursor.getString(cursor.getColumnIndex("matchesPlayed")).toString();

        // Populate fields with extracted properties
        teamNumText.setText(teamNum);
        telePointsText.setText(String.valueOf(teleopPoints));
        autoPointsText.setText(autoPoints);
        climbText.setText(climb);
        vaultPointsText.setText(vaultPoints);
        autoRunText.setText(autoRun);
        matchesText.setText(matchesPlayed);
    }
}
