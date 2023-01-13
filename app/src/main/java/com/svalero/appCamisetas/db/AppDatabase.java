package com.svalero.appCamisetas.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.appCamisetas.domain.Objeto;

@Database(entities = {Objeto.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract Objeto objetoDao();
}
