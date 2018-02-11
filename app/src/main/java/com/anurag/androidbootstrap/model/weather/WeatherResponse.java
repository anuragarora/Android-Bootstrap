package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anurag on 10/04/17.
 */
public class WeatherResponse {
    /** Weather accuracy message */
    @SerializedName("message")
    private String mAccuracy;

    /** Weather code */
    @SerializedName("cod")
    private String mCod;

    /** City count. provided city name as input */
    @SerializedName("count")
    private int mCityCount;

    /** Weather parameters list */
    @SerializedName("list")
    private List<WeatherParams> mWeatherList;

    public String getAccuracy() {
        return mAccuracy;
    }

    public String getCod() {
        return mCod;
    }

    public int getCityCount() {
        return mCityCount;
    }

    public List<WeatherParams> getWeatherList() {
        return mWeatherList;
    }
}
