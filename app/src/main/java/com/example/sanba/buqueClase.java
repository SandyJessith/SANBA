package com.example.sanba;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;

@Entity(tableName = "tabla_buque")
public class buqueClase {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombreBuque;
    private String nombreProducto;
    private String operacion;
    @ColumnInfo(name = "Columna_TL") //Esto cambia el nombre de la columna
    private String TL;
    //private int numeroUpdate;
    private float nominacion;
    private int priority;

/*    public buqueClase(String nombreBuque, String nombreProducto, String operacion, String TL,
                      int numeroUpdate, float nominacion) {
        this.nombreBuque = nombreBuque;
        this.nombreProducto = nombreProducto;
        this.operacion = operacion;
        this.TL = TL;
        this.numeroUpdate = numeroUpdate;
        this.nominacion = nominacion;
    }

    public buqueClase(String nombreBuque, String nombreProducto, String operacion, String TL,
                      int numeroUpdate) {
        this.nombreBuque = nombreBuque;
        this.nombreProducto = nombreProducto;
        this.operacion = operacion;
        this.TL = TL;
        this.numeroUpdate = numeroUpdate;
    }

    public buqueClase(String nombreBuque, String nombreProducto, String operacion, String TL
                      ) {
        this.nombreBuque = nombreBuque;
        this.nombreProducto = nombreProducto;
        this.operacion = operacion;
        this.TL = TL;
            }*/

    public buqueClase(int priority, float nominacion, String nombreBuque, String nombreProducto
            , String operacion, String TL) {

        this.nombreBuque = nombreBuque;
        this.nombreProducto = nombreProducto;
        this.operacion = operacion;
        this.TL = TL;
        this.priority = priority;
        this.nominacion = nominacion;

    }



    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }


    public String getNombreBuque() {
        return nombreBuque;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getTL() {
        return TL;
    }

    /*public int getNumeroUpdate() {
        return numeroUpdate;
    }*/

    public float getNominacion() {
        return nominacion;
    }


}