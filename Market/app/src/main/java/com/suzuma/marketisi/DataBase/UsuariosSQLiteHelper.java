package com.suzuma.marketisi.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maestro on 03/11/2016.
 */

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate="CREATE TABLE Usuarios(codigo INTEGER, nombre TEXT)";

    public UsuariosSQLiteHelper(Context contexto, String    nombre,
                                CursorFactory factory,
                                int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(sqlCreate);
    }
}
