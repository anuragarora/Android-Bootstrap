package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 18/04/17.
 */
public class City {
    public String getmCityName() {
        return mCityName;
    }

    @SerializedName("name")
    private String mCityName;
}
