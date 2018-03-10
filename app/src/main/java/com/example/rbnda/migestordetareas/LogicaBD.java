package com.example.rbnda.migestordetareas;

/**
 * Created by rbnda on 10/03/2018.
 */

public class LogicaBD {

    public static final String NOMBRETBLTAREAS="tbltareas";
    public static final String CAMPO_ID="idtareas";
    public static final String CAMPO_NOMBRE="nombretarea";
    public static final String CAMPO_DESCRIPCION_TAREA="descripciontarea";
    public static final String CAMPO_HORA="horatarea";
    public static final String CAMPO_FECHA="horafecha";
    public static final String CAMPO_REALIZADO="tarearealizada";



    public static final String CREARTBLCONTACTOS() {
        return  "CREATE TABLE " + NOMBRETBLTAREAS + "( " +
                CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAMPO_NOMBRE + " TEXT NOT NULL, " +
                CAMPO_DESCRIPCION_TAREA + " TEXT, " +
                CAMPO_HORA + " TEXT, " +
                CAMPO_FECHA+ " TEXT," +
                CAMPO_REALIZADO+ " TEXT )";
    }

    public static final String INSERTAREGISTRO(String nombre, String descripcion, String hora,String fecha, String realizada) {
        return   "INSERT INTO "
                + NOMBRETBLTAREAS
                + " ("
                + CAMPO_NOMBRE + ","
                + CAMPO_DESCRIPCION_TAREA + ","
                + CAMPO_HORA + ","
                + CAMPO_FECHA + ","
                + CAMPO_REALIZADO + ") VALUES ("
                + "'"+ nombre +"','"+ descripcion +"','"+ hora +"','"+ fecha +"','"+ realizada +"');";
    }

    public static final String SELECTTABLA(){
        return "SELECT * FROM "+ NOMBRETBLTAREAS;
    }

    public static final String ELIMINARREGISTRO(String nombre,String descripcion, String fecha){
        return "DELETE FROM "+ NOMBRETBLTAREAS + " WHERE " + CAMPO_NOMBRE + " = " +"'"+ nombre +"'" + " AND " + CAMPO_DESCRIPCION_TAREA + " = " + "'"+descripcion+"'" + " AND " + CAMPO_FECHA +" = " + "'"+fecha+"'";
    }

    public static final String MODIFICARREGISTRO(String idn,String ide,String nombre, String descripcion, String fecha, String hora, String realizada){
        return
                "UPDATE "+ NOMBRETBLTAREAS +
                        " SET "+ CAMPO_NOMBRE +" = "+"'"+nombre+"' , "+
                        CAMPO_DESCRIPCION_TAREA +" = "+"'"+descripcion+"' , "+
                        CAMPO_HORA +" = "+"'"+hora+"' , "+
                        CAMPO_FECHA +" = "+"'"+fecha+"' , "+
                        CAMPO_REALIZADO + " = "+"'"+realizada+"'"+
                        " WHERE "+ CAMPO_ID + " = "+"'"+idn+"'"+" AND "+ CAMPO_NOMBRE + " = "+ "'"+ide+ "'"+";";
    }

}