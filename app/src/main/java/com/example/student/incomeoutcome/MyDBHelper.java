package com.example.student.incomeoutcome;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String name = "income.sqlite3";
    private static final int version = 3;


    public MyDBHelper (Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    /*public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE income (" +
                "_id integer primary key autoincrement," +
                "itemname text not null," +
                "value text not null," +
                "date text not null," +
                "transaction text not null);";
        db.execSQL(sql);
    }*/

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE income (" +
                "_id integer primary key autoincrement," +
                "iname text not null," +             // course code
                "ivalue real default 0.0," +           // credit
                "iday int default 0," +
                "imonth text not null ," +            // letter grade e.g. A, B+
                "iyear int default 0," +
                "itrans text not null);";         // grade value e.g. 4, 3.5
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS income;";
        db.execSQL(sql);
        this.onCreate(db);
    }

}
