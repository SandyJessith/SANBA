package com.example.sanba;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class metodosFunciones {

    //Constructor
    public metodosFunciones() {
    }


    //Metodo para calculo de densidad estándar
   public float desidadStandar(){

      float  densidadStandar = 0;

        return densidadStandar;
    }

    //Metodo para calculo de corrección de techo flotante

    public static float techoFlotante(String APItabla, String APIobservado, String BblsCorr){

        try {

            float APItab = Float.parseFloat(APItabla);
            float APIobs = Float.parseFloat(APIobservado);
            float BblsCo = Float.parseFloat(BblsCorr);


            float FRA = (APItab - APIobs) * BblsCo;

            FRA = redondear(FRA, 2);


            return FRA;
        }catch (Exception e){
            return 0;

        }
    }


    //Metodo para el calculo del CTSH
    public static float correccionLamina(String tempLiqstr, String tempAmbstr, String TshRefstr){

try {


    float tempLiq = Float.parseFloat(tempLiqstr);
    float tempAmb = Float.parseFloat(tempAmbstr);
    float TshRef = Float.parseFloat(TshRefstr);

    float Tsh = ((7 * tempLiq) + tempAmb) / 8;
    final float a = (float) 0.0000062;

    float CTSH = 1 + (2 * a * (Tsh - TshRef)) + (float) Math.pow(a, 2) * (float) Math.pow((Tsh - TshRef), 2);

    CTSH = redondear(CTSH, 5);


    return CTSH;
}catch (Exception e){

    return 0;
}
    }

    //Metodo para el calculo del CTSH para tanques enchaquetados
    public static float correccionLaminaEn(String tempLiqstr, String TshRefstr){

        try {

            float tempLiq = Float.parseFloat(tempLiqstr);
            float TshRef = Float.parseFloat(TshRefstr);

            float Tsh = tempLiq;
            final float a = (float) 0.0000062;

            float CTSH = 1 + (2 * a * (Tsh - TshRef)) + (float) Math.pow(a, 2) * (float) Math.pow((Tsh - TshRef), 2);

            CTSH = redondear(CTSH, 5);


            return CTSH;
        }catch (Exception e){
            return 0;
        }
    }

    //Metodo para el calculo de VCF
    public static float factorCorrecionVolumen(float SGp, String tempLiqstr, String producto) {


        final double a1 =  -0.148759;
        final double a2 = -0.267408;
        final double a3 =  1.080760;
        final double a4 =  1.269056;
        final double a5 =  -4.089591;
        final double a6 =  -1.871251;
        final double a7 =  7.438081;
        final double a8 =  -3.536296;

        //δ60 Temperature shift value
        final double ro60 =  0.01374979547;

        //Euler
        final double e = (float) 2.7182818284590452354;

        //Inicializando constantes K0, K1 & K2

        double K0 = 0, K1=0, K2=0;

        double SG = redondear(SGp,1);
        double tempLiq = Double.parseDouble(tempLiqstr);

        double Ctl = 0;

        //Farenhait a Celcius
        double tcel =  ((tempLiq - 32) / 1.8);

        //Calculo de temperatura Corregida
        float tp = (float)tcel / 630;
        double t = redondear(tp,1);

        double deltaT = (a1 + (a2 + (a3 + (a4 + (a5 + (a6 + (a7 + a8 * t) * t) * t) * t) * t) * t) * t) * t;

        double tcorr = tcel - deltaT;
        //float tcorr = redondear(tcorrp,1);

        //Pasando la Temp corregida a Farenhait
        double tcorrf = 1.8 * tcorr +32;
       // float tcorrf = redondear(tcorrfp, 1);


        switch (producto) {
            case "No especificado":
                break;


            case "Crude Oil":
                if (SG >= 610.6 && SG < 1163.5) {
                 K0 =  341.0957;
                 K1 =  0;
                 K2 =  0;


                } else {
                        /*Toast.makeText(Calculos.class,"La densidad" +
                                " está por fuera del rango permitido",Toast.LENGTH_LONG).show();*/
                }
                break;
            case "Fuel Oils":
                if (SG >= 838.3127 && SG <= 1163.5) {
                     K0 = 103.8720;
                     K1 =  0.2701;
                     K2 =  0;
                } else {
                        /*Toast.makeText(Calculos.class,"La densidad" +
                                " está por fuera del rango permitido",Toast.LENGTH_LONG).show();*/
                }

            case "Jet Fuels":
                if (SG >= 787.5195 && SG < 838.3127) {
                     K0 =  330.3010;
                     K1 =  0;
                     K2 =  0;
                } else {
                        /*Toast.makeText(Calculos.class,"La densidad" +
                                " está por fuera del rango permitido",Toast.LENGTH_LONG).show();*/
                }
                break;
            case "Transition Zone":
                if (SG >= 770.3520 && SG < 787.5195) {
                     K0 =  1489.0670;
                     K1 =  0;
                     K2 =  -0.00186840;
                } else {
                        /*Toast.makeText(Calculos.class,"La densidad" +
                                " está por fuera del rango permitido",Toast.LENGTH_LONG).show();*/
                }
                break;
            case "Gasolines":
                if (SG >= 610.6 && SG < 770.3520) {
                     K0 =  192.4571;
                     K1 =  0.2438;
                     K2 =  0;
                } else {
                        /*Toast.makeText(Calculos.class,"La densidad" +
                                " está por fuera del rango permitido",Toast.LENGTH_LONG).show();*/
                }
                break;


            case "Lubricating Oils":
                if (SG >= 800.9 && SG < 1163.5) {
                     K0 =  0;
                     K1 =  0.34878;
                     K2 =  0;
                } else {
                        /*Toast.makeText(Calculos.class,"La densidad" +
                                " está por fuera del rango permitido",Toast.LENGTH_LONG).show();*/
                }
                break;
            default:
                    /*Toast.makeText(Calculos.this,"La densidad" +
                            " está por fuera del rango permitido",Toast.LENGTH_LONG).show();*/
                break;



        }



        double A = (ro60 / 2) * (((K0 / SG) + K1) * (1 / SG) + K2);

        double B = ((2 * K0) + (K1 * SG)) / (K0 + (K1 + (K2 * SG) * SG));

        // Base density shifted to IPTS-68 60°F basis (kg/m³)
        double SGS = SG * (1 + ((Math.exp (A * (1 + 0.8 * A)))-1) / (1 + A * (1 +
                (1.6 * A) * B)));

        //SGS Pasado a float
        //float SGSf = (float) SGS;

        //Calculo de factor de expansión termica a 60 °F Thermal expansion factor
        // at 60°F (°F-1) (if not input)

        double a60 = ((K0 / SGS) + K1) * (1 / SGS) + K2;

        //float a60 = redondear(a60p,6);

        //Calculo del delta t temperatura alterna y temperatura base
        double dt = tcorrf -  60.0068749;

        //Calculando el CTL
        Ctl = Math.exp (((-a60) * dt) * (1 + (0.8 * a60) * (dt + ro60)));
        float CTL = (float) Ctl;

       float CTLr = redondear(CTL,5);

        return CTLr;
    }

    //Metodo para el calculod de GOV

    public static float calcularGOV(String TOVstr, String FWstr, String FRAstr, String CTSHstr ) {

        try {

            float TOV = Float.parseFloat(TOVstr);
            float FW = Float.parseFloat(FWstr);
            float FRA = Float.parseFloat(FRAstr);
            float CTSH = Float.parseFloat(CTSHstr);


            float GOV = ((TOV - FW) * CTSH) + FRA;

            GOV = redondear(GOV, 2);


            return GOV;
        }catch (Exception e){
            return 0;
        }
    }

    public static float calcularGSV(String VCFstr, String GOVstr ) {

        try {

            float VCF = Float.parseFloat(VCFstr);
            float GOV = Float.parseFloat(GOVstr);


            float GSVp = (GOV*VCF);

           float GSV = redondear(GSVp, 2);


            return GSV;
        }catch (Exception e){
            return 0;
        }
    }


    //Metodo para redondear
    public static float redondear(float numero, int digitos) {
        float resultado;
        resultado = numero * (float)Math.pow(10, digitos);
        resultado = (float)Math.round(resultado);
        resultado =  resultado/(float)Math.pow(10, digitos);
        return resultado;
    }

}
