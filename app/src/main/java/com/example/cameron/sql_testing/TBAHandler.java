package com.example.cameron.sql_testing;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class TBAHandler {

    private static final String baseURL = "http://www.thebluealliance.com/api/v3";
    private static final String eventKey = "2018mndu";
    private static final int currentYear = 2018;
    RequestQueue queue;


    public TBAHandler(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void getTeamData(String call) {
        //String fullURL = baseURL + call;
        String fullURL = baseURL + "/match/2018mndu_qm1";

        TBAJSONRequest request = new TBAJSONRequest(Request.Method.GET, fullURL, null, new TBAListener(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

}
