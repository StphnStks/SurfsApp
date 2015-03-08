package com.example.stephen.surfsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;


/**
 * Created by Stephen on 17/02/2015.
 */


//outer class which creates database fields
public class TaskListDatabase {

    //Can we not set fields as int's
    public static final String KEY_ROWID = "_id";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_DESCRIPTION = "task_desc";
    public static final String COMPLETE = "complete";

    private static final String DATABASE_NAME = "Task_List";
    private static final String DATABASE_TABLE = "Tasks";
    private static final int DATABASE_VERSION = 1;

    //
    private static final String DATABASE_CREATE =
            "create table Tasks (_id integer primary key autoincrement, " +
                    "task_name text not null, " + "task_desc text not null, " + "complete text not null);";

    //declared here as required within both inner / outer classes
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    //constructor this is called to create an object in activity
    public TaskListDatabase(Context ctx)
    {
        this.context = ctx;
        //log i tests the SQL create statement
        Log.i("test", "value of sql create is " + DATABASE_CREATE);
        //instaniate's object of inner class (holds SQL queries)
        DBHelper = new DatabaseHelper(context);
    }

    //inner class contains onCreate and upgrade methods
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        //constructor with context passed by object
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //creates the fields for the database
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            // dB structure change..
        }
    } //

    //methods in outer class containing SQL queries
    public TaskListDatabase open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public long insertTask(String taskName, String taskDesc, String complete)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put(TASK_NAME, taskName);
        initialValues.put(TASK_DESCRIPTION, taskDesc);
        initialValues.put(COMPLETE, complete);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteTask(long rowld)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowld, null) > 0;
    }
    public Cursor getAllTasks ()
    {
        return db.query(DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                TASK_NAME,
                                TASK_DESCRIPTION,
                                COMPLETE
                        },
                null, null, null, null, null);
    }

    public Cursor getTask(long rowld) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                TASK_NAME,
                                TASK_DESCRIPTION,
                                COMPLETE
                        },
                KEY_ROWID + "=" + rowld, null, null, null, null, null);

        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateTask(long rowld, String taskName, String taskDesc, String complete)
    {
        ContentValues args = new ContentValues();
        args.put(TASK_NAME, taskName);
        args.put(TASK_DESCRIPTION, taskDesc);
        args.put(COMPLETE, complete);
        return db.update(DATABASE_TABLE, args,
                KEY_ROWID + "=" + rowld, null) > 0;
    }

}
