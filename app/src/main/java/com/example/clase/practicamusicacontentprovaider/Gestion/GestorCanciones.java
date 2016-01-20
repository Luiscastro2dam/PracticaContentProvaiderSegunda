package com.example.clase.practicamusicacontentprovaider.Gestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.practicamusicacontentprovaider.BaseDatos.Ayudante;
import com.example.clase.practicamusicacontentprovaider.BaseDatos.Galeria;
import com.example.clase.practicamusicacontentprovaider.tablas.Cancion;

import java.util.ArrayList;

/**
 * Created by Clase on 11/01/2016.
 */
public class GestorCanciones {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorCanciones(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }
    public ArrayList<Cancion> selectCanc(String album){
       // String[] args = new String[] {"Un alumno más"};
        String a ="Un alumno más";
        //Cursor c = bd.rawQuery("SELECT * FROM canciones where album like", null);
        Cursor c = bd.rawQuery("SELECT * from canciones where album like '"+a+"'", null);
        ArrayList<Cancion> listaa = new ArrayList<Cancion>();
//Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                System.out.println(c.getString(0) + "luiss3");
                Cancion cancion = new Cancion();
                cancion.setNombreCancion(c.getString(1));
                listaa.add(cancion);
            } while(c.moveToNext());
        }
        System.out.println("luiss1" + listaa.toString());
        return listaa;
    }
    public ArrayList<Cancion> selectDiscos(){
        Cursor c = bd.rawQuery("SELECT distinct(album) FROM canciones", null);
        ArrayList<Cancion> listaa = new ArrayList<Cancion>();
//Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                System.out.println(c.getString(0) + "luiss");
                Cancion cancion = new Cancion();
                cancion.setAlbum(c.getString(0));
                listaa.add(cancion);
            } while(c.moveToNext());
        }
        System.out.println("luiss1" + listaa.toString());
        return listaa;
    }
    public ArrayList<Cancion> selectCanciones() {
        ArrayList<Cancion> lista;
        lista = new ArrayList<Cancion>();
        Cursor cursor = bd.query(Galeria.TablaCanciones.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Cancion cancion;
        while (!cursor.isAfterLast()) {
            cancion = getRowCancion(cursor);
            lista.add(cancion);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Cancion());
            return lista;
        }
        return lista;
    }
    public Cancion getRowCancion(Cursor c) {
        Cancion cancion = new Cancion();
        cancion.setIdCancion(c.getInt(c.getColumnIndex(Galeria.TablaCanciones._ID)));
        cancion.setNombreCancion(c.getString(1));
        cancion.setAlbum(c.getString(2));
        cancion.setArtista(c.getString(3));
        cancion.setAño(c.getString(4));
        cancion.setGetCompositor(c.getString(5));
        cancion.setDuracion(c.getString(6));

        return cancion;
    }
    public void insertarCanciones(ArrayList<Cancion> listaCanciones) {
        for (int i = 0; i < listaCanciones.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("nombreCancion", listaCanciones.get(i).getNombreCancion());
            values.put("artista", listaCanciones.get(i).getArtista());
            values.put("album", listaCanciones.get(i).getAlbum());
            values.put("duracion", listaCanciones.get(i).getDuracion());
            values.put("compositor", listaCanciones.get(i).getGetCompositor());
            values.put("año", listaCanciones.get(i).getAño());
            values.put("imagen", listaCanciones.get(i).getImagen());
            bd.insert(Galeria.TablaCanciones.TABLA, null, values);
        }
    }

   /* public void insertarCanciones(ArrayList<Cancion> listaCanciones) {
        for (int i = 0; i < listaCanciones.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("nombreCancion", listaCanciones.get(i).getNombreCancion());
            values.put("idDisco", listaCanciones.get(i).getIdDisco());
            bd.insert(Galeria.TablaCanciones.TABLA, null, values);
        }
    }
    public List<Cancion> selectCanciones() {
        List<Cancion> lista;
        lista = new ArrayList<Cancion>();
        Cursor cursor = bd.query(Galeria.TablaCanciones.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Cancion cancion;
        while (!cursor.isAfterLast()) {
            cancion = getRowCancion(cursor);
            lista.add(cancion);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Cancion());
            return lista;
        }
        return lista;
    }
    public Cancion getRowCancion(Cursor c) {
        Cancion cancion = new Cancion();
        cancion.setIdCancion(c.getInt(c.getColumnIndex(Galeria.TablaCanciones._ID)));
        cancion.setNombreCancion(c.getString(1));
        cancion.setIdDisco(c.getInt(2));
        return cancion;
    }
    /*
    public void insertarCanciones(ArrayList<Cancion> listaCanciones){
           for (int i =0 ; i <listaCanciones.size();i++){
               ContentValues values = new ContentValues();
               values.put("nombreCancion", listaCanciones.get(i).getNombre());
               values.put("ruta", listaCanciones.get(i).getRuta());
               values.put("tipo", listaCanciones.get(i).getTipo());
               bd.insert(Galeria.TablaCanciones.TABLA, null, values);
           }
    }
    public List<Cancion> selectCanciones() {
        List<Cancion> lista;
        lista = new ArrayList<Cancion>();
        Cursor cursor = bd.query(Galeria.TablaCanciones.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Cancion cancion;
        while (!cursor.isAfterLast()) {
            cancion = getRowCancion(cursor);
            lista.add(cancion);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Cancion());
            return lista;
        }
        return lista;
    }
    public Cancion getRowCancion(Cursor c) {
       Cancion cancion = new Cancion();
        cancion.setIdCancion(c.getInt(c.getColumnIndex(Galeria.TablaCanciones._ID)));
        cancion.setNombre(c.getString(1));
        cancion.setRuta(c.getString(2));
        cancion.setTipo(c.getString(3));

        return cancion;
    }
    */
    public void borrarTodasCanciones(){
               bd.delete(Galeria.TablaCanciones.TABLA, null,
                       null);
    }
}
