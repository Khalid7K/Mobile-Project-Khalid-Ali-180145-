package com.example.khalid_ali_180145;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "NEW.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAMES";
    public static final String COL3 = "Email_Address";

    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY, " +
                " NAMES TEXT, Email_Address TEXT)";

        db.execSQL(createTable);
    }

    /* Every time the dB is updated (or upgraded) */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /* Basic function to add data. REMEMBER: The fields
       here, must be in accordance with those in
       the onCreate method above.
    */
    public boolean addData(String id, String Name, String Email) {
        long result;

        Cursor cur=getSpecifiedResultByID(id);

        if(cur.getCount()==1)
            return false;
        else {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, id);
            contentValues.put(COL2, Name);
            contentValues.put(COL3, Email);

            result = db.insert(TABLE_NAME, null, contentValues);
        }


        //if data are inserted incorrectly, it will return -1
        if(result == -1 ) {
            return false;
        } else {
            return true;
        }
    }

    /* Returns only one result */
    public Cursor structuredQuery(int ID) {
        SQLiteDatabase db = this.getReadableDatabase(); // No need to write
        Cursor cursor = db.query(
                TABLE_NAME, new String[]{COL1,
                        COL2, COL3}, COL1 + "=?",
                new String[]{String.valueOf(ID)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }


    public Cursor getSpecificResult(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where NAMES=? ",new String[]{Name});
        return data;
    }

    public Cursor getSpecifiedResultByID(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where ID=?  ",new String[]{String.valueOf(ID)});
        return data;

    }
    public Cursor getSpecifiedRByID(int Email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where Email_Address=?  ",new String[]{String.valueOf(Email)});
        return data;

    }

    // Return everything inside a specific table
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public int dltRow(String Name){

        SQLiteDatabase db = this.getWritableDatabase();
        int delete=0;

        long result= DatabaseUtils.queryNumEntries(db,TABLE_NAME,"NAMES=?",new String[]{Name});

        if(result>=1)
            delete=db.delete(TABLE_NAME,"NAMES=?",new String[]{Name});

        return delete;

    }

    public void update(int NewID, String NewEmail ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,NewID);
        contentValues.put(COL3,NewEmail);

        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{String.valueOf(NewID)});
        db.update(TABLE_NAME,contentValues,"Email_Address=?",new String[]{String.valueOf(NewEmail)});

    }


}
