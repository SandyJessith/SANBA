package com.example.sanba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Capitulo17_1_3 extends AppCompatActivity {

    Button btnVolverBuque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulo17_1_3);

        btnVolverBuque = (Button)findViewById(R.id.btnVolverBuque);

        btnVolverBuque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Capitulo17_1_3.this, Buque.class);
                startActivity(intent);
            }
        });

    }




}
