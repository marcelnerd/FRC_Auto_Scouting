package com.example.cameron.sql_testing;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.

    private final static String SQL_CREATE_ENTRIES = "CREATE TABLE teams (teamID INTEGER PRIMARY KEY, score INT, name VARCHAR(30))";
    private final static String SQL_DELETE_ENTRIES = "DROP IF TABLE EXISTS teams";
    public final static String SQL_TABLE_NAME = "teams";
    private Context context;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScoutData.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
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

    public long enterData(int teamID, int score, String name) {
        long newRowId;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("teamID", teamID);
        values.put("score", score);
        values.put("name", name);
        newRowId = db.insert(SQL_TABLE_NAME, null, values);
        return newRowId;
    }

    public long updateTeamStats(int teamID, int score, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SQL_TABLE_NAME + " WHERE teamID=" + teamID + ";", null);

        if(cursor.moveToNext()) {
            // TODO Get current score
            // TODO Update current score
            // TODO Insert new score into database
        }
    }

    public String getTeamData(int teamID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String returnString = "";
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + SQL_TABLE_NAME + " WHERE teamID=" + Integer.toString(teamID) + ";", null);

        while(cursor.moveToNext()) {
            returnString += "Team ID: " + Integer.toString(cursor.getInt(0)) + "  Score: " + Integer.toString(cursor.getInt(1)) + "   Name: " + cursor.getString(2);
        }

        cursor.close();

        return returnString;
    }
}