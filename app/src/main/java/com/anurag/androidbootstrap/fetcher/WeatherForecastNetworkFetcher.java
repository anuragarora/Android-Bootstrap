package com.anurag.androidbootstrap.fetcher;

import com.anurag.androidbootstrap.event.GetWeatherFailureResponseEvent;
import com.anurag.androidbootstrap.event.WeatherForecastSuccessEvent;
import com.anurag.androidbootstrap.model.weather.WeatherForecastResponse;
import com.anurag.androidbootstrap.network.NetworkManager;
import com.anurag.androidbootstrap.util.Logger;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onResponse(Call<WeatherForecastResponse> call, Response<WeatherForecastResponse> response) {
        Logger.i(WeatherForecastNetworkFetcher.class.getSimpleName(), "Response successfully received");
        mEventBus.post(new WeatherForecastSuccessEvent(response.body()));
    }

    @Override
    public void onFailure(Call<WeatherForecastResponse> call, Throwable t) {
        Logger.i(WeatherForecastNetworkFetcher.class.getSimpleName(), t.getMessage());
        Logger.i(WeatherForecastNetworkFetcher.class.getSimpleName(), "Response failure");
        mEventBus.post(new GetWeatherFailureResponseEvent());
    }
}
