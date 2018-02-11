package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 10/04/17.
 */
public class WeatherConditions {
    /** Weather condition id */
    @SerializedName("id")
    private int mId;

    /** Group of weather parameters (Rain, Snow, Extreme etc.) */
    @SerializedName("main")
    private String mWeatherMain;

    /** Weather condition within the group */
    @SerializedName("description")
    private String mWeatherDescription;

    /** Weather icon id */
    @SerializedName("icon")
    private String mWeatherIcon;

    public int getId() {
        return mId;
    }

    public String getWeatherMain() {
        return mWeatherMain;
    }

    public String getWeatherDescription() {
        return mWeatherDescription;
    }

    public String getWeatherIcon() {
        return mWeatherIcon;
    }
}
