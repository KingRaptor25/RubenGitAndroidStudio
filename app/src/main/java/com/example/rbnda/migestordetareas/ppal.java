package com.example.rbnda.migestordetareas;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ppal extends AppCompatActivity {

    //variables para la conexion

    ImageButton botonmas;
    ListView lvlistaTareas;

    ArrayList<tareas> listaTareas= new ArrayList<tareas>();
    int contador = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppal);
        lvlistaTareas = (ListView) findViewById(R.id.lvTareas);
        botonmas = (ImageButton) findViewById(R.id.btnagregar);



        //se extrae de base de datos
        AdminBD administradorbd = new AdminBD(this);
        SQLiteDatabase BD = administradorbd.getWritableDatabase();
        //Para insertar en la base de datos
        Cursor c = administradorbd.SELECTTABLA(BD);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                tareas con = new tareas(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
                listaTareas.add(con);

            } while(c.moveToNext());

            Adaptador a = new Adaptador(this, listaTareas);
            lvlistaTareas.setAdapter(a);
        }

        //Metodo para ir a agregar
        botonmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ppal.this, AgregarTarea.class);
                startActivityForResult(i, 1);
            }
        });

        lvlistaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String n = listaTareas.get(position).getNombre();
                String d = listaTareas.get(position).getDescripcion();
                String f = listaTareas.get(position).getFecha();
                String h = listaTareas.get(position).getHora();
                String r = listaTareas.get(position).getRealizado();
                Intent i = new Intent(ppal.this, vertarea.class);
                i.putExtra("nombre", n);
                i.putExtra("descripcion", d);
                i.putExtra("fecha",f);
                i.putExtra("hora",h);
                i.putExtra("realizado",r);
                startActivity(i);


            }
        });

        registerForContextMenu(lvlistaTareas);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lvTareas) {
            AdapterView.AdapterContextMenuInfo infoTarea = (AdapterView.AdapterContextMenuInfo) menuInfo;
            String[] elementoMenu = getResources().getStringArray(R.array.elementosMenuContextual);
            menu.setHeaderTitle(listaTareas.get(infoTarea.position).getNombre());
            for (int i = 0; i < elementoMenu.length; i++) {
                menu.add(Menu.NONE, i, i, elementoMenu[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo infoContacto = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String[] elementoMenu = getResources().getStringArray(R.array.elementosMenuContextual);
        int posicionElementoSeleccionado = item.getItemId();
        String opcionSeleccionado = elementoMenu[posicionElementoSeleccionado];



        switch (opcionSeleccionado) {
            case "Editar":
                Intent i = new Intent(ppal.this,Editar.class);
                i.putExtra("nombre", listaTareas.get(infoContacto.position).getNombre());
                i.putExtra("descripcion", listaTareas.get(infoContacto.position).getDescripcion());
                i.putExtra("fecha", listaTareas.get(infoContacto.position).getFecha());
                i.putExtra("pos", infoContacto.position);
                i.putExtra("hora", listaTareas.get(infoContacto.position).getHora());
                i.putExtra("realizado",listaTareas.get(infoContacto.position).getRealizado());
                startActivityForResult(i, 2);
                break;
            case "Eliminar":
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Desea eliminar Tarea?")
                        .setTitle("Eliminar")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Continuar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //ELIMINAR DE BASE DE DATOS
                                        AdminBD administradorbd = new AdminBD(ppal.this);
                                        SQLiteDatabase BD = administradorbd.getWritableDatabase();
                                        administradorbd.ELIMINARREGISTRO(BD,listaTareas.get(infoContacto.position).getNombre(),listaTareas.get(infoContacto.position).getDescripcion(),listaTareas.get(infoContacto.position).getFecha());


                                        listaTareas.remove(infoContacto.position);
                                        Adaptador a = new Adaptador(ppal.this, listaTareas);
                                        lvlistaTareas.setAdapter(a);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                break;

        }
        return true;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK) {
                    //se presiono agregar
                    String nombre = data.getExtras().getString("nombre");
                    String desc = data.getExtras().getString("descripcion");
                    String fecha = data.getExtras().getString("fecha");
                    String hora = data.getExtras().getString("hora");
                    String realizado = data.getExtras().getString("realizado");

                    tareas usuario = new tareas(nombre, desc, fecha, hora,realizado);
                    listaTareas.add(usuario);
                    Adaptador a = new Adaptador(this, listaTareas);
                    //agregar tambien a la base de datos
                    AdminBD administradorbd = new AdminBD(this);
                    SQLiteDatabase BD = administradorbd.getWritableDatabase();
                    //Para insertar en la base de datos
                    administradorbd.INSERTAREGISTRO(BD,nombre,desc,hora,fecha,realizado);

                    //se va a la lista
                    lvlistaTareas.setAdapter(a);

                }else{
                    // Toast.makeText(this, "operacion cancelada", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    //se presiono agregar
                    String nombre = data.getExtras().getString("nombre");
                    String desc = data.getExtras().getString("descripcion");
                    String fecha = data.getExtras().getString("fecha");
                    String hora = data.getExtras().getString("hora");
                    String realizado = data.getExtras().getString("realizado");

                    int pos = data.getExtras().getInt("pos");

                    String idn = data.getExtras().getString("nomModificar");
                    String ide = data.getExtras().getString("emailModificar");

                    tareas usuario = new tareas(nombre, desc, fecha, hora,realizado);
                    //modificar en la lista
                    listaTareas.set(pos,usuario);
                    Adaptador a = new Adaptador(this, listaTareas);
                    lvlistaTareas.setAdapter(a);



                    //modificar base de datos
                    AdminBD administradorbd = new AdminBD(this);
                    SQLiteDatabase BD = administradorbd.getWritableDatabase();
                    //Para insertar en la base de datos

                    administradorbd.MODIFICARREGISTRO(BD,idn,ide,nombre,desc,hora,fecha,realizado);

                }else{
                    Toast.makeText(this, "operacion cancelada", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }


}
