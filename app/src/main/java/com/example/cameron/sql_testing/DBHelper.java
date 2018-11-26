package com.example.cameron.sql_testing;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.cameron.sql_testing.MatchUpdater.getMatchData;

public class DBHelper extends SQLiteOpenHelper {

    private final static String SQL_CREATE_ENTRIES = "CREATE TABLE teams (_id INTEGER PRIMARY KEY, teleopPoints INT, autoPoints INT);";
    private final static String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS teams";
    public final static String SQL_TABLE_NAME = "teams";
    public static String nextMatch;
    private Context context;
    private MatchUpdater updater;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScoutDat.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        SQLiteDatabase d = this.getWritableDatabase();
        d.close();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
//        SQLiteDatabase d = this.getWritableDatabase();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private void executeSQLScript(SQLiteDatabase database, String dbname) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;

        try{
            inputStream = assetManager.open(dbname);
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();

            String[] createScript = outputStream.toString().split(";");
            for (int i = 0; i < createScript.length; i++) {
                String sqlStatement = createScript[i].trim();
                if (sqlStatement.length() > 0) {
                    database.execSQL(sqlStatement + ";");
                }
            }
        } catch (IOException e){
        } catch (SQLException e) {
        }
    }

    public void updateTeamStats(FRC2018Team team) {
        SQLiteDatabase db = this.getWritableDatabase();


        int oldTeleop, newTeleop, oldAutoP, newAutoP;

        int teleop = team.getTeleopPoints();
        int _id = team.getTeamNum();
        int autoPoints = team.getAutoPoints();

        Cursor cursor = db.rawQuery("SELECT * FROM " + SQL_TABLE_NAME + " WHERE _id=" + _id + ";", null);

        if(cursor.moveToNext()) {
            ////****UPDATE TELEOP****////
            oldTeleop = cursor.getInt(1);
            newTeleop = oldTeleop + teleop;
            db.execSQL("UPDATE teams SET teleopPoints='" + newTeleop + "' WHERE _id='" + _id + "';");
            //Log.v("minto", "UPDATE ONE");

            ////****UPDATE AUTO SCORE****////
            oldAutoP = cursor.getInt(2);
            newAutoP = autoPoints + oldAutoP;
            db.execSQL("UPDATE teams SET autoPoints=" + newAutoP + " WHERE _id='" + _id + "';");

        }
        else {
            db.execSQL("INSERT INTO " + SQL_TABLE_NAME + " VALUES (" + _id + ", " + teleop + ", '" + autoPoints + "');");
            Log.v("minto", "INSERT ONE");
        }

        db.close();
        //Log.v("minto", getTeamData(93));
    }

    public String getTeamData(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String returnString = "";
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + SQL_TABLE_NAME + " WHERE _id=" + Integer.toString(_id) + ";", null);

        while(cursor.moveToNext()) {
            returnString += "Team ID: " + Integer.toString(cursor.getInt(0)) + "  Teleop: " + Integer.toString(cursor.getInt(1)) + "   Auto Points: " + cursor.getInt(2);
        }

        cursor.close();
        db.close();

        return returnString;
    }

    public String getAllEntries() {
        SQLiteDatabase db = this .getWritableDatabase();
        String returnString = "";
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + SQL_TABLE_NAME + ";", null);

       while(cursor.moveToNext()) {
            Log.v("minto", "Team ID: " + Integer.toString(cursor.getInt(0)) + "  Teleop: " + Integer.toString(cursor.getInt(1)) + "   Auto Points: " + cursor.getInt(2));
            returnString += "Team ID: " + Integer.toString(cursor.getInt(0)) + "  Teleop: " + Integer.toString(cursor.getInt(1)) + "   Auto Points: " + cursor.getInt(2);
        }

        cursor.close();
        db.close();
        return returnString;
    }

    public Cursor getAllEntriesCursor() {
        SQLiteDatabase db = this .getWritableDatabase();
        String returnString = "";
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + SQL_TABLE_NAME + ";", null);

        return cursor;
    }

    public ArrayList<FRC2018Team> getAllEntriesList() {
        SQLiteDatabase db = this .getWritableDatabase();
        ArrayList<FRC2018Team> list = new ArrayList<>();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + SQL_TABLE_NAME + ";", null);

        while(cursor.moveToNext()) {
            Log.v("minto", "Team ID: " + Integer.toString(cursor.getInt(0)) + "  Teleop: " + Integer.toString(cursor.getInt(1)) + "   Auto Points: " + cursor.getInt(2));
            list.add(new FRC2018Team(false, cursor.getInt(2), false, cursor.getInt(0), cursor.getInt(1), 0));
        }

        cursor.close();
        db.close();
        return list;
    }
}