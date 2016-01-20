package com.example.clase.practicamusicacontentprovaider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clase.practicamusicacontentprovaider.tablas.Cancion;

/**
 * Created by Clase on 19/01/2016.
 */
public class MostrarMusica extends AppCompatActivity {
    Cancion cancion;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);
        img = (ImageView)findViewById(R.id.img);
        cancion = (Cancion) getIntent().getSerializableExtra("canciones");
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        tv1.setText(cancion.getNombreCancion().toString());
        tv2.setText("  ALBUM: "+cancion.getAlbum().toString());
        tv3.setText("ARTISTA: "+(cancion.getArtista()));
        tv4.setText("AÑO: "+(cancion.getAño()));
        tv5.setText("DURACION: "+(cancion.getDuracion()));
        tv6.setText("COMPOSITOR: "+(cancion.getGetCompositor()));
        String ruta = cancion.getImagen().toString();



    }
}
