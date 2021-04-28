package com.example.sanba.utilidades;


import android.provider.BaseColumns;

public class Utilidades {




        //Constantes campos tabla buque
        public static final String TABLA_BUQUE = "buque";
        public static final String CAMPO_NOMBRE = "nombreBuque";
       // public static final String CAMPO_ID = "id";
        public static final String CAMPO_PRODUCTO = "nombreProducto";
        public static final String CAMPO_OPERACION = "operacion";
        public static final String CAMPO_TL = "TL";
       // public static final String CAMPO_NOMINACION = "nominacion";


  public static  final String CREAR_TABLA_BUQUE="CREATE TABLE "+TABLA_BUQUE+"("+CAMPO_NOMBRE+" TEXT,"
          +CAMPO_PRODUCTO+" TEXT,"+CAMPO_OPERACION+" TEXT," +
            " "+CAMPO_TL+" TEXT)";


}
