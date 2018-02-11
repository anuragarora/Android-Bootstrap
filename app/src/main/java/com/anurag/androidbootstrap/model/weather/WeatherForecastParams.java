package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anurag on 18/04/17.
 */
public class WeatherForecastParams {
    @SerializedName("temp")
    private Temp mTemp;

    @SerializedName("pressure")
    private double mPressure;

    @SerializedName("humidity")
    private double mHumidity;

    @SerializedName("speed")
    private double mSpeed;


    @SerializedName("deg")
    private double mDeg;

    @SerializedName("clouds")
    private int mCloudiness;

    /**
     * Time of data calculation, unix, UTC
     */
    @SerializedName("dt")
    private int mDt;

    @SerializedName("weather")
    private List<WeatherConditions> mWeather;

    public List<WeatherConditions> getWeather() {
        return mWeather;
    }

    public Temp getTemp() {
        return mTemp;
    }

    public double getPressure() {
        return mPressure;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public int getDt() {
        return mDt;
    }

    public double getmSpeed() {
        return mSpeed;
    }

    public double getmDeg() {
        return mDeg;
    }

    public int getmCloudiness() {
        return mCloudiness;
    }
}
