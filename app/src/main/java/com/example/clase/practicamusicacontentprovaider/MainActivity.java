package com.example.clase.practicamusicacontentprovaider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.clase.practicamusicacontentprovaider.Adaptadores.Adaptador;
import com.example.clase.practicamusicacontentprovaider.Adaptadores.Adaptadordiscos;
import com.example.clase.practicamusicacontentprovaider.Gestion.GestorCanciones;
import com.example.clase.practicamusicacontentprovaider.tablas.Cancion;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
 //para guardar los datos de las tablas creamos arrays para meter nuestros objetos
    private ArrayList<Cancion> listaMusica = new ArrayList<Cancion>();
    ArrayList<Cancion> listae= new ArrayList<>();

 //declaramos los gestores de las BD

    private GestorCanciones gestorCanciones;


    private ListView lv;
    private Adaptador ad;
    private Adaptadordiscos adi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INICIAMOS los gestores de la bd
        gestorCanciones = new GestorCanciones(this);


    }
    public void init(){

    }
    //------------menu principal--------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_mostrarMusica: {
                lv = (ListView) findViewById(R.id.lvMostrar);
                listaMusica= new ArrayList<>();
                listaMusica= this.buscadorAudio();
                System.out.println("kira"+listaMusica.toString());
                ad = new Adaptador(this, R.layout.item, listaMusica);
                lv.setAdapter(ad);
                this.registerForContextMenu(lv);
                return true;
            }
            case R.id.menu_mostrarDiscos: {
                lv = (ListView) findViewById(R.id.lvMostrar);
                listae = gestorCanciones.selectDiscos();
                System.out.println(listae.toString()+"luise");
                adi = new Adaptadordiscos(this, R.layout.item, (ArrayList<Cancion>) listae);
                lv.setAdapter(adi);
                this.registerForContextMenu(lv);
                return true;
            }
            case R.id.menu_mostrarAudiobd: {


            }
            case R.id.menu_mostrSincronicar: {
                this.mensajeAlerta();
                return true;
            }
            case R.id.menu_mostraArtistas: {

            }
        }
        return super.onOptionsItemSelected(item);
    }
    //-----------------------------------------------------------
public ArrayList mostrarDiscos(){
    listaMusica = new ArrayList<>();
   ArrayList<Cancion> lista = new ArrayList<>();
    listaMusica = gestorCanciones.selectCanciones();
    for(int i = 0; i < listaMusica.size();i++) {

        for (int a = 0; a < lista.size(); a++) {
            if (!listaMusica.get(i).getAlbum().equals(lista.get(a).getAlbum())) {
                      lista.add(listaMusica.get(i));
            }
        }
        System.out.println("luiss"+lista.toString());
    }
    return lista;


}
    //---------Menu ListView-------------------------------------

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = menuInfo.position;
        switch (item.getItemId()) {
            case R.id.menu_Informacion: {
                Intent i = new Intent(this, MostrarMusica.class);
                i.putExtra("canciones", (Serializable) listaMusica.get(pos));
                startActivity(i);
                return true;
            }
            case R.id.menu_canciones: {
              String album = listae.get(pos).getAlbum();
                lv = (ListView) findViewById(R.id.lvMostrar);
                ArrayList<Cancion> listao= new ArrayList<>();
                listao = gestorCanciones.selectCanc(album);
                System.out.println(listao.toString()+"luise");
                ad = new Adaptador(this, R.layout.item, (ArrayList<Cancion>) listao);
                lv.setAdapter(ad);
                this.registerForContextMenu(lv);
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);
    }
    //-------------------------------------------------------------------
    /*--------------------------------------------------------------------*/
    @Override
    protected void onResume() {
        gestorCanciones.open();

        super.onResume();
        this.init();

    }
    @Override
    protected void onPause() {
        gestorCanciones.close();

        super.onPause();
    }
    /*---------buscarAudio------------------------------------------------*/

    private ArrayList buscadorAudio() {
        try {
            String TAG = "Audio";
            Cursor cur = getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                    null);

            if (cur == null) {
                //si esta nulo el cursor
                Log.e(TAG, "cursor nulo al leer las canciones(buscadorAudio)");

            } else if (!cur.moveToFirst()) {
                // la consulta esta mal en canciones
                Log.e(TAG, "conuslta errona canciones(buscadorAudio)");

            } else {
                Log.i(TAG, "lee canciones(buscadorAudio)");
                //vamos a rellenar nuestra array obtenemos los datos que nos interesan.
                do {
                    int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int filePathIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    int title2 = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int title3 = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                    int title4 = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
                    int title5 = cur.getColumnIndex(MediaStore.Audio.Media.COMPOSER);
                    int title6 = cur.getColumnIndex(MediaStore.Audio.Media.YEAR);
                    int title7 = cur.getColumnIndex(MediaStore.Images.Media.DATA);


                    System.out.println("artista*="+cur.getString(title2));
                    System.out.println("titulocancion*="+cur.getString(titleColumn));
                    System.out.println("albun*="+cur.getString(title3));
                    System.out.println("DURATION="+cur.getString(title4));
                    System.out.println("COMPOSER=" + cur.getString(title5));
                    System.out.println("año=" + cur.getString(title6));
                    System.out.println("Rutaimagen=" + cur.getString(title7));
                    System.out.println("--------------------------------------------");

                    Cancion cancion = new Cancion();
                    cancion.setNombreCancion(cur.getString(titleColumn));
                    cancion.setAlbum(cur.getString(title3));
                    cancion.setArtista(cur.getString(title2));
                    cancion.setAño(cur.getString(title6));
                    cancion.setDuracion(cur.getString(title4));
                    cancion.setImagen(cur.getString(title7));
                    listaMusica.add(cancion);




                } while (cur.moveToNext());
                System.out.println("luis" + listaMusica.toString());
                System.out.println("----------------------------------)");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList) listaMusica;
    }

    /*------------------------------------------------------------------------*/
  /*  public ArrayList  buscadorVideo(){

           // int title1 = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);//devuelve nombre carpeta donde esta
           // int title1 = cur.getColumnIndex(MediaStore.Audio.Media.COMPOSER);// devuelve 0
           //int title3 = cur.getColumnIndex(MediaStore.Audio.Media.YEAR);
         //   int title4 = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
           //int title2 = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);//devuelve <unknown>
           // int rutaimage = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
     /* String rutafoto = cur.getString(a);
            Bitmap bmThumbnail = ThumbnailUtils.
                    extractThumbnail(ThumbnailUtils.createVideoThumbnail(rutafoto,
                            MediaStore.Video.Thumbnails.MINI_KIND), 80, 50);
img.setImageBitmap(bmThumbnail);*/
          //  System.out.println(cur.getString(titleColumn)+"titulo luise");
          //  System.out.println(cur.getString(title1)+"luise");
         //  System.out.println(cur.getString(title3)+"año");
        //    System.out.println(cur.getString(title4)+"duracion");
           // System.out.println(cur.getString(rutaimage)+"luise");

      /*  try {
            String TAG = "Video";
            Cursor cur = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null);
            cur.moveToFirst();

            if (cur == null) {
                //si esta nulo el cursor
                System.out.println("nulo el cursor(buscadorVideo)");

            } else if (!cur.moveToFirst()) {
                // la consulta esta mal en canciones
                System.out.println("la consulta esta mal(buscadorVideo)");

            } else {
                System.out.println("lee canciones(buscadorVideo)");
                //vamos a rellenar nuestra array obtenemos los datos que nos interesan.
                do {
                    int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE) ;
                    int rutaimage = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    Disco disco = new Disco();
                    disco.setTiulo(cur.getString(titleColumn));
                    disco.setRutaImagen(cur.getString(rutaimage));
                    disco.setTipo("video");
                    listaVideos.add(disco);

                } while (cur.moveToNext());
                System.out.println("luisVIDEOS" + listaVideos.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList) listaVideos;
    }*/
    public void mensajeAlerta() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿  Si usted Acepta se borraran los datos actuales ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                gestorCanciones.borrarTodasCanciones();

                listaMusica = buscadorAudio();
                gestorCanciones.insertarCanciones((ArrayList<Cancion>) listaMusica);
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();

    }
}
