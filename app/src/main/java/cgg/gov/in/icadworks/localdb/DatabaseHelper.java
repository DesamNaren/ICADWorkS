package cgg.gov.in.icadworks.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import cgg.gov.in.icadworks.model.request.OTUpdateRequest;

/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ots_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(OTUpdateRequest.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + OTUpdateRequest.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }



    public OTUpdateRequest getOT(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(OTUpdateRequest.TABLE_NAME,
                new String[]{OTUpdateRequest.COLUMN_ID, OTUpdateRequest.COLUMN_OTS,
                        OTUpdateRequest.COLUMN_STR_ID,
                        OTUpdateRequest.COLUMN_STR_NO,
                        OTUpdateRequest.COLUMN_Photo,
                        OTUpdateRequest.COLUMN_User,
                        OTUpdateRequest.COLUMN_Pass},
                OTUpdateRequest.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        OTUpdateRequest note = new OTUpdateRequest(
                cursor.getInt(cursor.getColumnIndex(OTUpdateRequest.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_OTS)),
                cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_ID)),
                cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_NO)),
                cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Photo)),
                cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_User)),
                cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Pass)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<OTUpdateRequest> getAllNotes() {
        List<OTUpdateRequest> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + OTUpdateRequest.TABLE_NAME + " ORDER BY " +
                OTUpdateRequest.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                OTUpdateRequest note = new OTUpdateRequest();
                note.setId(cursor.getInt(cursor.getColumnIndex(OTUpdateRequest.COLUMN_ID)));
                note.setOts(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_OTS)));
                note.setStructureId(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_ID)));
                note.setStructureNo(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_NO)));
                note.setPhoto(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Photo)));
                note.setUser(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_User)));
                note.setPassword(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Pass)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + OTUpdateRequest.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }



    public int updateNote(OTUpdateRequest note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OTUpdateRequest.COLUMN_STR_ID, note.getStructureId());

        // updating row
        return db.update(OTUpdateRequest.TABLE_NAME, values, OTUpdateRequest.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }


    public void deleteNote(OTUpdateRequest note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(OTUpdateRequest.TABLE_NAME, OTUpdateRequest.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
