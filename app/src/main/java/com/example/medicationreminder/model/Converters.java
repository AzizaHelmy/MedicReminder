package com.example.medicationreminder.model;

import androidx.room.TypeConverter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Converters {

    @TypeConverter
    public static String[] stringToDrugList(String value) {
        Type listType = new TypeToken<String[]>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String drugListToString(String []list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


    @TypeConverter
    public static List<String> stringToList(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String ListToString(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}