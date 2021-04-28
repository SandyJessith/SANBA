package com.example.sanba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class consultarBuque extends AppCompatActivity {
/*
    EditText campoTL;
    TextView campoNombreBuque, campoNombreProducto, campoOperacion;
    Button btnConsultarBuque;
    ConexionSQLiteHelper conn;*/

    public static final int ADD_BUQUE_REQUEST = 1;
    public static final int EDIT_BUQUE_REQUEST = 2;

    private BuqueViewModel buqueViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_buque);


        //Esto es para la funcionalidad del menu flotante
        FloatingActionButton buttonAgregarBuque = findViewById(R.id.button_add_vessel);

        buttonAgregarBuque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consultarBuque.this, ActividadEditarBuque.
                        class);
                startActivityForResult(intent, ADD_BUQUE_REQUEST);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BuqueAdapter adapter = new BuqueAdapter();
        recyclerView.setAdapter(adapter);

        buqueViewModel = ViewModelProviders.of(this).get(BuqueViewModel.class);
        //Estos es para que se actualice automaticamente
        buqueViewModel.getAllBuques().observe(this, new Observer<List<buqueClase>>() {

            //Debo investigar más a fondo la funcionalidad de este metodo
            @Override
            public void onChanged(@Nullable List<buqueClase> buques) {
                adapter.submitList(buques);
            }
        });

        //Esto es para activar la opcion de deslizar los elementos del Recyclerview a los lados
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            /*@Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.
                    ViewHolder viewHolder) {
                return 0;
            }*/

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder
                    viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            //Aca le decimos que hacer cuando el elemento se deslice a un lado
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                buqueViewModel.delete(adapter.getBuqueAt(viewHolder.getAdapterPosition()));
                Toast.makeText(consultarBuque.this, "Buque borrado",
                        Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        //Aca le decimos que hacer cuando se le cliquee (Editar)
        adapter.setOnItemClickListener(new BuqueAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(buqueClase buque) {
                Intent intent = new Intent(consultarBuque.this, ActividadEditarBuque
                        .class);

                intent.putExtra(ActividadEditarBuque.EXTRA_ID, buque.getId());
                intent.putExtra(ActividadEditarBuque.EXTRA_NOMBRE_BUQUE, buque.getNombreBuque());
                intent.putExtra(ActividadEditarBuque.EXTRA_SPINNER_OPERACION, buque.getOperacion());
                intent.putExtra(ActividadEditarBuque.EXTRA_NOMBRE_PRODUCTO, buque.getNombreBuque());
                intent.putExtra(ActividadEditarBuque.EXTRA_NOMINACION, buque.getNominacion());
                intent.putExtra(ActividadEditarBuque.EXTRA_TL, buque.getTL());
                intent.putExtra(ActividadEditarBuque.EXTRA_NUMBER_PICKER, buque.getPriority());

                startActivityForResult(intent, EDIT_BUQUE_REQUEST);

            }
        });

      /*  adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(consultarBuque.this, "Has cliqueado un buque",
                        Toast.LENGTH_SHORT).show();
            }
        });*/

      /*  conn = new ConexionSQLiteHelper(getApplicationContext(), "bd buques", null, 1);


        campoTL = (EditText) findViewById(R.id.et_consultar_TL);
        campoNombreBuque = (TextView) findViewById(R.id.tv_nombreBuque);
        campoNombreProducto = (TextView) findViewById(R.id.tv_nombreProducto);
        campoOperacion = (TextView) findViewById(R.id.tv_operacion);

        btnConsultarBuque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_obtener_informacion_buque:
                        consultar();
                        limpiar();
                        break;

                        default:
                            break;
                }
            }
        });

    }
    private void limpiar() {
        campoNombreBuque.setText("");
        campoOperacion.setText("");
        campoNombreProducto.setText("");
    }

    private void consultar() {

        //Conexión a base de datos para lectura
        SQLiteDatabase db = conn.getReadableDatabase();

        //Parametros a recibir
        String [] parametros = {campoTL.getText().toString()};

        //Parametros a regresar
        String [] campos ={Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_OPERACION,
        Utilidades.CAMPO_PRODUCTO};

        Cursor cursor = db.query(Utilidades.TABLA_BUQUE,campos,
                Utilidades.CAMPO_TL+"=?",parametros, null,null,
                null);

        if(cursor.moveToFirst()) {

            do {
                try {

                    campoNombreBuque.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_NOMBRE)));
                    campoOperacion.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_OPERACION)));
                    campoNombreProducto.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_PRODUCTO)));

                    Toast.makeText(getApplicationContext(), "Los datos se leyeron correctamente"
                            , Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No existe un buque con esa TL en " +
                            "la base de datos", Toast.LENGTH_LONG).show();
                }
            }while (cursor.moveToNext());
        }

            */
    }

    //Aqui inserta o actualiza el buque en la base de datos
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BUQUE_REQUEST && resultCode == RESULT_OK) {

            String nombreBuque = data.getStringExtra(ActividadEditarBuque.EXTRA_NOMBRE_BUQUE);
            String nombreProoducto = data.getStringExtra(ActividadEditarBuque.EXTRA_NOMBRE_PRODUCTO);
            String TL = data.getStringExtra(ActividadEditarBuque.EXTRA_TL);
            Float nominacion = Float.parseFloat(data.getStringExtra(ActividadEditarBuque.EXTRA_NOMINACION));
            String operacion = data.getStringExtra(ActividadEditarBuque.EXTRA_SPINNER_OPERACION);
            int priority = data.getIntExtra(ActividadEditarBuque.EXTRA_NUMBER_PICKER, 1);

            buqueClase buque = new buqueClase(priority, nominacion, nombreBuque, nombreProoducto, operacion, TL);

            buqueViewModel.insert(buque);

            Toast.makeText(this, "" + nombreBuque + " ha sido creado",
                    Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_BUQUE_REQUEST &&
                resultCode == RESULT_OK) {
            int id = data.getIntExtra(ActividadEditarBuque.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Este buque no puede ser actualizado",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String nombreBuque = data.getStringExtra(ActividadEditarBuque.EXTRA_NOMBRE_BUQUE);
            String nombreProoducto = data.getStringExtra(ActividadEditarBuque.EXTRA_NOMBRE_PRODUCTO);
            String TL = data.getStringExtra(ActividadEditarBuque.EXTRA_TL);
            Float nominacion = Float.parseFloat(data.getStringExtra(ActividadEditarBuque.EXTRA_NOMINACION));
            String operacion = data.getStringExtra(ActividadEditarBuque.EXTRA_SPINNER_OPERACION);
            int priority = data.getIntExtra(ActividadEditarBuque.EXTRA_NUMBER_PICKER, 1);

            buqueClase buque = new buqueClase(priority, nominacion, nombreBuque, nombreProoducto,
                    operacion, TL);

            buque.setId(id);

            buqueViewModel.update(buque);

            Toast.makeText(this, "" + nombreBuque + " ha sido actualizado",
                    Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Buque no creado", Toast.LENGTH_SHORT).show();
        }
    }

    //Esto hace que se despliegue el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.consultarbuque_menu, menu);
        return true;
    }

    //Esto es para dar funcionalidad a lo que se despliega en los tres puntos
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.borar_todos_losbuque:
                buqueViewModel.deleteAllBuques();
                Toast.makeText(this, "Todos los buques han sido borrados",
                        Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
