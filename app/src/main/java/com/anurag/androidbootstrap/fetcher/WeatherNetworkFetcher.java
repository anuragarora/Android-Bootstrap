package com.anurag.androidbootstrap.fetcher;

import com.anurag.androidbootstrap.event.GetWeatherFailureResponseEvent;
import com.anurag.androidbootstrap.event.GetWeatherSuccessResponseEvent;
import com.anurag.androidbootstrap.model.weather.WeatherResponse;
import com.anurag.androidbootstrap.network.NetworkManager;
import com.anurag.androidbootstrap.util.Logger;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by anurag on 10/04/17.
 */
public class WeatherNetworkFetcher implements WeatherFetcher, Callback<WeatherResponse> {
    private final NetworkManager mNetworkManager;
    private final EventBus mEventBus;

    public WeatherNetworkFetcher(NetworkManager networkManager, EventBus eventBus) {
        this.mNetworkManager = networkManager;
        this.mEventBus = eventBus;
    }

    public void getWeatherByGeoData(String lat, String lon){
        mNetworkManager.getWeatherByGeoData(lat,lon, this);
    }

    public void getWeatherByCityName(String cityName){
        mNetworkManager.getWeatherByCityName(cityName, this);
    }

    @Override
    public void onResponse(Response<WeatherResponse> response, Retrofit retrofit) {
        Logger.i(WeatherNetworkFetcher.class.getSimpleName(), "Response successfully received");
        mEventBus.post(new GetWeatherSuccessResponseEvent(response.body()));
    }

    @Override
    public void onFailure(Throwable t) {
        Logger.i(WeatherNetworkFetcher.class.getSimpleName(), "Response failure");
        mEventBus.post(new GetWeatherFailureResponseEvent());
    }
}
