package com.example.Manga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mangaDB";
    private static final String TABLE_NAME = "manga";
    private static final String COL_ID = "id";
    private static final String COL_TITRE = "titre";
    private static final String COL_AUTEUR = "auteur";
    private static final String COL_GENRE = "genre";
    private static final String COL_CHAPITRES = "chapitres";

    public BaseDHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITRE + " TEXT, " +
                COL_AUTEUR + " TEXT, " +
                COL_GENRE + " TEXT, " +
                COL_CHAPITRES + " INTEGER)";
        db.execSQL(createTable);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean ajouterManga(String titre, String auteur, String genre, int chapitres) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TITRE, titre);
        contentValues.put(COL_AUTEUR, auteur);
        contentValues.put(COL_GENRE, genre);
        contentValues.put(COL_CHAPITRES, chapitres);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;  // Retourne true si l'insertion a réussi
    }

    public Cursor obtenirTousLesMangas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean supprimerManga(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }
}

