package com.example.cameron.sql_testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    TextView textBox;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = findViewById(R.id.textBox);
        button = findViewById(R.id.button);
        helper = new DBHelper(this);
        //helper.enterData(223, 4, "the bois");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.updateTeamStats(243, 7, "the vois");
                textBox.setText(helper.getTeamData(243 ));
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.50/index.html";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textBox.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

    }
}
