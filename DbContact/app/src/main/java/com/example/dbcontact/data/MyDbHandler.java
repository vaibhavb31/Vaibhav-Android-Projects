package com.example.dbcontact.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.dbcontact.Model.Contacts;
import com.example.dbcontact.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler( Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PHONE + " TEXT" + ")";
        Log.d("tab", "Query being run is : "+ create);
        db.execSQL(create);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //Add Contact In DB
    public void addContact(Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());


        db.insert(Params.TABLE_NAME, null, values);
        Log.d("data", "Successfully inserted");
        db.close();


    }
    //Read Contact From DB
    public List<Contacts> getContacts()
    {
        List<Contacts> contactsList= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // DB query
        String select= "SELECT * FROM "+Params.TABLE_NAME;
        Cursor cursor= db.rawQuery(select, null);

        // loop all contacts in table
        if(cursor.moveToFirst())
        {
            do{
                Contacts contacts= new Contacts();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setPhoneNumber(cursor.getString(2));
                contactsList.add(contacts);
            }while (cursor.moveToNext());
        }
        return contactsList;

    }

    public void deleteContact(int idtxt) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID +"=?", new String[]{String.valueOf(idtxt)});
        db.close();
    }

    public int updateContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contacts.getName());
        values.put(Params.KEY_PHONE, contacts.getPhoneNumber());

        //Lets update now
        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(contacts.getId())});

    }
}
