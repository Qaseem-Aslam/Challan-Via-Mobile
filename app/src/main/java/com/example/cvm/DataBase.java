package com.example.cvm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public static String Database_Name="UserInfo";
    public static String Table1 = "Users";
    public static String Table2 = "History";
    public static String Table3 = "Reward";
    public DataBase(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+Table1+"(Phone TEXT PRIMARY KEY,EMAIL TEXT UNIQUE,USERNAME TEXT ,PASSWORD TEXT)");
        db.execSQL("Create table "+Table2+"(Phone TEXT,AMOUNT,DATE,CARNUMBER)");
        db.execSQL("Create table "+Table3+"(PHONE TEXT ,AMOUNT TEXT,STATUS TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table IF EXISTS "+Table1);
        db.execSQL("Drop table IF EXISTS "+Table2);
        db.execSQL("Drop table IF EXISTS "+Table3);
        onCreate(db);
    }
    public boolean InsertUser(User user)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Phone",user.getPhone());
        contentValues.put("Email",user.getEmail());
        contentValues.put("UserName",user.getName());
        contentValues.put("Password",user.getPassword());
        long val = db.insert(Table1,null,contentValues);
        if(val==-1)
            return false;
        return true;
    }
    public boolean InsertHistory(History history)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Phone",history.getPhone());
        contentValues.put("Amount",history.getAmount());
        contentValues.put("Date",history.getDate());
        contentValues.put("CarNumber",history.getCarNumber());
        long val = db.insert(Table2,null,contentValues);
        if(val==-1)
            return false;
        return true;
    }
    public boolean InsertRewarc(Reward reward)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Phone",reward.getPhone());
        contentValues.put("Amount",reward.getAmount());
        contentValues.put("Status",reward.getStatus());
        long val = db.insert(Table3,null,contentValues);
        if(val==-1)
            return false;
        return true;
    }
    public User getUser(String Phone,String Pass)
    {
        User user;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Table1,null);
        while(cursor.moveToNext())
        {
            String phone = cursor.getString(0);
            String pass = cursor.getString(3);
            if(phone.equals(Phone) && pass.equals(Pass))
            {
                user = new User(cursor.getString(2),phone,pass,cursor.getString(1));
                return user;
            }
        }
        return null;
    }
    public ArrayList<History> getHistory(String Phone)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Table2,null);
        ArrayList<History> history= new ArrayList<History>();
        while(cursor.moveToNext())
        {
            String phone = cursor.getString(0);
            if(phone.equals(Phone))
            {
                String Date= cursor.getString(2);
                String Amount = cursor.getString(1);
                String CarNumber=cursor.getString(3);
                history.add(new History(phone,Amount,Date,CarNumber));
            }
        }
        return history;
    }
    public ArrayList<Reward> getReward(String Phone)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Table3,null);
        ArrayList<Reward> reward= new ArrayList<Reward>();
        while(cursor.moveToNext())
        {
            String phone = cursor.getString(0);
            if(phone.equals(Phone))
            {
                String Status= cursor.getString(2);
                String Amount = cursor.getString(1);
                reward.add(new Reward(phone,Amount,Status));
            }
        }
        return reward;
    }
}
