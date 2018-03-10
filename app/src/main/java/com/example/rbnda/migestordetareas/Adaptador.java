package com.example.rbnda.migestordetareas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by rbnda on 10/03/2018.
 */

//Los adaptadores ya tienen una base
//derivamos de otra clase
public class Adaptador extends BaseAdapter {
    //declarar dos elementos propios de la clase
    Context contexto;

    ArrayList<tareas> listatareas;

    public Adaptador(Context c, ArrayList<tareas> l){
        //CONSTRUCTOR
        contexto=c;
        listatareas=l;
    }

    @Override
    public int getCount() { //cuantos elementos hay en la lista
        return listatareas.size();
    }

    @Override
    public Object getItem(int position) { //regresa el elemento que seleccionaste en el List_View
        return listatareas.get(position);

    }

    @Override
    public long getItemId(int position) { //Regresa la posicion del elemento seleccionado
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //la vista seleccionada osea el renglon
        //objeto de la clase view
        View renglon=convertView;
        if(convertView==null) {
            //viene dessinflada
            //codigo para inflar la vista
            LayoutInflater inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //primer argumento es el layout personalizado
            //los ultimos dos quien sabe
            renglon = inflador.inflate(R.layout.renglon, parent, false);
        }

        TextView tvNombre= (TextView) renglon.findViewById(R.id.tvnombre);
        TextView tvDescripcion = (TextView) renglon.findViewById(R.id.tvdescripcion);
        TextView tvFecha = (TextView) renglon.findViewById(R.id.tvfecha);
        TextView tvHora = (TextView) renglon.findViewById(R.id.tvhora);
        TextView tvRealizado = (TextView) renglon.findViewById(R.id.tvrealizado) ;

        tvNombre.setText(listatareas.get(position).getNombre());
        tvDescripcion.setText(listatareas.get(position).getDescripcion());
        tvFecha.setText(listatareas.get(position).getFecha());
        tvHora.setText(listatareas.get(position).getHora());
        tvRealizado.setText(listatareas.get(position).getRealizado());



        return renglon;
    }
}