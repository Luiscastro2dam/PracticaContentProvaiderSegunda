package com.example.clase.practicamusicacontentprovaider.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clase on 11/01/2016.
 */
public class Ayudante extends SQLiteOpenHelper {
//Para poder crear bases de datos en nuestra aplicación
// debemos usar las clases hijas de "SQLiteOpenHelper".

    // Nombre de nuestro archivo de base de datos
    public static final String DATABASE_NAME = "musicaSolo.sqlite";
    public static final int DATABASE_VERSION = 2;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    //se encargara de crear las tablas si no existen
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql="create table "+ Galeria.TablaCanciones.TABLA+
                " ("+ Galeria.TablaCanciones._ID+
                " integer primary key autoincrement, "+
                Galeria.TablaCanciones.NOMBRECANCION+" text, "+
                Galeria.TablaCanciones.ALBUM+" text, "+
                Galeria.TablaCanciones.ARTISTA+" text, "+
                Galeria.TablaCanciones.AÑO+" text, "+
                Galeria.TablaCanciones.COMPOSITOR+" text, "+
                Galeria.TablaCanciones.DURACION+" text, "+
                Galeria.TablaCanciones.IMAGEN+" text) ";
        db.execSQL(sql);

    }
    // las actualizara
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "+ Galeria.TablaCanciones.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }

}

