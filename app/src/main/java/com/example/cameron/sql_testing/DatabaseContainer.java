package com.example.cameron.sql_testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

public class DatabaseContainer extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.

    private final static String SQL_CREATE_ENTRIES = "CREATE TABLE teams (teamID INTEGER PRIMARY KEY, teleopPoints INT, autoPoints INT)";
    private final static String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS teams";
    public final static String SQL_TABLE_NAME = "teams";
    public static String nextMatch;
    private Context context;
    private MatchUpdater updater;

    @NonNull
    public static SQLiteDatabase d;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScoutData.db";

    public DatabaseContainer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        updater = new MatchUpdater();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        //d = this.getWritableDatabase();
        //ff.close();
        //d = db;
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

    public void insert(String a, String b, ContentValues c) {
        this.getWritableDatabase().insert(a, b, c);
    }

    public void rawQuery(String q, String[] a) {
        d.rawQuery(q, a);
    }

}
