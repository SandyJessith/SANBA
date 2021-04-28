package com.example.sanba;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanba.utilidades.Utilidades;

import java.util.Calendar;


public class Update extends AppCompatActivity implements View.OnClickListener {
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final int        DIALOG_DATE_PICKER  = 100;
    private int                     datePickerInput;
    private static final int        DIALOG_TIMW_PICKER  = 100;
    private int                     timePickerInput;

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets

    Button ibObtenerFecha,ibObtenerHora, btnEnviar, btnEnviarDesplazamiento,btnEnviarRata,
    ibObtenerFechaDesplazamiento, ibObtenerFechaInicioCargue, ibObtenerFechaPareCargue,
    ibObtenerFechaReanudeCargue, ibObtenerHoraInicioCargue, ibObtenerHoraPareCargue,
    ibObtenerHoraReanudeCargue,ibObtenerFechaETC, ibObtenerHoraETC, ibObtenerFechaUpdateRata,
    ibObtenerHoraUpdateRata,  btnGuardarBuque;
    EditText etFecha,etHora,etFechaTiempos, etHoraTiempos,etFechaRata,etHoraRata,etFechaETCRata,
            etHoraETCRata,etFechaDesplazamiento,etFechaInicioCargue, etHoraInicioCargue,
            etFechaPareCargue, etHoraPareCargue, etFechaReanudeCargue,etHoraReanudeCargue,
            etFechaUpdateRata, etHoraUpdateRata,
            nombreBuque,nombreProducto, TL, evento,operacion,fechaDesplazamiento, horaInicioDesplazamiento,
            fechaInicioDesplazamiento, fechaPareCargue,horaPareCargue, fechaReanudaCargue,
            horaReanudaCargue, OBQ, VEF, rataEstimada, fechaETC, horaETC,cifraBuque
            ,cifraTierra, Cargado, Rata, porCargar, fechaRata, horaRata;
    TextView diferencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //Widget EditText donde se mostrara la fecha obtenida


        //FECHAS
        etFechaTiempos = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        etFechaDesplazamiento = (EditText) findViewById(R.id.et_mostrar_fecha_Desplazamiento);
        etFechaInicioCargue = (EditText) findViewById(R.id.et_mostrar_fecha_inicio_cargue);
        etFechaPareCargue = (EditText) findViewById(R.id.et_mostrar_fecha_pare_cargue);
        etFechaReanudeCargue = (EditText) findViewById(R.id.et_mostrar_fecha_reanuda_cargue);
        etFechaETCRata = (EditText) findViewById(R.id.et_mostrar_fecha_ETC);
        etFechaUpdateRata = (EditText) findViewById(R.id.FechaUpdateRata);

        //HORAS
        etHoraTiempos = (EditText) findViewById(R.id.et_mostrar_hora_picker);
        etHoraInicioCargue = (EditText) findViewById(R.id.et_mostrar_hora_inicio_cargue);
        etHoraPareCargue = (EditText) findViewById(R.id.et_mostrar_hora_pare_cargue);
        etHoraReanudeCargue = (EditText) findViewById(R.id.et_mostrar_hora_reanuda_cargue);
        etHoraETCRata = (EditText) findViewById(R.id.et_mostrar_hora_ETC);
        etHoraUpdateRata = (EditText) findViewById(R.id.HoraUpdateRata);





        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (Button) findViewById(R.id.ib_obtener_fecha);
        //Evento setOnClickListener - clic
        ibObtenerFecha.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha de dezplazamiento
        ibObtenerFechaDesplazamiento = (Button) findViewById(R.id.ib_obtener_fecha_Desplazamiento);
        //Evento setOnClickListener - clic
        ibObtenerFechaDesplazamiento.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha inicio cargue
        ibObtenerFechaInicioCargue = (Button) findViewById(R.id.ib_obtener_fecha_incio_cargue);
        //Evento setOnClickListener - clic
        ibObtenerFechaInicioCargue.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha pare cargue
        ibObtenerFechaPareCargue = (Button) findViewById(R.id.ib_obtener_fecha_pare_cargue);
        //Evento setOnClickListener - clic
        ibObtenerFechaPareCargue.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha reinicie cargue
        ibObtenerFechaReanudeCargue = (Button) findViewById(R.id.ib_obtener_fecha_reanuda_cargue);
        //Evento setOnClickListener - clic
        ibObtenerFechaReanudeCargue.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha ETC
        ibObtenerFechaETC = (Button) findViewById(R.id.obtener_fechaETC);
        //Evento setOnClickListener - clic
        ibObtenerFechaETC.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha Update Rata
        ibObtenerFechaUpdateRata = (Button) findViewById(R.id.obtenerFechaUpdateRata);
        //Evento setOnClickListener - clic
        ibObtenerFechaUpdateRata.setOnClickListener(this);




        //HORAS
        //Widget EditText donde se mostrara la hora obtenida
        etHoraTiempos = (EditText) findViewById(R.id.et_mostrar_hora_picker);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        ibObtenerHora = (Button) findViewById(R.id.ib_obtener_hora);
        //Evento setOnClickListener - clic
        ibObtenerHora.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la hora inicio cargue
        ibObtenerHoraInicioCargue = (Button) findViewById(R.id.ib_obtener_hora_inicio_cargue);
        //Evento setOnClickListener - clic
        ibObtenerHoraInicioCargue.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la hora pare cargue
        ibObtenerHoraPareCargue = (Button) findViewById(R.id.ib_obtener_hora_pare_cargue);
        //Evento setOnClickListener - clic
        ibObtenerHoraPareCargue.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la hora reunude cargue
        ibObtenerHoraReanudeCargue = (Button) findViewById(R.id.ib_obtener_hora_reanuda_cargue);
        //Evento setOnClickListener - clic
        ibObtenerHoraReanudeCargue.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la hora reunude cargue
        ibObtenerHoraETC = (Button) findViewById(R.id.btnObtener_horaETC);
        //Evento setOnClickListener - clic
        ibObtenerHoraETC.setOnClickListener(this);

        //Widget ImageButton del cual usaremos el evento clic para obtener la hora reunude cargue
        ibObtenerHoraUpdateRata = (Button) findViewById(R.id.obtenerHoraUpdateRata);
        //Evento setOnClickListener - clic
        ibObtenerHoraUpdateRata.setOnClickListener(this);




        //Widget que obtiene el Nombre del buque
        nombreBuque = (EditText) findViewById(R.id.nombreBuque);
        //Widget que obtiene el Nombre del producto
        nombreProducto = (EditText) findViewById(R.id.nombreProducto);
        //Widget que obtiene la TL
        TL = (EditText) findViewById(R.id.TL);
        //Widget que obtiene el Evento
        evento = (EditText) findViewById(R.id.evento);
        //Widget que obtiene la Operación
        operacion = (EditText) findViewById(R.id.operacion);
        //Widget que obtiene la Fecha de desplazamiento
        fechaDesplazamiento = (EditText) findViewById(R.id.et_mostrar_fecha_Desplazamiento);
        //Widget que obtiene la Fecha de inicio de cargue
        fechaInicioDesplazamiento = (EditText) findViewById(R.id.et_mostrar_fecha_inicio_cargue);
        //Widget que obtiene la Hora de inicio de cargue
        horaInicioDesplazamiento = (EditText) findViewById(R.id.et_mostrar_hora_inicio_cargue);
        //Widget que obtiene la Fecha de pare de cargue
        fechaPareCargue = (EditText) findViewById(R.id.et_mostrar_fecha_pare_cargue);
        //Widget que obtiene la Hora de pare de cargue
        horaPareCargue = (EditText) findViewById(R.id.et_mostrar_hora_pare_cargue);
        //Widget que obtiene la Fecha de reanude de cargue
        fechaReanudaCargue = (EditText) findViewById(R.id.et_mostrar_fecha_reanuda_cargue);
        //Widget que obtiene la Hora de reanude de cargue
        horaReanudaCargue = (EditText) findViewById(R.id.et_mostrar_hora_reanuda_cargue);
        //Widget que obtiene la Fecha de ETC
        fechaETC = (EditText) findViewById(R.id.et_mostrar_fecha_ETC);
        //Widget que obtiene la Hora de ETC
        horaETC = (EditText) findViewById(R.id.et_mostrar_hora_ETC);
        //Widget que obtiene el OBQ
        cifraBuque = (EditText) findViewById(R.id.cifraBuqueDesplazamiento);
        //Widget que obtiene el VEF<
        cifraTierra = (EditText) findViewById(R.id.cifraTierraDesplazamiento);
        //Widget que obtiene el Rata Estimada
        diferencia = (TextView) findViewById(R.id.diferenciaDesplazamiento);

        //Widget que obtiene el OBQ
        OBQ = (EditText) findViewById(R.id.OBQ);
        //Widget que obtiene el VEF
        VEF = (EditText) findViewById(R.id.VEF);
        //Widget que obtiene el Rata Estimada
        rataEstimada = (EditText) findViewById(R.id.rataEstimada);
        //Widget que obtiene el volumen cargado
        Cargado = (EditText) findViewById(R.id.cargado);
        //Widget que obtiene la rata
        Rata = (EditText) findViewById(R.id.rata);
        //Widget que obtiene el Rata Estimada
        porCargar = (EditText) findViewById(R.id.porCargar);
        //Widget que obtiene la Fecha de la Rata
        fechaRata = (EditText) findViewById(R.id.FechaUpdateRata);
        //Widget que obtiene la Hora de la Rata
        horaRata = (EditText) findViewById(R.id.HoraUpdateRata);







        //Acción guardar información de buque
        btnGuardarBuque = (Button)findViewById(R.id.btnGuardarBuque);

        btnGuardarBuque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             // registrarBuque();
                //Registrar Buques Con SQL
                registrarBuqueSql();
            }
        });

        //Acción botón enviar Update

        btnEnviar = (Button)findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hora = etHoraTiempos.getText().toString();
                String fecha = etFechaTiempos.getText().toString();
                String nombreBuquestr = nombreBuque.getText().toString();
                String nombreProductostr = nombreProducto.getText().toString();
                String TLstr = TL.getText().toString();
                String eventostr = evento.getText().toString();
                String operacionstr = operacion.getText().toString();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "*"+ nombreBuquestr+"/"+operacionstr+
                        "/"+nombreProductostr+"/"+TLstr+"*" +"\n"+"\n" +"_"+fecha+"_"+ "\n"+ "\n"+
                        eventostr+":  "+hora+"hrs");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });

        //Acción botón enviar Desplazamiento

        btnEnviarDesplazamiento = (Button)findViewById(R.id.btnEnviarDesplazamiento);



        btnEnviarDesplazamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreBuquestr = nombreBuque.getText().toString();
                String nombreProductostr = nombreProducto.getText().toString();
                String TLstr = TL.getText().toString();
                String operacionstr = operacion.getText().toString();
                String fechaDesplazamientostr = fechaDesplazamiento.getText().toString();
                String fechaInicioDesplazamientostr = etFechaInicioCargue.getText().toString();
                String horaInicioDesplazamientostr =  etHoraInicioCargue.getText().toString();
                String fechaPareCarguestr = fechaPareCargue.getText().toString();
                String horaPareCarguestr = horaPareCargue.getText().toString();
                String horaReanudaCarguestr = etHoraReanudeCargue.getText().toString();
                String fechaReanudaCarguestr = etFechaReanudeCargue.getText().toString();
                String OBQstr = OBQ.getText().toString();
                String VEFstr = VEF.getText().toString();
                String rataEstimadastr = rataEstimada.getText().toString();
                String cifraBuquestr = cifraBuque.getText().toString();
                String cifraTierrastr = cifraTierra.getText().toString();
                String fechaETCstr = etFechaETCRata.getText().toString();
                String horaETCstr = etHoraETCRata.getText().toString();
                float cifraBuquef = Float.parseFloat(cifraBuquestr);
                float cifraTierraf = Float.parseFloat(cifraTierrastr);

                float diferenciaf =metodosFunciones.redondear( cifraBuquef - cifraTierraf,2);



                String diferenciastr = String.valueOf(diferenciaf);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "*"+ nombreBuquestr+"/"+operacionstr+
                        "/"+nombreProductostr+"/"+TLstr+"*" +"\n"+"\n" +"_"+fechaDesplazamientostr+"_"+
                        "\n"+ "\n"+"Inicio cargue: "+fechaInicioDesplazamientostr+" "+
                        horaInicioDesplazamientostr+" hrs"+"\n"+"\n"+"Para cargue: "+fechaPareCarguestr+ " "+
                        horaPareCarguestr+ " hrs"+"\n"+"\n"+ "Reanuda Cargue: "+fechaReanudaCarguestr+" "
                +horaReanudaCarguestr+ " hrs"+"\n"+"\n"+ "COMPARACIÖN: "+"\n"+"\n"+"Cifra buque: "+
                cifraBuquestr+ " Bbls"+ "\n"+"Cifra tierra: "+cifraTierrastr+ " Bbls" +"\n"+"Diferencia: "
                        +diferenciastr+" Bbls"+ "\n"+"\n"+"OBQ: "+OBQstr+ " Bbls"+"\n"+"\n"+ "VEF: "
                        +VEFstr+"\n"+"\n"+"Rata estimada: "+ rataEstimadastr+"\n"+"\n"+"ETC: "
                        +fechaETCstr+ " "+horaETCstr+ " hrs");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });

        btnEnviarRata = (Button)findViewById(R.id.btnEnviarRata);

        btnEnviarRata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreBuquestr = nombreBuque.getText().toString();
                String nombreProductostr = nombreProducto.getText().toString();
                String TLstr = TL.getText().toString();
                String operacionstr = operacion.getText().toString();
                String fechaETCstr = fechaETC.getText().toString();
                String horaETCstr = horaETC.getText().toString();
                String cargadostr = Cargado.getText().toString();
                String ratastr = Rata.getText().toString();
                String porCargarstr = porCargar.getText().toString();
                String fechaRatastr = fechaRata.getText().toString();
                String horaRatastr = horaRata.getText().toString();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "*"+ nombreBuquestr+"/"+operacionstr+
                        "/"+nombreProductostr+"/"+TLstr+"*" +"\n"+"\n" +"_"+fechaRatastr+"_"+" "+ horaRatastr+" hrs"
                        +"\n"+"\n"+"Cargado: "+cargadostr+" Bbls GOV"+"\n"+"Rata:" +ratastr+" bph" +"\n"+
                        "Por cargar: "+porCargarstr+" Bbls GOV"+"\n"+"\n"+"ETC: "+
                        fechaETCstr+ " "+horaETCstr+ " hrs" );
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });


    }

    private void registrarBuqueSql() {
        //Definir Base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd buques",
                null,1);

        //Abrir base de datos para escritura
        SQLiteDatabase db = conn.getWritableDatabase();

        String insert = "INSERT INTO "+Utilidades.TABLA_BUQUE+" ( "+Utilidades.CAMPO_TL+
                ","+ Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_OPERACION+","+
                Utilidades.CAMPO_PRODUCTO+")"+"VALUES ("+ TL.getText().toString()+",'"+
                nombreBuque.getText().toString()+"','"+operacion.getText().toString()+"','"
                +nombreProducto.getText().toString()+"')";

        db.execSQL(insert);

        Toast.makeText(getApplicationContext(),"Buque Guardado",Toast.LENGTH_SHORT).show();


        //Cerrar base de datos
        db.close();

    }

    // Metodo para guardar los datos del buque en la base de datos
    private void registrarBuque() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd buques",
                null,1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_TL,TL.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, nombreBuque.getText().toString() );
        values.put(Utilidades.CAMPO_PRODUCTO, nombreProducto.getText().toString());
        values.put(Utilidades.CAMPO_OPERACION, operacion.getText().toString());

        long idResultante=db.insert(Utilidades.TABLA_BUQUE, Utilidades.CAMPO_TL, values);

        Toast.makeText(getApplicationContext(), "ID buque: "+idResultante, Toast.LENGTH_SHORT).show();


    }


    @Override
        public void onClick (View v){

   /*     if(v.getId() == R.id.ib_obtener_fecha){
            etFecha = etFechaTiempos;
            obtenerFecha();
        }else if(v.getId() == R.id.ib_obtener_fecha_Desplazamiento){
            etFecha = etFechaDesplazamiento;
            obtenerFecha();
        }else if(v.getId() == R.id.ib_obtener_fecha_incio_cargue){
            etFecha = etFechaInicioCargue;
            obtenerFecha();
        }else if (v.getId() == R.id.ib_obtener_fecha_pare_cargue){
            etFecha = etFechaPareCargue;
            obtenerFecha();
        }else if(v.getId() == R.id.ib_obtener_fecha_reanuda_cargue){
            etFecha = etFechaReanudeCargue;
            obtenerFecha();
        }else if (v.getId() == R.id.obtener_fechaETC){
            etFecha = etFechaETCRata;
            obtenerFecha();
        }else if (v.getId() == R.id.ib_obtener_hora){
            etHora = etHoraTiempos;
            obtenerHora();
        }else if (v.getId() == R.id.ib_obtener_hora_inicio_cargue){
            etHora = etHoraInicioCargue;
            obtenerHora();
        }else if (v.getId() == R.id.ib_obtener_hora_pare_cargue){
            etHora = etHoraPareCargue;
            obtenerHora();
        }else if(v.getId() == R.id.ib_obtener_fecha_reanuda_cargue){
            etHora = etHoraReanudeCargue;
            obtenerHora();
        }else if(v.getId() == R.id.btnObtener_horaETC){
            etHora = etHoraETCRata;
            obtenerHora();
        }*/

               datePickerInput = v.getId();

        switch (datePickerInput) {
            case R.id.ib_obtener_fecha:
            case R.id.ib_obtener_fecha_Desplazamiento:
            case R.id.ib_obtener_fecha_incio_cargue:
            case R.id.ib_obtener_fecha_pare_cargue:
            case R.id.ib_obtener_fecha_reanuda_cargue:
            case R.id.obtener_fechaETC:
            case R.id.obtenerFechaUpdateRata:

                obtenerFecha();
                break;


            case R.id.ib_obtener_hora:
                etHora = etHoraTiempos;
                obtenerHora();
                break;
            case R.id.ib_obtener_hora_inicio_cargue:
                etHora = etHoraInicioCargue;
                obtenerHora();
                break;
            case R.id.ib_obtener_hora_pare_cargue:
                etHora = etHoraPareCargue;
                obtenerHora();
                break;
            case R.id.ib_obtener_hora_reanuda_cargue:
                etHora = etHoraReanudeCargue;
                obtenerHora();
                break;
            case R.id.btnObtener_horaETC:
                etHora = etHoraETCRata;
                obtenerHora();
                break;
            case R.id.obtenerHoraUpdateRata:
                etHora = etHoraUpdateRata;
                obtenerHora();
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


                try {
                    switch (datePickerInput) {
                        case R.id.ib_obtener_fecha:
                            etFechaTiempos.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                            break;
                        case R.id.ib_obtener_fecha_Desplazamiento:
                            etFechaDesplazamiento.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                            break;
                        case R.id.ib_obtener_fecha_incio_cargue:
                          etFechaInicioCargue.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                          break;
                        case R.id.ib_obtener_fecha_pare_cargue:
                            etFechaPareCargue.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                            break;
                        case R.id.ib_obtener_fecha_reanuda_cargue:
                            etFechaReanudeCargue.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                            break;
                        case R.id.obtener_fechaETC:
                            etFechaETCRata.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                            break;
                        case R.id.obtenerFechaUpdateRata:
                            etFechaUpdateRata.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                            break;

                            default:
                                break;
                    }



                }catch (Exception e){

                }
            }

            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /*
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
                try {
                    etHora.setText(horaFormateada + ":" + minutoFormateado + " ");
                }catch (Exception e){}

            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, true);

        recogerHora.show();
    }



}



