package com.example.cvm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDB extends SQLiteOpenHelper {
    public static final String Database_Name="Current.db";
    public static final String Table_Name="User";
    public static final String Col2="Phone";
    public static final String Col3="Password";
    private int id=0;

    public LocalDB(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "Create table "+Table_Name+" (ID INTEGER PRIMARY KEY,PHONE TEXT,PASSWORD TEXT)";
        db.execSQL(Query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table IF EXISTS "+Table_Name);
        onCreate(db);
    }
    public boolean Insert(String Phone,String Password)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id++);
        contentValues.put(Col2,Phone);
        contentValues.put(Col3,Password);
        long rows = db.insert(Table_Name,null,contentValues);
        if(rows==-1)
            return false;
        return true;
    }
    public CurrentUser isExists(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Table_Name,null);
        CurrentUser c = null;
        if(cursor.moveToFirst())
        {
            c = new CurrentUser(cursor.getString(1),cursor.getString(2));

        }
        return c;
    }
    public boolean Remove()
    {
        SQLiteDatabase db = getWritableDatabase();
        if(db.delete(Table_Name,"ID = ?",new String[]{"0"} )>0){
            return true;
        }
        return false;
    }

}
