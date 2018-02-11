package com.anurag.androidbootstrap.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.anurag.androidbootstrap.R;
import com.anurag.androidbootstrap.event.GetWeatherFailureResponseEvent;
import com.anurag.androidbootstrap.event.GetWeatherSuccessResponseEvent;
import com.anurag.androidbootstrap.event.WeatherForecastFailureResponse;
import com.anurag.androidbootstrap.event.WeatherForecastSuccessEvent;
import com.anurag.androidbootstrap.fetcher.WeatherFetcher;
import com.anurag.androidbootstrap.fetcher.WeatherForecastFetcher;
import com.anurag.androidbootstrap.model.weather.WeatherForecastResponse;
import com.anurag.androidbootstrap.model.weather.WeatherResponse;
import com.anurag.androidbootstrap.util.BackgroundHelper;
import com.anurag.androidbootstrap.util.Logger;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

import static com.anurag.androidbootstrap.module.EventBusModule.eventBus;
import static com.anurag.androidbootstrap.module.FetcherModule.WeatherFetcher;
import static com.anurag.androidbootstrap.module.FetcherModule.WeatherForecastFetcher;

public class WeatherActivity extends BaseActivity {
    private static final String TAG = WeatherActivity.class.getSimpleName();
    @BindView(R.id.activity_weather_view_switcher)
    public ViewSwitcher mViewSwitcher;

    @BindView(R.id.activity_weather_icon)
    public ImageView mWeatherIcon;

    @BindView(R.id.activity_weather_in_city_title)
    public TextView mWeatherMainInCity;

    @BindView(R.id.activity_weather_in_city_title_value)
    public TextView mWeatherInCityValue;

    @BindView(R.id.activity_weather_description_value)
    public TextView mWeatherDescriptionValue;

    @BindView(R.id.activity_weather_temp_feels_like_value)
    public TextView mWeatherFeelsLikeValue;

    @BindView(R.id.activity_weather_temp_min_value)
    public TextView mWeatherMinValue;

    @BindView(R.id.activity_weather_temp_max_value)
    public TextView mWeatherMaxValue;

    @BindView(R.id.activity_weather_sunrise_value)
    public TextView mWeatherSunrise;

    @BindView(R.id.activity_weather_sunset_value)
    public TextView mWeatherSunset;

    @BindView(R.id.activity_weather_sunrise)
    public RelativeLayout mWeatherSunriseLayout;

    @BindView(R.id.activity_weather_sunset)
    public RelativeLayout mWeatherSunsetLayout;

    private final WeatherFetcher mWeatherFetcher;
    private final EventBus mEventBus;
    private final WeatherForecastFetcher mWeatherForecastFetcher;
    //private GPS mGPS;

    public WeatherActivity() {
        this(WeatherFetcher(), eventBus(), WeatherForecastFetcher());
    }

    public WeatherActivity(WeatherFetcher weatherFetcher, EventBus eventBus,
                           WeatherForecastFetcher weatherForecastFetcher) {
        mWeatherFetcher = weatherFetcher;
        mEventBus = eventBus;
        mWeatherForecastFetcher = weatherForecastFetcher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        mEventBus.register(this);
        //mGPS = new GPS(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*if(mGPS.canGetLocation()){
            mWeatherFetcher.getWeatherByGeoData(String.valueOf(mGPS.getLatitude()), String.valueOf(mGPS.getLongitude()));}
        else {
            mWeatherFetcher.getWeatherByCityName("Tempe");
        }*/

        mViewSwitcher.setBackgroundResource(BackgroundHelper.getBackground());
        Logger.i(TAG, "Calling weather by cityname");
        mWeatherFetcher.getWeatherByCityName("Tempe");
        //mWeatherForecastFetcher.getWeatherForecastByGeoLocation("33.424564", "-111.94");
    }

    public void onEvent(GetWeatherSuccessResponseEvent event) {
        WeatherResponse response = event.getResponse();
        mViewSwitcher.showNext();
        Log.i(TAG, "Inside success");

        Picasso.with(WeatherActivity.this)
                .load("http://openweathermap.org/img/w/" +
                        response.getWeatherList().get(0).getWeather().get(0).getWeatherIcon() + ".png")
                .into(mWeatherIcon);

        // Filling in data
        mWeatherMainInCity.setText("Weather in city: " + response.getWeatherList().get(0).getName());

        //Weather Main
        mWeatherInCityValue.setText(
                response.getWeatherList().get(0).getWeather().get(0).getWeatherMain());

        //Weather Description
        mWeatherDescriptionValue.setText(
                response.getWeatherList().get(0).getWeather().get(0).getWeatherDescription());

        // Temp feels like
        mWeatherFeelsLikeValue.setText(
                Double.toString(response.getWeatherList().get(0).getMain().getTemp()));

        // min Temp
        mWeatherMinValue.setText(
                Double.toString(response.getWeatherList().get(0).getMain().getMinTemp()));

        //Max temp
        mWeatherMaxValue.setText(
                Double.toString(response.getWeatherList().get(0).getMain().getMaxTemp()));

        // Sunrise
        if (response.getWeatherList().get(0).getSys().getSunrise() != 0.0) {
            mWeatherSunriseLayout.setVisibility(View.VISIBLE);
            mWeatherSunrise.setText(getDateAsString(response.getWeatherList().get(0).getSys().getSunrise()));
        } else {
            mWeatherSunriseLayout.setVisibility(View.GONE);
        }

        // Sunset
        if (response.getWeatherList().get(0).getSys().getSunset() != 0.0) {
            mWeatherSunsetLayout.setVisibility(View.VISIBLE);
            mWeatherSunset.setText(getDateAsString(response.getWeatherList().get(0).getSys().getSunset()));
        } else {
            mWeatherSunsetLayout.setVisibility(View.GONE);
        }
        // Fill all attributes on getting a success response
    }

    public void onEvent(WeatherForecastSuccessEvent event) {
        Logger.i(TAG, "Weather forecast success");
        WeatherForecastResponse response = event.getResponse();
    }

    public void onEvent(WeatherForecastFailureResponse event) {
        Logger.i(TAG, "Weather forecast failure");
        Logger.i(TAG, "forecast failure response");
    }

    public void onEvent(GetWeatherFailureResponseEvent event) {
        Logger.i(TAG, "request failed");
    }

    private String getDateAsString(Long unixSeconds) {
        Date date = new Date(unixSeconds * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.US); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-7")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }
}
