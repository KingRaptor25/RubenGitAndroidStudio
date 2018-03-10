package com.example.rbnda.migestordetareas;

/**
 * Created by rbnda on 10/03/2018.
 */

public class tareas {

    private String nombre;
    private String descripcion;
    private String fecha;
    private String hora;
    private String realizado;

    public tareas(){
        nombre="";
        descripcion="";
        fecha="";
        hora="";
        realizado="";
    }
    public tareas(String n, String d, String f, String h, String r){
        nombre=n;
        descripcion=d;
        fecha=f;
        hora=h;
        realizado=r;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getRealizado() {
        return realizado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }
}
