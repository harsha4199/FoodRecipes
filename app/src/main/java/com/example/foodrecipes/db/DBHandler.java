package com.example.foodrecipes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodrecipes.interfaces.AuthInterface;
import com.example.foodrecipes.model.User;

public class DBHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    AuthInterface authInterface;
    private static final int DATABSE_VERSION=2;
    private static final String DATABSE_NAME ="foodrecepies.db";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory, AuthInterface authInterface) {
        super(context, DATABSE_NAME, factory, DATABSE_VERSION);
        db = getWritableDatabase();
        this.authInterface = authInterface;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addUser(String name, String username, String password){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        db.insert(TABLE_NAME, null, values);
        db.close();
        authInterface.signUp();
    }

    public void getUser(String username){
        String temp="";
        SQLiteDatabase db = getWritableDatabase();
        String rawQuery = "SELECT * FROM %s WHERE %s='%s'";
        String query = String.format(rawQuery, TABLE_NAME, COLUMN_USERNAME, username);
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        User user = null;
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("id"))!=null){
                user = new User(c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("username")),
                        c.getString(c.getColumnIndex("password")));
                break;
            }
            c.moveToNext();
        }
        authInterface.signIn(user);
    }

}
