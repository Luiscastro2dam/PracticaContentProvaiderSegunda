package com.example.clase.practicamusicacontentprovaider.BaseDatos;

import android.provider.BaseColumns;

/**
 * Created by Clase on 11/01/2016.
 */
public class Galeria {

    private Galeria(){

    }
    public static abstract class TablaCanciones implements
            BaseColumns {
        public static final String TABLA = "Canciones";
        public static final String NOMBRECANCION = "nombreCancion";
        public static final String ARTISTA= "artista";
        public static final String ALBUM= "album";
        public static final String DURACION= "duracion";
        public static final String COMPOSITOR= "compositor";
        public static final String AÑO= "año";
        public static final String IMAGEN= "imagen";

    }


}
