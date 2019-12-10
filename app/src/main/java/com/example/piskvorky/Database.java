package com.example.piskvorky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static String TABLE_NAME = "highscore1";
    private static String DB_NAME = "database.db";

    private static String ID = "id";
    private static String NAME = "name";
    private static String SCORE = "score";

    public Database(Context context){
        super(context,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table highscore1 " +
                        "(id text, name text, score double)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String id, String name, double score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(SCORE, score);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Score> select() {
        ArrayList<Score> array_list = new ArrayList<Score>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            Score s = new Score();
            s.setID(res.getString(res.getColumnIndex(ID)));
            s.setName(res.getString(res.getColumnIndex(NAME)));
            s.setScore(res.getInt(res.getColumnIndex(SCORE)));

            array_list.add(s);
            res.moveToNext();
        }
        return array_list;
    }


}
