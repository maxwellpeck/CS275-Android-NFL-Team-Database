package com.example.nflteamapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nflteamapp.database.TeamDbSchema.TeamTable;

public class TeamBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "teamBase.db";
    public TeamBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TeamTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TeamTable.Cols.UUID + ", " +
                TeamTable.Cols.TITLE + ", " +
                TeamTable.Cols.QB + ", " +
                TeamTable.Cols.VENUE + ", " +
                TeamTable.Cols.CITY + ", " +
                TeamTable.Cols.DATE + ", " +
                TeamTable.Cols.ACTIVE + ", " +
                TeamTable.Cols.COACH +
                ")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
