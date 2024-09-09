package com.elcha.uts.tugassqlite;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mahasiswaManager";
    private static final String TABLE_MAHASISWA = "mahasiswa";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIM = "nim";
    private static final String KEY_PRODI = "prodi";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MAHASISWA_TABLE = "CREATE TABLE " + TABLE_MAHASISWA + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAMA + " TEXT,"
            + KEY_NIM + " TEXT,"
            + KEY_PRODI + " TEXT" + ")";
        db.execSQL(CREATE_MAHASISWA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }

    // Menambahkan data mahasiswa
    public void addMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, mahasiswa.getNama());
        values.put(KEY_NIM, mahasiswa.getNim());
        values.put(KEY_PRODI, mahasiswa.getProdi());

        db.insert(TABLE_MAHASISWA, null, values);
        db.close(); // Closing database connection
    }

    // Mendapatkan semua data mahasiswa
    @SuppressLint("Range")
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MAHASISWA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                mahasiswa.setNama(cursor.getString(cursor.getColumnIndex(KEY_NAMA)));
                mahasiswa.setNim(cursor.getString(cursor.getColumnIndex(KEY_NIM)));
                mahasiswa.setProdi(cursor.getString(cursor.getColumnIndex(KEY_PRODI)));
                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close(); // Closing database connection
        return mahasiswaList;
    }
}

