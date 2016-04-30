package com.example.dana.final_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dana on 4/29/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_ACTIVITIES = "activities";     // name of the table
    public static final String COLUMN_ID = "_id";               // name of the id field
    public static final String COLUMN_ACTIVITY = "activity";      // name of the comment field

    private static final String DATABASE_NAME = "activities.db"; // name of the database
    private static final int DATABASE_VERSION = 1;              // version of the database

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ACTIVITIES + "(" +
            COLUMN_ID + " integer primary key autoincrement " +
            COLUMN_ACTIVITY + " text not null ";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        onCreate(db);
    }

}