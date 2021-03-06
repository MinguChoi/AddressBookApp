package com.example.addressbookapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "addressdb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String addressSQL = "Create table tb_address" + "(_id integer primary key autoincrement," +
                                                        "name, " +
                                                        "phone, " +
                                                        "email)";
        db.execSQL(addressSQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            db.execSQL("drop table tb_address");
            onCreate(db);
        }
    }
}
