package com.anurag.androidbootstrap.network;

import com.anurag.androidbootstrap.model.weather.WeatherForecastResponse;
import com.anurag.androidbootstrap.model.weather.WeatherResponse;

import retrofit.Callback;

/**
 * Created by anurag on 02/03/16.
 */
public interface NetworkManager {
    /**
     *
     * @param lat       Latitude of the location
     * @param lon       Longitude of the location
     */
    void getWeatherByGeoData(final String lat, final String lon, Callback<WeatherResponse> callback);

    void getWeatherByCityName(final String fullCityName, Callback<WeatherResponse> callback);

    void getWeatherForecastByCityName(final String fullCityName, Callback<WeatherForecastResponse> callback);

    void getWeatherForecastByGeoData(final String lat, final String lon, Callback<WeatherForecastResponse> callback);
}
