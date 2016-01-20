package com.example.clase.practicamusicacontentprovaider.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clase.practicamusicacontentprovaider.R;
import com.example.clase.practicamusicacontentprovaider.tablas.Cancion;

import java.util.ArrayList;

/**
 * Created by 2dam on 05/10/2015.
 */
public class Adaptador extends ArrayAdapter<Cancion>{

    private int res;
    private LayoutInflater lInflator;
    private ArrayList<Cancion> valores;
    private Context con;

    static class ViewHolder{
        public TextView tvTitulo;
        public ImageView imgFoto;
    }


    public Adaptador(Context context, int resource, ArrayList<Cancion> objects) {
        super(context, resource, objects);
        this.res= resource; // LAYOUT
        this.valores= objects; // LISTA DE VALORES
        this.con= context;
        lInflator=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv= new ViewHolder();
        if(convertView==null){
            convertView= lInflator.inflate(res, null);
            TextView tvTitulo= (TextView)convertView.findViewById(R.id.tvTitulo) ;
            gv.tvTitulo=tvTitulo;
            ImageView imgFoto= (ImageView) convertView.findViewById(R.id.imgFoto);
            gv.imgFoto= imgFoto;
            convertView.setTag(gv);
        }else{
            gv= (ViewHolder) convertView.getTag();
        }
        gv.tvTitulo.setText(valores.get(position).getNombreCancion());
        Bitmap bit= this.stringToBitMap(valores.get(position).getImagen());
               gv.imgFoto.setImageBitmap(bit);
           gv.imgFoto.setImageResource(R.drawable.ic_action);


        return convertView;
    }

    public Bitmap stringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
