package com.example.medicationreminder.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medicationreminder.model.Drug;

@Database(entities = {Drug.class},version=1)
public abstract class AppDataBase extends RoomDatabase {
    private  static AppDataBase instance=null;
    public static synchronized AppDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"movies").build();

        }
        return instance;
    }
    public  abstract DrugDao drugDao();
}
