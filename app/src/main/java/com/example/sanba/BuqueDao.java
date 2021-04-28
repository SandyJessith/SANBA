package com.example.sanba;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BuqueDao {

    @Insert
    void insert(buqueClase buque);

    @Update
    void update (buqueClase buque);

    @Delete
    void delete (buqueClase buque);

    @Query("DELETE FROM tabla_buque")
    void deteleAllBuques();

    @Query("SELECT * FROM tabla_buque ORDER BY priority DESC")
    LiveData<List<buqueClase>> getAllBuques();


}
