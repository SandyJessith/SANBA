package com.example.sanba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buque extends AppCompatActivity {

    Button btnVolver, btnUpdate, terminosDefiniciones, informacionGeneral, resumenEstandar,
            resumenOperacion, comunicacionOperacion, btnConsultarBuque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buque);

        //Acción botón volver

        btnVolver = (Button)findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Acción botón update

        btnUpdate = (Button)findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, Update.class);
                startActivity(intent);
            }
        });

        //Acción botón Consultar

        btnConsultarBuque = (Button)findViewById(R.id.btnConsultarBuque);

        btnConsultarBuque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, consultarBuque.class);
                startActivity(intent);
            }
        });







        //Acción ir a terminos y deficniciones
        terminosDefiniciones = (Button)findViewById(R.id.terminosDefiniciones);

        terminosDefiniciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, Capitulo17_1_3.class);
                startActivity(intent);
            }
        });

        //Acción ir a información general
        informacionGeneral = (Button)findViewById(R.id.informacionGeneral);

        informacionGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, Capitulo17_1_5.class);
                startActivity(intent);
            }
        });

        //Acción ir a Resumen estandar
         resumenEstandar= (Button)findViewById(R.id.resumenEstandar);

        resumenEstandar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, Capitulo17_1_6.class);
                startActivity(intent);
            }
        });

        //Acción ir a Resumen de la operación
        resumenOperacion= (Button)findViewById(R.id.resumenOperacion);

        resumenOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, Capitulo17_1_7.class);
                startActivity(intent);
            }
        });

        //Acción ir a Comunicación y operación buque/tierra
        comunicacionOperacion= (Button)findViewById(R.id.comunicacionOperacion);

        comunicacionOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buque.this, Capitulo17_1_8.class);
                startActivity(intent);
            }
        });


    }
}
