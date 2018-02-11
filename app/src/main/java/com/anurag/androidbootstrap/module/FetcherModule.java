package com.anurag.androidbootstrap.module;


import com.anurag.androidbootstrap.fetcher.WeatherFetcher;
import com.anurag.androidbootstrap.fetcher.WeatherForecastFetcher;
import com.anurag.androidbootstrap.fetcher.WeatherForecastNetworkFetcher;
import com.anurag.androidbootstrap.fetcher.WeatherNetworkFetcher;

import static com.anurag.androidbootstrap.module.EventBusModule.eventBus;
import static com.anurag.androidbootstrap.module.NetworkManagerModule.retrofitNetworkManager;

/**
 * Created by anurag_arora on 11/17/2015.
 */
public class FetcherModule {
    public static WeatherFetcher WeatherFetcher() {
        return new WeatherNetworkFetcher(retrofitNetworkManager(), eventBus());
    }

    public static WeatherForecastFetcher WeatherForecastFetcher() {
        return new WeatherForecastNetworkFetcher(retrofitNetworkManager(), eventBus());
    }
}
