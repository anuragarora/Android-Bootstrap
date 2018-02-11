package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anurag on 10/04/17.
 */
public class Snow {
    /** Snow volume for the last 3 hours */
    @SerializedName("3h")
    private int mLast3Hrs;

}
