package com.example.dana.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dana on 5/6/2016.
 */
public class ActivityDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public ActivityDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // create activity from a string. Adds the activity to the database and converts it to a activity object
    public Activity createActivity(String activity, String activitytime) {
        // use values to store a new record for the database
        ContentValues values = new ContentValues();
        // store the new activity in the comment field of the new record
        values.put(MySQLiteHelper.COLUMN_ACTIVITY, activity);
        values.put(MySQLiteHelper.COLUMN_TYPE, activitytime);
        // insert new record into the table
        long insertId = database.insert(MySQLiteHelper.TABLE_ACTIVITY, null, values);
        // reads the last record back from the database
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ACTIVITY,
                null, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        // convert the database record into a activity object
        Activity newActivity = cursorToActivity(cursor);
        cursor.close();
        return newActivity;
    }

    // delete this activity from the database using the id field
    public void deleteActivity(Activity activity) {
        long id = activity.getId();
        System.out.println("Activity deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_ACTIVITY, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    // get all the activities from the database and convert them into a List
    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<Activity>();
        // grad all the comments from the database table
        String orderBy = MySQLiteHelper.COLUMN_ACTIVITY + " ASC";
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ACTIVITY,
                null, null, null, null, null, orderBy);

        // loop through all the record in the cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // create activity from the current record and add it to the List
            Activity activity = cursorToActivity(cursor);
            activities.add(activity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return activities;
    }

    // converts database records stored in a cursor to a comment object
    private Activity cursorToActivity(Cursor cursor) {
        Activity activity = new Activity();

        activity.setId( cursor.getLong( cursor.getColumnIndex( MySQLiteHelper.COLUMN_ID )) );

        activity.setActivity(cursor.getString( cursor.getColumnIndex( MySQLiteHelper.COLUMN_ACTIVITY ) ));

        activity.setType(cursor.getString( cursor.getColumnIndex( MySQLiteHelper.COLUMN_TYPE ) ));

        return activity;
    }
}

