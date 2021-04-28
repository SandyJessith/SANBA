package com.example.sanba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

//Esta clase se creó utlizando CTRL + F6
public class ActividadEditarBuque extends AppCompatActivity {

    //Esto se utiliza para el metodo guardarBuque() como llave para guardar un valor
    public static final String EXTRA_ID = "com.example.sanba.EXTRA_ID";
    public static final String EXTRA_NOMBRE_BUQUE = "com.example.sanba.EXTRA_NOMBRE_BUQUE";
    public static final String EXTRA_NOMBRE_PRODUCTO = "com.example.sanba.EXTRA_NOMBRE_PRODUCTO";
    public static final String EXTRA_TL = "com.example.sanba.EXTRA_TL";
    public static final String EXTRA_NOMINACION = "com.example.sanba.EXTRA_NOMINACION";
    public static final String EXTRA_SPINNER_OPERACION = "com.example.sanba.EXTRA_SPINNER_OPERACION";
    public static final String EXTRA_NUMBER_PICKER = "com.example.sanba.EXTRA_NUMBER_PICKER";


    private EditText editTextNombreBuque, editTextNombreProducto, editTextTL, editTextNominacion;
    private Spinner spinnerOperacion;
    private NumberPicker numberPickerPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_agregar_buque);

        editTextNombreBuque = findViewById(R.id.edit_text_nombreBuque);
        editTextNombreProducto = findViewById(R.id.edit_text_nombreProducto);
        editTextTL = findViewById(R.id.edit_text_TL);
        editTextNominacion = findViewById(R.id.edit_text_nominacion);
        spinnerOperacion = findViewById(R.id.operacion_spinner);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        //Valor inferior y superior del numberPicker
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        //Esto hacer que al dar click se regrese a la pagina anterior?
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        //Esto es para cuando se abra para editar llene los datos que tenía anteriormente
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editar buque");
            editTextNombreBuque.setText(intent.getStringExtra(EXTRA_NOMBRE_BUQUE));
            spinnerOperacion.setSelection(intent.getIntExtra(spinnerOperacion.toString(),1));
            editTextNombreProducto.setText(intent.getStringExtra(EXTRA_NOMBRE_PRODUCTO));
            editTextNominacion.setText(intent.getStringExtra(EXTRA_NOMINACION));
            editTextTL.setText(intent.getStringExtra(EXTRA_TL));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_NUMBER_PICKER, 1));
        } else {
            setTitle("Agregar buque");
        }


        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operacion_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperacion.setAdapter(adapter);

    }

    private void guardarBuque() {
        String nombreBuque = editTextNombreBuque.getText().toString();
        String nombreProducto = editTextNombreProducto.getText().toString();
        String TL = editTextTL.getText().toString();
        String nominacion = editTextNominacion.getText().toString();
        String operacion = spinnerOperacion.getSelectedItem().toString();
        int priority = numberPickerPriority.getValue();


        if (nombreBuque.trim().isEmpty() || nombreProducto.trim().isEmpty() || TL.trim().isEmpty() ||
                nominacion.trim().isEmpty() || operacion.trim().isEmpty()) {
            Toast.makeText(this, "No se aceptan espaciosvacios",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Aqui se toman los datos con llave y valor
        Intent data = new Intent();
        data.putExtra(EXTRA_NOMBRE_BUQUE, nombreBuque);
        data.putExtra(EXTRA_NOMBRE_PRODUCTO, nombreProducto);
        data.putExtra(EXTRA_TL, TL);
        data.putExtra(EXTRA_NOMINACION, nominacion);
        data.putExtra(EXTRA_SPINNER_OPERACION, operacion);
        data.putExtra(EXTRA_NUMBER_PICKER, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_agregar_buque, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guardar_buque:
                guardarBuque();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


}
