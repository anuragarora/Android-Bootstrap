package com.anurag.androidbootstrap.event;

import com.anurag.androidbootstrap.model.weather.WeatherForecastResponse;

/**
 * Created by anurag on 18/04/17.
 */
public class WeatherForecastSuccessEvent {
    WeatherForecastResponse mWeatherResponse;

    public WeatherForecastSuccessEvent(WeatherForecastResponse weatherResponse) {
        mWeatherResponse = weatherResponse;
    }

    public WeatherForecastResponse getResponse() {
        return mWeatherResponse;
    }
}
