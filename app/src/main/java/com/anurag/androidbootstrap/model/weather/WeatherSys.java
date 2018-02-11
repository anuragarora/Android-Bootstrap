package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 10/04/17.
 */
public class WeatherSys {
    /** Country code (GB, JP etc.) */
    @SerializedName("country")
    private String mCountry;

    /** Sunrise time, unix, UTC */
    @SerializedName("sunrise")
    private long mSunrise;

    /** Sunset time, unix, UTC */
    @SerializedName("sunset")
    private long mSunset;

    public String getCountry() {
        return mCountry;
    }

    public long getSunrise() {
        return mSunrise;
    }

    public long getSunset() {
        return mSunset;
    }
}
