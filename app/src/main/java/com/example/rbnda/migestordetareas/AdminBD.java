package com.example.rbnda.migestordetareas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rbnda on 10/03/2018.
 */

public class AdminBD extends SQLiteOpenHelper {
    private static final String NOMBREBD ="ejemplo3.bd";
    private static final int VERSION =1;

    public AdminBD(Context context) {
        super(context, NOMBREBD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(LogicaBD.CREARTBLCONTACTOS());

    }
    public void INSERTAREGISTRO (SQLiteDatabase db,String nombre, String descripcion, String fecha, String hora, String realizado ){

        db.execSQL(LogicaBD.INSERTAREGISTRO(nombre,descripcion,fecha,hora,realizado));
    }

    public Cursor SELECTTABLA (SQLiteDatabase db){
        Cursor c =  db.rawQuery(LogicaBD.SELECTTABLA(),null);
        return c;
    }

    public void ELIMINARREGISTRO(SQLiteDatabase db,String id, String nombre, String descripcion){
        db.execSQL(LogicaBD.ELIMINARREGISTRO(id,nombre,descripcion));
    }
    public void MODIFICARREGISTRO(SQLiteDatabase db, String idn, String ide, String nombre, String descripcion, String hora, String fecha, String realizado){
        db.execSQL(LogicaBD.MODIFICARREGISTRO(idn,ide,nombre,descripcion,hora,fecha,realizado));
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
