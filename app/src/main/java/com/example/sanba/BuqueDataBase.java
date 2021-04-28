package com.example.sanba;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {buqueClase.class},version = 1)//Si quieres pasarle más de una entity utilzas{} y separas con ","
public abstract class BuqueDataBase extends RoomDatabase {

    private static BuqueDataBase instance;

    public abstract BuqueDao buqueDao();

    public static synchronized BuqueDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BuqueDataBase.class,
                    "buque_database").fallbackToDestructiveMigration().addCallback(roomcallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(instance).execute();
        }
    };

    private static class PopulateDbAsynctask extends AsyncTask<Void, Void,Void>{

        private BuqueDao buqueDao;

        private PopulateDbAsynctask(BuqueDataBase db){
            buqueDao = db.buqueDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            buqueDao.insert(new buqueClase(2,250000,"Lumen N",
                    "Acordionero", "Cargue","190009"));

            buqueDao.insert(new buqueClase(3,430000,"RBD GINNO FERRETTI",
                    "FUEL OIL", "Cargue","190008"));

            buqueDao.insert(new buqueClase(1,300000,"ONIX",
                    "NAFTHA", "Descargue","190007"));

            return null;
        }
    }

}
