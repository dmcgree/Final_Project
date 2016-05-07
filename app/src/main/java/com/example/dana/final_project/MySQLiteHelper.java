package com.example.dana.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dana McGree, Ashley Stalvig, Ted Jacobi on 4/29/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_ACTIVITY = "actvivities";     // name of the table
    public static final String COLUMN_ID = "_id";               // name of the id field
    public static final String COLUMN_ACTIVITY = "activity";      // name of the comment field
    public static final String COLUMN_TYPE = "type";        // name of the rating field

    private static final String DATABASE_NAME = "comments.db"; // name of the database
    private static final int DATABASE_VERSION = 2;              // version of the database

    // Database creation sql statement
//    private static final String DATABASE_CREATE = "create table "
//            + TABLE_COMMENTS + "(" +
//                COLUMN_ID + " integer primary key autoincrement, " +
//                COLUMN_COMMENT + " text not null, " +
//                COLUMN_RATING + " text not null; ";
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ACTIVITY + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_ACTIVITY
            + " text not null, " + COLUMN_TYPE
            + " text not null);";

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        onCreate(db);
    }

}
