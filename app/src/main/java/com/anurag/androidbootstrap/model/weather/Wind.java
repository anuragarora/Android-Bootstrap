package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 10/04/17.
 */
public class Wind {
    /** Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour. */
    @SerializedName("speed")
    private double mSpeed;

    /** Wind direction, degrees (meteorological) */
    @SerializedName("deg")
    private double mDegree;

    public double getmSpeed() {
        return mSpeed;
    }

    public double getmDegree() {
        return mDegree;
    }
}
