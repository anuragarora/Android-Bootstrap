package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 18/04/17.
 */
public class Temp {
    @SerializedName("day")
    private double mDay;

    @SerializedName("min")
    private double mMin;

    @SerializedName("max")
    private double mMax;

    @SerializedName("night")
    private double mNight;

    @SerializedName("eve")
    private double mEve;

    @SerializedName("morn")
    private double mMorn;

    public double getDay() {
        return mDay-273.15;
    }

    public double getMin() {
        return mMin-273.15;
    }

    public double getMax() {
        return mMax-273.15;
    }

    public double getNight() {
        return mNight-273.15;
    }

    public double getEve() {
        return mEve-273.15;
    }

    public double getMorn() {
        return mMorn-273.15;
    }
}
