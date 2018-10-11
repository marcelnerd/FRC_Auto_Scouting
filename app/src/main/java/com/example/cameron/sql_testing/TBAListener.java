package com.example.cameron.sql_testing;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TBAListener implements Response.Listener<JSONObject> {

    TextView textBox;
    JSONObject json = null;
    boolean hasJson = false;

    public TBAListener() {
        //Log.d("minto", "CREATED LISTENER");
    }


    @Override
    public void onResponse(JSONObject response) {
        HashMap[] map = null;
        //Log.d("minto", "Response: " + response);
        this.json = response;
        try {
            map = MatchUpdater.getMatchData(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.d("minto", map.toString());

        for (HashMap e : map) {
            Log.d("minto", e.entrySet().toString());


      }

    }


    public JSONObject getJson() {
        return json;
    }

    public boolean hasJson() {
        return (json != null);
    }

}
