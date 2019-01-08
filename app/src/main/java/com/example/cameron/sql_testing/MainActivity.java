package com.example.cameron.sql_testing;

import android.content.Context;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    public static Context c; // Static context that can be accessed from other classes

    public DatabaseContainer container = new DatabaseContainer(this);
    Button button;
    TBAHandler handler;
    ListView list;
    private static int currentMatch = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        list = findViewById(R.id.listMain);
        handler = new TBAHandler(this);

        //handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018_mndu", 1));
        //Log.v("minto", handler.helper.getAllEntries());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", currentMatch));
            }
        });

        //handler.helper.onUpgrade(handler.helper.getWritableDatabase(), 4, 5);
        for(int i = 1; i < 80; i++) {
            handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", i));
        }

        FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(this, TBAHandler.helper.getAllEntriesCursor());
        list.setAdapter(todoAdapter);
    }

    public static void setCurrentMatch(int c) {
        currentMatch = c;
    }

    public static int getCurrentMatch() {
        return currentMatch;
    }

}
