package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anurag on 10/04/17.
 */
public class WeatherParams {
    /** City ID */
    @SerializedName("id")
    private int mId;

    /** City name */
    @SerializedName("name")
    private String mName;

    /** City coordinates */
    @SerializedName("coord")
    private Coordinate mCoord;

    /** City weather main */
    @SerializedName("main")
    private WeatherMain mMain;

    /** Time of data calculation, unix, UTC */
    @SerializedName("dt")
    private int mDt;

    /** City weather wind */
    @SerializedName("wind")
    private Wind mWind;

    /** City (country, sunrise, sunset) */
    @SerializedName("sys")
    private WeatherSys mSys;

    /** City rain info */
    @SerializedName("rain")
    private Rain mRain;

    /** City snow info */
    @SerializedName("snow")
    private Snow mSnow;

    /** City cloudiness info */
    @SerializedName("clouds")
    private Clouds mClouds;

    /** City weather more info */
    @SerializedName("weather")
    private List<WeatherConditions> mWeather;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Coordinate getCoord() {
        return mCoord;
    }

    public WeatherMain getMain() {
        return mMain;
    }

    public int getDt() {
        return mDt;
    }

    public Wind getWind() {
        return mWind;
    }

    public WeatherSys getSys() {
        return mSys;
    }

    public Rain getRain() {
        return mRain;
    }

    public Snow getSnow() {
        return mSnow;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public List<WeatherConditions> getWeather() {
        return mWeather;
    }
}
