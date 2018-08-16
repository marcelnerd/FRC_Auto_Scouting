package com.example.cameron.sql_testing;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class TBAHandler {

    private static final String baseURL = "https://www.thebluealliance.com/api/v3";
    private static final String eventKey = "2018mndu";
    private static final int currentYear = 2018;
    public static JSONObject json;
    RequestQueue queue;


    public TBAHandler(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void getMatchData(String call) {
        TBAListener listener = new TBAListener();

        //String fullURL = baseURL + call;
        String fullURL = baseURL + "/match/2018mndu_qm1";
        //Log.d("minto", fullURL);
        //Log.d("minto", "https://www.thebluealliance.com/api/v3/match/2018mndu_qm1");

        TBAJSONRequest request = new TBAJSONRequest(Request.Method.GET, fullURL, null, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.d("minto", "DID NOT WORK");
            Log.d("minto", error.toString());
            }
        });
        queue.add(request);


    }

    public static String getMatch(int matchNum) {
        return String.format("/match/%1$s_qm%2$d", eventKey, matchNum);
    }

    public static String getTeamStatus(int teamNumber) {
        return String.format("/team/frc%1$d/events/%2$d/statuses", teamNumber, currentYear);
    }

    public static String getTeamMatches(int teamNumber) {
        return String.format("/team/frc%1$d/event/%2$s/matches", teamNumber, eventKey);
    }

    public static void setJson(JSONObject j) {
        json = j;
    }

}
