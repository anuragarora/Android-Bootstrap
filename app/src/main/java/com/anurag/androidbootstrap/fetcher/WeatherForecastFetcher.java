package com.anurag.androidbootstrap.fetcher;

/**
 * Created by anurag on 18/04/17.
 */
public interface WeatherForecastFetcher {
    void getWeatherForecastByCityName(String cityName);
    void getWeatherForecastByGeoLocation(String lat, String lon);

}
