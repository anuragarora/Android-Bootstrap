package com.anurag.androidbootstrap.network;

import android.content.res.Resources;

import com.anurag.androidbootstrap.R;
import com.anurag.androidbootstrap.model.weather.WeatherForecastResponse;
import com.anurag.androidbootstrap.model.weather.WeatherResponse;
import com.anurag.androidbootstrap.util.Logger;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.Converter;
import retrofit.Retrofit;

/**
 * Created by anurag on 02/03/16.
 */
public class RetrofitNetworkManager implements NetworkManager {
    private static final String TAG = RetrofitNetworkManager.class.getSimpleName();
    private final OkHttpClient mOkClient;
    private final String mBaseUrl;
    private final Converter.Factory mGsonConverter;
    private final Resources mResources;

    public RetrofitNetworkManager(OkHttpClient okClient,
                                  String baseUrl,
                                  Converter.Factory gsonConverter,
                                  Resources resources) {

        this.mOkClient = okClient;
        this.mBaseUrl = baseUrl;
        this.mGsonConverter = gsonConverter;
        this.mResources = resources;
    }

    /**
     * Creates a partially build retrofit object for OpenWeatherMap service.
     *
     * @return Builder for retrofit with OpenWeatherMap base url and okRestClient
     */
    private Retrofit.Builder getAdapter() {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(mOkClient);
    }

    @Override
    public void getWeatherByGeoData(String lat, String lon, Callback<WeatherResponse> callback) {
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .getWeatherByGeoData(lat, lon, "1", mResources.getString(R.string.weather_open_weather_map_appid))
                .enqueue(callback);
    }

    @Override
    public void getWeatherByCityName(String fullCityName, Callback<WeatherResponse> callback) {
        Logger.i(TAG, "Reached retrofit network manager cityName");
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .getWeatherByCityName(mResources.getString(R.string.weather_open_weather_map_appid), fullCityName)
                .enqueue(callback);
    }

    @Override
    public void getWeatherForecastByCityName(String fullCityName, Callback<WeatherForecastResponse> callback) {
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .get16DayWeatherForcastByCityName(mResources.getString(R.string.weather_open_weather_map_appid),
                        fullCityName, "16")
                .enqueue(callback);
    }

    @Override
    public void getWeatherForecastByGeoData(String lat, String lon, Callback<WeatherForecastResponse> callback) {
        getAdapter()
                .addConverterFactory(mGsonConverter)
                .build()
                .create(ApiService.class)
                .get16DayWeatherForecastByGeoData(lat, lon, "16",
                        mResources.getString(R.string.weather_open_weather_map_appid))
                .enqueue(callback);
    }
}
