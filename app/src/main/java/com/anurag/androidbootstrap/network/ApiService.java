package com.anurag.androidbootstrap.network;

import com.anurag.androidbootstrap.model.weather.WeatherForecastResponse;
import com.anurag.androidbootstrap.model.weather.WeatherResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Endpoints for the REST web services
 */
public interface ApiService {

    @GET("data/2.5/find")
    Call<WeatherResponse> getWeatherByGeoData(@Query("lat") String lat, @Query("lon") String lon,
                                              @Query("cnt") String cities, @Query("appid") String appId);

    @GET("data/2.5/find")
    Call<WeatherResponse> getWeatherByCityName(@Query("appid") String appid, @Query("q") String cityName);

    @GET("data/2.5/forecast/daily")
    Call<WeatherForecastResponse> get16DayWeatherForcastByCityName(@Query("appid") String appid,
                                                                   @Query("q") String cityName,
                                                                   @Query("cnt") String count);


    @GET("data/2.5/forecast/daily")
    Call<WeatherForecastResponse> get16DayWeatherForecastByGeoData(@Query("lat") String lat, @Query("lon") String lon,
                                                                   @Query("cnt") String dayCount, @Query("appid") String appId);
}
