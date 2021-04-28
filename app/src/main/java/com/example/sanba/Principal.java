package com.example.sanba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {
    Button button_buque;
    Button button_calculos;
    Button button_tierra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

         /* //Ir a la pagina de Updates
        button_tierra = findViewById(R.id.button_tierra);

        button_tierra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Tierra.class);
                startActivity(intent);
            }
        });*/

        //Ir a la pagina de Buque
        button_buque = findViewById(R.id.button_buque);

        button_buque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Buque.class);
                startActivity(intent);
            }
        });

        //Ir a la pagina de calculos
        button_calculos = findViewById(R.id.button_calculos);

        button_calculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Calculos.class);
                startActivity(intent);
            }
        });

    }
}
