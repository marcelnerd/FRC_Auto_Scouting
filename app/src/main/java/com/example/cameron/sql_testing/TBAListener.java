package com.example.cameron.sql_testing;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TBAListener implements Response.Listener<JSONObject> {

    TextView textBox;
    JSONObject json = null;
    boolean hasJson = false;
    DBHelper helper;

    public TBAListener(DBHelper h) {
        //Log.d("minto", "CREATED LISTENER");
        helper = h;
    }


    @Override
    public void onResponse(JSONObject response) {
        HashMap[] map = null;
        FRC2018Team team = null;

        //Log.d("minto", "Response: " + response);
        this.json = response;
        try {
            map = MatchUpdater.getMatchData(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (HashMap e : map) {
            team = buildTeam(e.entrySet());
            helper.updateTeamStats(team);
        }

    }

    private static void yeet() {
    }


    private FRC2018Team buildTeam(Set entry) {

        boolean tempClimb = false;
        int tempAP = 999;
        boolean tempAR = false;
        int tempTeamNum = 0;
        int tempTP = 999;
        int tempVP = 999;

        String currItem;

        Iterator it = entry.iterator();

        while(it.hasNext()) {
            currItem = it.next().toString();
            if(currItem.contains("endgameClimb")) {
                tempClimb = Boolean.parseBoolean(currItem.substring(currItem.indexOf('=')+1, currItem.length()));
                //Log.v("minto", Boolean.toString(tempClimb));
            }
            else if(currItem.contains("autoPoints")) {
                tempAP = Integer.parseInt(currItem.substring(currItem.indexOf('=')+1, currItem.length()));
            }
            else if(currItem.contains("autoRun")) {
                tempAR = Boolean.parseBoolean(currItem.substring(currItem.indexOf('=')+1, currItem.length()));
            }
            else if(currItem.contains("teamNumber")) {
                tempTeamNum = Integer.parseInt(currItem.substring(currItem.indexOf('=')+1, currItem.length()));
                //Log.v("minto", Integer.toString(tempTeamNum));
            }
            else if(currItem.contains("teleopPoints")) {
                tempTP = Integer.parseInt(currItem.substring(currItem.indexOf('=')+1, currItem.length()));
            }
            else if(currItem.contains("vaultPoints")) {
                tempVP = Integer.parseInt(currItem.substring(currItem.indexOf('=')+1, currItem.length()));
            }

            //Log.d("minto", "Current entry item: " + currItem);
        }

        return new FRC2018Team(tempClimb, tempAP, tempAR, tempTeamNum, tempTP, tempVP);
    }


    public JSONObject getJson() {
        return json;
    }

    public boolean hasJson() {
        return (json != null);
    }
}


