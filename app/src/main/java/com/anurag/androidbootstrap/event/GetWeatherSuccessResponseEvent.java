package com.anurag.androidbootstrap.event;

import com.anurag.androidbootstrap.model.weather.WeatherResponse;

/**
 * Created by anurag on 11/04/17.
 */
public class GetWeatherSuccessResponseEvent {
    WeatherResponse mWeatherResponse;

    public GetWeatherSuccessResponseEvent(WeatherResponse weatherResponse) {
        mWeatherResponse = weatherResponse;
    }

    public WeatherResponse getResponse() {
        return mWeatherResponse;
    }
}
