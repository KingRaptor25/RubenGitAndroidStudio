package com.example.rbnda.migestordetareas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Calendar;

public class AgregarTarea extends AppCompatActivity implements View.OnClickListener{

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String PUNTOS = ":";


    //Calendario
    public final Calendar c = Calendar.getInstance();
    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText nombre;
    EditText descripcion;
    EditText etFecha;
    EditText etHora;
    Button agregar;
    TextView txtrealizado;
    Spinner spinnerRealizado;
    ImageButton ibObtenerFecha;
    ImageButton ibObtenerHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);
        //Widget EditText donde se mostrara la fecha obtenida
        nombre = (EditText) findViewById(R.id.etnombre);
        descripcion = (EditText) findViewById(R.id.etdescripcion);
        etFecha = (EditText) findViewById(R.id.etfecha);
        etHora = (EditText) findViewById(R.id.ethora);
        spinnerRealizado = (Spinner) findViewById(R.id.spEstatus);
        txtrealizado = (TextView) findViewById(R.id.txtrealizado) ;

       String [] estatus = getResources().getStringArray(R.array.arrayspinner);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,estatus);
        spinnerRealizado.setAdapter(adaptador);

        spinnerRealizado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                txtrealizado.setText(String.valueOf(parent.getItemAtPosition(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (ImageButton) findViewById(R.id.ibfecha);
        ibObtenerHora = (ImageButton) findViewById(R.id.ibhora);
        //Evento setOnClickListener - clic
        ibObtenerHora.setOnClickListener(this);
        ibObtenerFecha.setOnClickListener(this);

        String nomMod="";
        String descMod="";
        String fechaMod="";
        String horaMod="";
        String realizado="";

        int pos = 0;
        if(getIntent()!=null) {
            if (getIntent().getExtras() != null) {
                nombre.setText(getIntent().getExtras().get("nombre").toString());
                descripcion.setText(getIntent().getExtras().get("descripcion").toString());
                etFecha.setText(getIntent().getExtras().get("fecha").toString());
                etHora.setText(getIntent().getExtras().get("hora").toString());
                txtrealizado.setText(getIntent().getExtras().get("realizada").toString());


                descMod = getIntent().getExtras().get("descripcion").toString();
                nomMod = getIntent().getExtras().get("nombre").toString();
                fechaMod = getIntent().getExtras().get("fecha").toString();
                horaMod = getIntent().getExtras().get("hora").toString();
                realizado = getIntent().getExtras().get("realizado").toString();



                pos = getIntent().getExtras().getInt("pos");


            }
        }

        agregar = (Button) findViewById(R.id.btnagregar);
        final int finalPos = pos;
        final String finaldescMod = descMod;
        final String finalNomMod = nomMod;
        final String finalFechaMod = fechaMod;
        final String finalHoraMod = horaMod;
        final String finalRealizadoMod = realizado;

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String n = nombre.getText().toString();
                String d = descripcion.getText().toString();
                String f = etFecha.getText().toString();
                String h = etHora.getText().toString();
                String r = txtrealizado.getText().toString();



                Intent i = getIntent();
                i.putExtra("nombre",n);
                i.putExtra("descripcion",d);
                i.putExtra("fecha",f);
                i.putExtra("hora",h);
                i.putExtra("realizado",r);

                i.putExtra("nomModificar", finalNomMod);
                i.putExtra("descripcionModificar", finaldescMod);
                i.putExtra("fechaModificar", finalFechaMod);
                i.putExtra("horaModificar", finalHoraMod);
                i.putExtra("realizado", finalRealizadoMod);
                i.putExtra("pos", finalPos);


                setResult(RESULT_OK, i);
                finish();
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibfecha:
                obtenerFecha();
                break;

            case R.id.ibhora:
                obtenerHora();
                break;

        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                etHora.setText(horaFormateada + PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }
}
