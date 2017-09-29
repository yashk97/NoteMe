package com.example.yash.noteme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NoteDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";
    public static final String TABLE_NAME = "listdata";
    public static final String COL_TITLE = "title";
    public static final String COL_CONTENT = "content";
    public static final String COL_DATE = "date";
    public static final String COL_ID = "id";

    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table listdata" +
                "(id integer primary key ," +
                "title text ,content text ," +
                "date text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS listdata");
        onCreate(db);
    }

    public ArrayList<MyData> getAllData() {

        ArrayList<MyData> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from listdata", null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String string1 = cursor.getString(cursor.getColumnIndex(COL_TITLE));
            String string2 = cursor.getString(cursor.getColumnIndex(COL_CONTENT));
            String string3 = cursor.getString(cursor.getColumnIndex(COL_DATE));
            MyData obj = new MyData(string1, string2, string3);
            arrayList.add(obj);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;

    }

    public void insert_data(MyData md){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE,md.Title);
        values.put(COL_CONTENT,md.Content);
        values.put(COL_DATE, md.date);

    // Insert the new row, returning the primary key value of the new row
        db.insert(TABLE_NAME, null, values);
    }

}
