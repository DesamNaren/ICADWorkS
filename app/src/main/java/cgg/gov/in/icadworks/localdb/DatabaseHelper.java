//package cgg.gov.in.icadworks.localdb;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cgg.gov.in.icadworks.model.request.OTUpdateRequest;
//
///**
// * Created by ravi on 15/03/18.
// */
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//
//    // Database Name
//    private static final String DATABASE_NAME = "ots_db";
//
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    // Creating Tables
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // create notes table
//        db.execSQL(OTUpdateRequest.CREATE_TABLE);
//    }
//
//    // Upgrading database
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + OTUpdateRequest.TABLE_NAME);
//
//        // Create tables again
//        onCreate(db);
//    }
//
//
//    public long insertOT(OTUpdateRequest otUpdateRequest) {
//        // get writable database as we want to write data
//        long id = 0;
//        try {
//
//            SQLiteDatabase db = this.getWritableDatabase();
//
//            ContentValues values = new ContentValues();
//            // `id` and `timestamp` will be inserted automatically.
//            // no need to add them
//            values.put(OTUpdateRequest.COLUMN_OTS, otUpdateRequest.getOts());
//            values.put(OTUpdateRequest.COLUMN_STR_ID, otUpdateRequest.getStructureId());
//            values.put(OTUpdateRequest.COLUMN_STR_NO, otUpdateRequest.getStructureNo());
//            values.put(OTUpdateRequest.COLUMN_Photo, otUpdateRequest.getPhoto());
//            values.put(OTUpdateRequest.COLUMN_User, otUpdateRequest.getUser());
//            values.put(OTUpdateRequest.COLUMN_Pass, otUpdateRequest.getPassword());
//            values.put(OTUpdateRequest.COLUMN_EMPID, otUpdateRequest.getEmpId());
//            values.put(OTUpdateRequest.COLUMN_DESI, otUpdateRequest.getDes());
//            values.put(OTUpdateRequest.COLUMN_POSTTYPE, otUpdateRequest.getPost());
//
//            // insert row
//            id = db.insert(OTUpdateRequest.TABLE_NAME, null, values);
//
//            // close db connection
//            db.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // return newly inserted row id
//        return id;
//    }
//
//
//    public OTUpdateRequest getOT(String strNo, String empId, String des, String post) {
//        OTUpdateRequest note = null;
//        try {
//            // get readable database as we are not inserting anything
//            SQLiteDatabase db = this.getReadableDatabase();
//
//            Cursor cursor = db.query(OTUpdateRequest.TABLE_NAME,
//                    new String[]{OTUpdateRequest.COLUMN_ID, OTUpdateRequest.COLUMN_OTS,
//                            OTUpdateRequest.COLUMN_STR_ID,
//                            OTUpdateRequest.COLUMN_STR_NO,
//                            OTUpdateRequest.COLUMN_Photo,
//                            OTUpdateRequest.COLUMN_User,
//                            OTUpdateRequest.COLUMN_Pass,
//                            OTUpdateRequest.COLUMN_EMPID,
//                            OTUpdateRequest.COLUMN_DESI,
//                            OTUpdateRequest.COLUMN_POSTTYPE},
//                    OTUpdateRequest.COLUMN_STR_NO + "=?" + " and " + OTUpdateRequest.COLUMN_EMPID + "=?"
//                            + " and " + OTUpdateRequest.COLUMN_DESI + "=?" + " and " + OTUpdateRequest.COLUMN_POSTTYPE + "=?",
//                    new String[]{strNo, empId, des, post}, null, null, null, null);
//
//            if (cursor != null)
//                cursor.moveToFirst();
//
//            // prepare note object
//            note = new OTUpdateRequest(
//                    cursor.getInt(cursor.getColumnIndex(OTUpdateRequest.COLUMN_ID)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_OTS)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_ID)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_NO)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Photo)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_User)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Pass)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_EMPID)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_DESI)),
//                    cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_POSTTYPE)));
//
//            // close the db connection
//            cursor.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return note;
//    }
//
//    public List<OTUpdateRequest> getAllOTs(String empId, String des, String post) {
//        List<OTUpdateRequest> notes = new ArrayList<>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(OTUpdateRequest.TABLE_NAME,
//                new String[]{OTUpdateRequest.COLUMN_ID, OTUpdateRequest.COLUMN_OTS,
//                        OTUpdateRequest.COLUMN_STR_ID,
//                        OTUpdateRequest.COLUMN_STR_NO,
//                        OTUpdateRequest.COLUMN_Photo,
//                        OTUpdateRequest.COLUMN_User,
//                        OTUpdateRequest.COLUMN_Pass,
//                        OTUpdateRequest.COLUMN_EMPID,
//                        OTUpdateRequest.COLUMN_DESI,
//                        OTUpdateRequest.COLUMN_POSTTYPE},
//                OTUpdateRequest.COLUMN_EMPID + "=?"
//                        + " and " + OTUpdateRequest.COLUMN_DESI + "=?" + " and " + OTUpdateRequest.COLUMN_POSTTYPE + "=?",
//                new String[]{empId, des, post}, null, null, null, null);
//
//        if (cursor != null) {
//
//            if (cursor.moveToFirst()) {
//                do {
//                    OTUpdateRequest note = new OTUpdateRequest();
//                    note.setId(cursor.getInt(cursor.getColumnIndex(OTUpdateRequest.COLUMN_ID)));
//                    note.setOts(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_OTS)));
//                    note.setStructureId(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_ID)));
//                    note.setStructureNo(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_STR_NO)));
//                    note.setPhoto(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Photo)));
//                    note.setUser(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_User)));
//                    note.setPassword(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_Pass)));
//                    note.setPassword(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_EMPID)));
//                    note.setPassword(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_DESI)));
//                    note.setPassword(cursor.getString(cursor.getColumnIndex(OTUpdateRequest.COLUMN_POSTTYPE)));
//
//                    notes.add(note);
//                } while (cursor.moveToNext());
//            }
//
//            cursor.close();
//        }
//
//        // close db connection
//        db.close();
//
//        // return notes list
//        return notes;
//    }
//
//    public int getNotesCount() {
//        String countQuery = "SELECT  * FROM " + OTUpdateRequest.TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//
//        // return count
//        return count;
//    }
//
//
//    public int updateNote(OTUpdateRequest otUpdateRequest) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(OTUpdateRequest.COLUMN_OTS, otUpdateRequest.getOts());
//        values.put(OTUpdateRequest.COLUMN_STR_ID, otUpdateRequest.getStructureId());
//        values.put(OTUpdateRequest.COLUMN_STR_NO, otUpdateRequest.getStructureNo());
//        values.put(OTUpdateRequest.COLUMN_Photo, otUpdateRequest.getPhoto());
//        values.put(OTUpdateRequest.COLUMN_User, otUpdateRequest.getUser());
//        values.put(OTUpdateRequest.COLUMN_Pass, otUpdateRequest.getPassword());
//
//        // updating row
//        return db.update(OTUpdateRequest.TABLE_NAME, values, OTUpdateRequest.COLUMN_STR_NO + " = ?",
//                new String[]{String.valueOf(otUpdateRequest.getStructureNo())});
//    }
//
//
//    public void deleteNote(OTUpdateRequest note) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(OTUpdateRequest.TABLE_NAME, OTUpdateRequest.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(note.getId())});
//        db.close();
//    }
//}
