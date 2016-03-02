package com.anurag.androidbootstrap.module;

import com.anurag.androidbootstrap.converter.RepoInfoConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anurag_Arora on 3/2/2016.
 */
public class ConverterModule {
    private static final String GSON_DATE_FORMAT = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'";
    private static final Gson gson = new GsonBuilder()
            .setDateFormat(GSON_DATE_FORMAT)
            .create();

    public static Converter.Factory gsonConverter(){
        return GsonConverterFactory.create(gson);
    }

    public static RepoInfoConverter repoInfoConverter(){
        return new RepoInfoConverter(gsonConverter());
    }
}
