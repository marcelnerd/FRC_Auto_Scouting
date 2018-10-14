package com.example.cameron.sql_testing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static Context c;
    public DatabaseContainer container = new DatabaseContainer(this);
    public TextView textBox;
    Button button;
    TBAHandler handler;

    //public DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = findViewById(R.id.textBox);
        button = findViewById(R.id.button);
        //helper = new DBHelper(this);
        handler = new TBAHandler(this);
        handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018_mndu", 1));
        //handler.getMatchData();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018_mndu", 1));
                textBox.setText(handler.helper.getTeamData(93));
            }
        });
    }



}
