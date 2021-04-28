package com.example.sanba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private BuqueViewModel buqueViewModel;

    Button btnIngresar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd buques",
                null,1);
*/
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = ((EditText) findViewById(R.id.EtUsuario)).getText().toString();
                String contraseña = ((EditText) findViewById(R.id.EtContraseña)).getText().toString();

                if (usuario.equals("Sandy.chico")&& contraseña.equals("Proyecto2019"))
                {
                    startActivity(new Intent(MainActivity.this, Principal.class ));
                }else {
                    Toast.makeText(getApplicationContext(),"Credenciales incorrectas",Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}
