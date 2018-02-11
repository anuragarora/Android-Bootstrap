package com.anurag.androidbootstrap.model.weather;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

/**
 * Created by anurag on 10/04/17.
 */
public class WeatherMain {
    /** Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. */
    @SerializedName("temp")
    private double mTemp;

    /** Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa */
    @SerializedName("pressure")
    private double mPressure;

    /** Humidity, % */
    @SerializedName("humidity")
    private double mHumidity;

    /** Minimum temperature at the moment.
    * This is deviation from current temp that is possible for large cities and
    * megalopolises geographically expanded (use these parameter optionally).
    * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. */
    @SerializedName("temp_min")
    private double mMinTemp;

    /** Maximum temperature at the moment.
    * This is deviation from current temp that is possible for large cities and
    * megalopolises geographically expanded (use these parameter optionally).
    * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. */
    @SerializedName("temp_max")
    private double mMaxTemp;

    public double getPressure() {
        return mPressure;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public double getTemp() {
        return Double.parseDouble(new DecimalFormat("##.##").format(mTemp-273.15));
    }

    public double getMinTemp() {
        return Double.parseDouble(new DecimalFormat("##.##").format(mMinTemp-273.15));
    }

    public double getMaxTemp() {
        return (Double.parseDouble(new DecimalFormat("##.##").format(mMaxTemp-273.15)));
    }
}
