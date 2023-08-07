package com.example.steptofitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry1="create table users(name text,email text,phone text,password text)";
        db.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser(String name,String email,String phone,String pass)
    {
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("phone",phone);
        values.put("password",pass);

        SQLiteDatabase database=getWritableDatabase();
        database.insert("users",null,values);
        database.close();
    }


    public int fetchData(String username,String password)
    {
        int result=0;
        String str[]={username,password};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where email=? and password=?",str);
        if(cursor.moveToFirst())
        {
            result=1;
        }

        return result;
    }

    public ArrayList<String> getUserData(String email,String pass)
    {
        String str[]={email,pass};
        ArrayList<String> userData=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from users where email=? and password=?",str);

        while (cursor.moveToNext())
        {
            userData.add(cursor.getString(0));
            userData.add(cursor.getString(1));
            userData.add(cursor.getString(2));
            userData.add(cursor.getString(3));
        }
        db.close();
        return userData;
    }

    public ArrayList<String> getUserPassword(String email,String phone)
    {
        String str[]={email,phone};
        ArrayList<String> userData=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from users where email=? and phone=?",str);

        while (cursor.moveToNext())
        {
            userData.add(cursor.getString(0));
            userData.add(cursor.getString(1));
            userData.add(cursor.getString(2));
            userData.add(cursor.getString(3));
        }
        db.close();
        return userData;
    }


}
