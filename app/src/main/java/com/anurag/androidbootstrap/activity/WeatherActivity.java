package com.anurag.androidbootstrap.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.anurag.androidbootstrap.R;
import com.anurag.androidbootstrap.event.GetWeatherFailureResponseEvent;
import com.anurag.androidbootstrap.event.GetWeatherSuccessResponseEvent;
import com.anurag.androidbootstrap.event.WeatherForecastFailureResponse;
import com.anurag.androidbootstrap.event.WeatherForecastSuccessEvent;
import com.anurag.androidbootstrap.fetcher.WeatherFetcher;
import com.anurag.androidbootstrap.fetcher.WeatherForecastFetcher;
import com.anurag.androidbootstrap.model.weather.WeatherResponse;
import com.anurag.androidbootstrap.util.BackgroundHelper;
import com.anurag.androidbootstrap.util.Logger;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

import static com.anurag.androidbootstrap.module.ApplicationModule.getFusedLocationClient;
import static com.anurag.androidbootstrap.module.EventBusModule.eventBus;
import static com.anurag.androidbootstrap.module.FetcherModule.WeatherFetcher;
import static com.anurag.androidbootstrap.module.FetcherModule.WeatherForecastFetcher;

public class WeatherActivity extends BaseActivity implements OnSuccessListener<Location>,
        OnFailureListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final int PERMISSION_REQUEST_CODE_COARSE_LOCATION = 0;
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

    @BindView(R.id.activity_weather_coordinator)
    public CoordinatorLayout mCoordinator;

    private final WeatherFetcher mWeatherFetcher;
    private final EventBus mEventBus;
    private final WeatherForecastFetcher mWeatherForecastFetcher;

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
        //mWeatherFetcher.getWeatherByCityName("Tempe");

        mViewSwitcher.setBackgroundResource(BackgroundHelper.getBackground());
        //Logger.i(TAG, "Calling weather by cityname");
        //mWeatherFetcher.getWeatherByCityName("Tempe");
        //mWeatherForecastFetcher.getWeatherForecastByGeoLocation("33.424564", "-111.94");

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        } else {
            getLastLocation();
        }
    }

    /**
     * Check that permission has been checked for at every place before this function call
     * is performed
     */
    private void getLastLocation() {
        @SuppressLint("MissingPermission")
        Task<Location> cityLocation = getFusedLocationClient().getLastLocation();
        if (cityLocation!=null) {
            cityLocation.addOnSuccessListener(this);
            cityLocation.addOnFailureListener(this);
        } else{
            getWeatherFallback();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE_COARSE_LOCATION){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                getWeatherFallback();
            }
        }
    }

    private void getWeatherFallback() {
        Snackbar.make(mCoordinator, "Permission not granted. Fallback to Tempe, AZ weather",
                Snackbar.LENGTH_SHORT).show();
        mWeatherFetcher.getWeatherByCityName("Tempe");
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Snackbar.make(mCoordinator, "Need location for fetching weather info",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(WeatherActivity.this,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE_COARSE_LOCATION);
                }
            }).show();
        } else {
            ActivityCompat.requestPermissions(WeatherActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE_COARSE_LOCATION);
            /*Snackbar.make(mCoordinator, "Location permission denied. Fallback to Tempe Weather",
                    Snackbar.LENGTH_SHORT).show();*/
        }
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
    }

    public void onEvent(WeatherForecastFailureResponse event) {
        Logger.i(TAG, "Weather forecast failure");
    }

    public void onEvent(GetWeatherFailureResponseEvent event) {
        Toast.makeText(this, "Failed to retrieve weather information", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Location location) {
        if (location != null) {
            mWeatherFetcher.getWeatherByGeoData(String.valueOf(location.getLatitude()),
                    String.valueOf(location.getLongitude()));
        }
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        //onEvent(new GetWeatherFailureResponseEvent());
        //Toast.makeText(this, "Requesting Failed! Fallback location: Tempe, AZ",
        //        Toast.LENGTH_SHORT).show();
        //mWeatherFetcher.getWeatherByCityName("Tempe");
        getWeatherFallback();
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
