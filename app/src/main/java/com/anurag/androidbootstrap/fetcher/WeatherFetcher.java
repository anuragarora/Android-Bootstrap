package com.anurag.androidbootstrap.fetcher;

/**
 * Created by anurag on 10/04/17.
 */
public interface WeatherFetcher {
    void getWeatherByGeoData(String lat, String lon);
    void getWeatherByCityName(String cityName);

}
