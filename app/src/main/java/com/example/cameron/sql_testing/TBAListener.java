package com.example.cameron.sql_testing;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

public class TBAListener implements Response.Listener<JSONObject> {

    TextView textBox;
    JSONObject json;

    public TBAListener() {

    }

    @Override
    public void onResponse(JSONObject response) {

        //textBox.setText("Response: " + response.toString());
        Log.d("minto", "Response: " + response);
        this.json = response;

        //Log.d("minto", "GOT TO HERE");

    }

    public JSONObject getJson() {
        return json;
    }

}
