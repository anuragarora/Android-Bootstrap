package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anurag on 18/04/17.
 */
public class WeatherForecastResponse {
    /** Weather accuracy message */
    @SerializedName("city")
    private City mCity;

    /** Weather code */
    @SerializedName("cod")
    private String mCod;

    @SerializedName("coord")
    private Coordinate mCoord;

    /** Weather parameters list */
    @SerializedName("list")
    private List<WeatherForecastParams> mWeatherList;

    public City getCity() {
        return mCity;
    }

    public String getCod() {
        return mCod;
    }

    public Coordinate getmCoord() {
        return mCoord;
    }

    public List<WeatherForecastParams> getWeatherList() {
        return mWeatherList;
    }
}
