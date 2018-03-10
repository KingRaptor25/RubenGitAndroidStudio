package com.example.rbnda.migestordetareas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class vertarea extends AppCompatActivity {

    EditText nombre,descripcion,fecha,hora;
    TextView realizado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertarea);
        nombre = (EditText) findViewById(R.id.etnombre);
        descripcion = (EditText) findViewById(R.id.etdescripcion);
        fecha = (EditText) findViewById(R.id.etfecha);
        hora = (EditText) findViewById(R.id.ethora);
        realizado = (TextView) findViewById(R.id.etrealizado);

        nombre.setText(getIntent().getExtras().get("nombre").toString());
        descripcion.setText(getIntent().getExtras().get("descripcion").toString());
        fecha.setText(getIntent().getExtras().get("fecha").toString());
        hora.setText(getIntent().getExtras().get("hora").toString());
        realizado.setText(getIntent().getExtras().get("realizado").toString());


    }
}
