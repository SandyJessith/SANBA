package com.example.sanba;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BuqueRepository {
    private BuqueDao buqueDao;
    private LiveData<List<buqueClase>> allBuques;

    public BuqueRepository(Application application){
        BuqueDataBase dataBase = BuqueDataBase.getInstance(application);
        buqueDao = dataBase.buqueDao();
        allBuques = buqueDao.getAllBuques();
    }

    public void insert (buqueClase buque){
       new InsertBuqueAsyncTask(buqueDao).execute(buque);
    }

    public void update (buqueClase buque){
        new UpdateBuqueAsyncTask(buqueDao).execute(buque);
    }

    public void delete (buqueClase buque){
        new DeleteBuqueAsyncTask(buqueDao).execute(buque);
    }

    public  void deteAllBuques (){
        new DeleteAllBuqueAsyncTask(buqueDao).execute();
    }

    public LiveData<List<buqueClase>> getAllBuques() {
        return allBuques;
    }

    //Este es para insertar
    private static class InsertBuqueAsyncTask extends AsyncTask <buqueClase, Void, Void>{
        private BuqueDao buqueDao;

        private InsertBuqueAsyncTask(BuqueDao buqueDao){
            this.buqueDao = buqueDao;
        }

        @Override
        protected Void doInBackground(buqueClase... buques) {
            buqueDao.insert(buques[0]);
            return null;
        }
    }

    private static class UpdateBuqueAsyncTask extends AsyncTask <buqueClase, Void, Void>{
        private BuqueDao buqueDao;

        private UpdateBuqueAsyncTask(BuqueDao buqueDao){
            this.buqueDao = buqueDao;
        }

        @Override
        protected Void doInBackground(buqueClase... buques) {
            buqueDao.update(buques[0]);
            return null;
        }
    }

    private static class DeleteBuqueAsyncTask extends AsyncTask <buqueClase, Void, Void>{
        private BuqueDao buqueDao;

        private DeleteBuqueAsyncTask(BuqueDao buqueDao){
            this.buqueDao = buqueDao;
        }

        @Override
        protected Void doInBackground(buqueClase... buques) {
            buqueDao.delete(buques[0]);
            return null;
        }
    }

    private static class DeleteAllBuqueAsyncTask extends AsyncTask <Void, Void, Void>{
        private BuqueDao buqueDao;

        private DeleteAllBuqueAsyncTask(BuqueDao buqueDao){
            this.buqueDao = buqueDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            buqueDao.deteleAllBuques();
            return null;
        }
    }
}

