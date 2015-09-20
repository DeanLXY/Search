package com.example.search.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wxj on 2015-9-20.
 */
public class JsonUtils {


    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static <E> List<E> fromJson2List(String json, Class<E> clazz) {
        Gson gson = new Gson();

        Type type = new TypeToken<List<E>>(){}.getType();
        return (List<E>) gson.fromJson(json, type);
    }
}
