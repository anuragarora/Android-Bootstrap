package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 10/04/17.
 */
public class Clouds {
    /** Cloudiness, % */
    @SerializedName("all")
    private int mCloudiness;

    public int getCloudiness() {
        return mCloudiness;
    }
}
