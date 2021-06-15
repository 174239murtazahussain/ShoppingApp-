package com.example.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class myDB extends SQLiteOpenHelper {
    public static final String DB_NAME="Data";
    public static final String TABLE1_NAME="Items";
    public static final String TABLE2_NAME="Days";
    public static final String COl_1="id";
    public static final String COL_2="name";
    public static final String COL_3="price";
    public static final String COL_4="dsecription";
    public static final String COL_5="dates";
    public static final String COL_6="Id";


    myDB(Context context)
    {
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table "+TABLE1_NAME+" (id text primary key ,name text,price text,description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        onCreate(db);

    }

    //insert students data in tableNo.1
    public void insertData(Items s){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COl_1,s.id);
        content.put(COL_2,s.name);
        content.put(COL_3,s.price);
        content.put(COL_4,s.description);
        //db.insertWithOnConflict("Students",null,content,SQLiteDatabase.CONFLICT_REPLACE);
        long result=db.insert(TABLE1_NAME,null,content);
        db.close();
    }


    //getting a days attendance by passing that days date
    public ArrayList<Items> getSpecificDayAttendance(String s1) {
        ArrayList<Items> s = new ArrayList<Items>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Items ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Items st = new Items();
                st.setId(cursor.getString(0));
                st.setName(cursor.getString(1));
                st.setPrice(cursor.getString(2));
                st.setDescription(cursor.getString(3));
                // Adding contact to list
                s.add(st);
            } while (cursor.moveToNext());
        }

        // return contact list
        return s;
    }


    public void deleteAttendance(Items s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE1_NAME, COl_1 + " = ?",
                new String[] { String.valueOf(s.getId()) });
        db.close();
    }

}
