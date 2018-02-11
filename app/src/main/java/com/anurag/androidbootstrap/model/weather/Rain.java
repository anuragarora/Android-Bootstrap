package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 10/04/17.
 */
public class Rain {
    /** Rain volume for the last 3 hours */
    @SerializedName("3h")
    private float mLast3Hrs;
}