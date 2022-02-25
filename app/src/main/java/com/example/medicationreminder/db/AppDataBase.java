package com.example.medicationreminder.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.medicationreminder.model.Converters;
import com.example.medicationreminder.model.Medication;

@Database(entities = {Medication.class},version=6)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    private  static AppDataBase instance=null;
    public  abstract DrugDao drugDao();
    public static synchronized AppDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"Medication").build();
        }
        return instance;
    }

}
