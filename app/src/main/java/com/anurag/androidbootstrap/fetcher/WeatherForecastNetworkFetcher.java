package com.anurag.androidbootstrap.fetcher;

import com.anurag.androidbootstrap.event.GetWeatherFailureResponseEvent;
import com.anurag.androidbootstrap.event.WeatherForecastSuccessEvent;
import com.anurag.androidbootstrap.model.weather.WeatherForecastResponse;
import com.anurag.androidbootstrap.network.NetworkManager;
import com.anurag.androidbootstrap.util.Logger;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by anurag on 18/04/17.
 */
public class WeatherForecastNetworkFetcher implements WeatherForecastFetcher, Callback<WeatherForecastResponse> {
    private final NetworkManager mNetworkManager;
    private final EventBus mEventBus;

    public WeatherForecastNetworkFetcher(NetworkManager networkManager, EventBus eventBus){
        mNetworkManager = networkManager;
        mEventBus = eventBus;
    }

    @Override
    public void getWeatherForecastByCityName(String cityName) {
        mNetworkManager.getWeatherForecastByCityName(cityName, this);
    }

    @Override
    public void getWeatherForecastByGeoLocation(String lat, String lon) {
        Logger.i(WeatherForecastNetworkFetcher.class.getSimpleName(), "Fetcher calling forecast by location");
        mNetworkManager.getWeatherForecastByGeoData(lat, lon, this);
    }

    @Override
    public void onResponse(Response<WeatherForecastResponse> response, Retrofit retrofit) {
        Logger.i(WeatherForecastNetworkFetcher.class.getSimpleName(), "Response successfully received");
        mEventBus.post(new WeatherForecastSuccessEvent(response.body()));
    }

    @Override
    public void onFailure(Throwable t) {
        Logger.i(WeatherForecastNetworkFetcher.class.getSimpleName(), t.getMessage());
        Logger.i(WeatherForecastNetworkFetcher.class.getSimpleName(), "Response failure");
        mEventBus.post(new GetWeatherFailureResponseEvent());
    }
}
