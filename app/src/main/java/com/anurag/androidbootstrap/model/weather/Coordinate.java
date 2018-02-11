package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 10/04/17.
 */
public class Coordinate {
    // City geo location, latitude
    @SerializedName("lat")
    private double mLat;

    // City geo location, longitude
    @SerializedName("lon")
    private double mLon;
}
