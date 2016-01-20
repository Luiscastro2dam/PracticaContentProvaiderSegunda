package com.example.clase.practicamusicacontentprovaider.tablas;

import java.io.Serializable;

/**
 * Created by Clase on 18/01/2016.
 */
public class Cancion implements Serializable{

    private String nombreCancion,artista,album,duracion,getCompositor,año,imagen;
    private int idCancion;

    public Cancion() {
    }

    public Cancion(String nombreCancion, String artista, String album, String duracion, String getCompositor, String año, String imagen, int idCancion) {
        this.nombreCancion = nombreCancion;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.getCompositor = getCompositor;
        this.año = año;
        this.imagen = imagen;
        this.idCancion = idCancion;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "nombreCancion='" + nombreCancion + '\'' +
                ", artista='" + artista + '\'' +
                ", album='" + album + '\'' +
                ", duracion='" + duracion + '\'' +
                ", getCompositor='" + getCompositor + '\'' +
                ", año='" + año + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idCancion=" + idCancion +
                '}';
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGetCompositor() {
        return getCompositor;
    }

    public void setGetCompositor(String getCompositor) {
        this.getCompositor = getCompositor;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }
}
