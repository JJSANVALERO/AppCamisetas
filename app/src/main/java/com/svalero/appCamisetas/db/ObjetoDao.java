package com.svalero.appCamisetas.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.appCamisetas.domain.Objeto;

import java.util.List;

@Dao
public interface ObjetoDao {

    @Query("SELECT * FROM objeto")
    List<Objeto> getAll();

    @Query("SELECT * FROM objeto WHERE id = :id")
    Objeto getById(Long id);

    @Insert
    void insert(Objeto objeto);

    @Delete
    void delete(Objeto objeto);

    @Update
    void update (Objeto objeto);
}
