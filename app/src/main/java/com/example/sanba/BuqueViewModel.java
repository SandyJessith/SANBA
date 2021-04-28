package com.example.sanba;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BuqueViewModel extends AndroidViewModel {
    private BuqueRepository repository;
    private LiveData<List<buqueClase>> allBuques;

    public BuqueViewModel(@NonNull Application application) {
        super(application);
        repository = new BuqueRepository(application);
        allBuques = repository.getAllBuques();
    }

    public void insert(buqueClase buque){
        repository.insert(buque);
    }

    public void update (buqueClase buque){
        repository.update(buque);
    }

    public void delete (buqueClase buque){
        repository.delete(buque);
    }

    public void deleteAllBuques (){
        repository.deteAllBuques();
    }

    public LiveData<List<buqueClase>> getAllBuques(){
        return allBuques;
    }
}
