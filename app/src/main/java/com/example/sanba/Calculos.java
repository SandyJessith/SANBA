package com.example.sanba;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Calculos extends AppCompatActivity {

    private Button button_calcularTecho,button_calcularGOV,button_calcularCTSH, button_calcular_VCF
            ,getButton_calcularGSV;
    private EditText APItab, APIobs, BblsCor, FW, TOV,tempLiq,tempAmb,TshRef, APIstd,VCF;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculos);


        //Instancia de la clase de los metodos y funciones
        //  metodosFunciones misMetodosFunciones = new metodosFunciones();


        //Obtención de datos del EditText FRA
        APItab = (EditText) findViewById(R.id.APItabla);
         APIobs = (EditText)findViewById(R.id.APIobs);
         BblsCor = (EditText) findViewById(R.id.BblsCor);
        Button button_calcularTecho = (Button) findViewById(R.id.button_calcular_techo);

        //Obtención de datos del EditText CTSH
        tempLiq = (EditText) findViewById(R.id.tempLiq);
        tempAmb = (EditText)findViewById(R.id.tempAmb);
        TshRef = (EditText) findViewById(R.id.TshRef);
        Button button_calcularCTSH = (Button) findViewById(R.id.button_calcular_CTSH);

        //Obtención de datos del EditText VCF
        APIstd = (EditText) findViewById(R.id.APIstd);
        Button button_calcular_VCF = (Button) findViewById(R.id.button_calcular_VCF);

        //Obtencion de datos del EditText GOV
        FW = (EditText) findViewById(R.id.FW);
        TOV = (EditText) findViewById(R.id.TOV);
        Button button_calcularGOV = (Button) findViewById(R.id.button_calcular_GOV);


        calcularFRA();
        calcularGOV();
        calcularCTSH();
        calcularVCF();
        calcularGSV();

    }

    private void calcularGSV() {

        Button button_calcularGSV = (Button) findViewById(R.id.button_calcular_GSV);

        EditText VCFp = (EditText) findViewById(R.id.VCF);
        EditText GOVp = (EditText) findViewById(R.id.GOV);
        final String VCFstr = VCFp.getText().toString();
        final String GOVstr = GOVp.getText().toString();




        //Calcular GSV

        button_calcularGSV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

               float GSV = metodosFunciones.calcularGSV(VCFstr,GOVstr);

                EditText tvRespuesta = (EditText) findViewById(R.id.GSV);

                String respuesta = String.valueOf(GSV);

                tvRespuesta.setText(respuesta + " Bbls");

            }
        });

    }

    private void calcularVCF() {

        mSpinner = (Spinner) findViewById(R.id.productoSpinner);

        //Elementos selecionables (productos)

        ArrayList<String> productos = new ArrayList<>();


        productos.add("No especificado");
        productos.add("Crude Oil");
        productos.add("Fuel Oils");
        productos.add("Jet Fuels");
        productos.add("Transition Zone");
        productos.add("Gasolines");
        productos.add("Lubricating Oils");

        ArrayAdapter adp = new ArrayAdapter(Calculos.this, android.R.layout.simple_spinner_dropdown_item, productos);
        mSpinner.setAdapter(adp);

        //Metodo para saber cuando se selecciona un producto
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Arroja el producto que se seleciona
                final String producto = (String) mSpinner.getAdapter().getItem(position);
                //Manda un mensaje a la pantalla
                Toast.makeText(Calculos.this, "Selecionaste: "+producto, Toast.LENGTH_SHORT).show();

                Button button_calcular_VCF = (Button) findViewById(R.id.button_calcular_VCF);

                //Calcular VCF


                button_calcular_VCF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            String APIstdstr = APIstd.getText().toString();
                            String tempLiqstr = tempLiq.getText().toString();

                            float tempLiq = Float.parseFloat(tempLiqstr);
                            float APIstd = Float.parseFloat(APIstdstr);

                            //Densidad en Kg/m3
                            float SGp = (float) ((141.5 / (APIstd + 131.5))*999.016);
                            float SG = metodosFunciones.redondear(SGp,1);


                            //Verificando condición de temperatura & densidad

                            if (tempLiq >= -58 && tempLiq <= 302) {


                                switch (producto){
                                    case "No especificado":
                                        break;


                                    case "Crude Oil":
                                    case "Fuel Oils":
                                    case "Jet Fuels":
                                    case "Transition Zone":
                                    case "Gasolines":
                                        if (SG>=610.6 && SG<=1163.5) {
                                            float VCF = metodosFunciones.factorCorrecionVolumen(SG, tempLiqstr, producto);

                                            TextView tvRespuesta = (TextView) findViewById(R.id.VCF);


                                            String respuesta = String.valueOf(VCF);

                                            tvRespuesta.setText(respuesta);
                                        }else {
                                            Toast.makeText(Calculos.this,"La densidad" +
                                                    " está por fuera del rango permitido",Toast.LENGTH_LONG).show();
                                        }
                                        break;



                                    case "Lubricating Oils":
                                        if (SG>=800.9 && SG<=1163.5) {
                                            float VCF = metodosFunciones.factorCorrecionVolumen(SG, tempLiqstr,producto);

                                            TextView tvRespuesta = (TextView) findViewById(R.id.VCF);


                                            String respuesta = String.valueOf(VCF);

                                            tvRespuesta.setText(respuesta);
                                        }else {
                                            Toast.makeText(Calculos.this,"La densidad" +
                                                    " está por fuera del rango permitido",Toast.LENGTH_LONG).show();
                                        }
                                        break;

                                        default:
                                            Toast.makeText(Calculos.this,"La densidad" +
                                                    " está por fuera del rango permitido",Toast.LENGTH_LONG).show();
                                }


                                                            }else {
                                Toast.makeText(Calculos.this,"La temperatura" +
                                        " está por fuera del rango permitido",Toast.LENGTH_LONG).show();
                            }

                        }catch (Exception e){
                            return;
                        }




                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



    }

    private void calcularCTSH() {
        Button button_calcularCTSH = (Button) findViewById(R.id.button_calcular_CTSH);

        //Calcular CTSH


        button_calcularCTSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempLiqstr = tempLiq.getText().toString();
                String tempAmbstr = tempAmb.getText().toString();
                String TshRefstr = TshRef.getText().toString();
                CheckBox enchaquetado = (CheckBox) findViewById(R.id.enchaqueetado);


                if (enchaquetado.isChecked()){
                    float CTSHen = metodosFunciones.correccionLaminaEn(tempLiqstr,TshRefstr);

                    TextView tvRespuesta = (TextView) findViewById(R.id.CTSH);

                    String respuesta = String.valueOf(CTSHen);

                    tvRespuesta.setText(respuesta);



                } else{
                    float CTSH = metodosFunciones.correccionLamina(tempLiqstr,tempAmbstr,TshRefstr);

                    TextView tvRespuesta = (TextView) findViewById(R.id.CTSH);

                    String respuesta = String.valueOf(CTSH);

                    tvRespuesta.setText(respuesta);


                }

            }
        });
    }

    private void calcularFRA() {


        Button button_calcularTecho = (Button) findViewById(R.id.button_calcular_techo);

        //Calcular Correción por techo


        button_calcularTecho.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String APItabstr = APItab.getText().toString();
                String APIobsstr = APIobs.getText().toString();
                String BblsCorstr = BblsCor.getText().toString();

                float FRA = metodosFunciones.techoFlotante(APItabstr, APIobsstr, BblsCorstr);

                TextView tvRespuesta = (TextView) findViewById(R.id.FRA);

                String respuesta = String.valueOf(FRA);

                tvRespuesta.setText(respuesta + " Bbls");

            }
        });

    }

    private void calcularGOV() {

            Button button_calcularGOV = (Button) findViewById(R.id.button_calcular_GOV);

            //Calcular Correción por techo


            button_calcularGOV.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {

                    //Calculando el FRA

                    String APItabstr = APItab.getText().toString();
                    String APIobsstr = APIobs.getText().toString();
                    String BblsCorstr = BblsCor.getText().toString();

                    float FRA = metodosFunciones.techoFlotante(APItabstr, APIobsstr, BblsCorstr);



                    //Calculando el CTSH

                    String tempLiqstr = tempLiq.getText().toString();
                    String tempAmbstr = tempAmb.getText().toString();
                    String TshRefstr = TshRef.getText().toString();
                    CheckBox enchaquetado = (CheckBox) findViewById(R.id.enchaqueetado);

                    //Calculando GOV


                    if (enchaquetado.isChecked()){
                        float CTSHen = metodosFunciones.correccionLaminaEn(tempLiqstr,TshRefstr);

                        String TOVstr= TOV.getText().toString();
                        String FWstr = FW.getText().toString();
                        String FRAstr = String.valueOf(FRA);
                        String CTSHstr = String.valueOf(CTSHen);

                        float GOV = metodosFunciones.calcularGOV(TOVstr,FWstr,FRAstr,CTSHstr);

                        TextView tvRespuesta = (TextView) findViewById(R.id.GOV);

                        String respuesta = String.valueOf(GOV);

                        tvRespuesta.setText(respuesta + " Bbls");
                    } else{
                        float CTSH = metodosFunciones.correccionLamina(tempLiqstr,tempAmbstr,TshRefstr);

                        String TOVstr= TOV.getText().toString();
                        String FWstr = FW.getText().toString();
                        String FRAstr = String.valueOf(FRA);
                        String CTSHstr = String.valueOf(CTSH);

                        float GOV = metodosFunciones.calcularGOV(TOVstr,FWstr,FRAstr,CTSHstr);

                        TextView tvRespuesta = (TextView) findViewById(R.id.GOV);

                        String respuesta = String.valueOf(GOV);

                        tvRespuesta.setText(respuesta + " Bbls");

                    }







                }
            });

        }

    }

