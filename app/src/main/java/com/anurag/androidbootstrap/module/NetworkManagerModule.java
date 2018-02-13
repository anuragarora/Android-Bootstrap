package com.anurag.androidbootstrap.module;

import com.anurag.androidbootstrap.R;
import com.anurag.androidbootstrap.network.NetworkManager;
import com.anurag.androidbootstrap.network.RetrofitNetworkManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.anurag.androidbootstrap.module.ApplicationModule.resources;
import static com.anurag.androidbootstrap.module.ConverterModule.gsonConverter;

/**
 * Network Manager Module
 */
public class NetworkManagerModule {
    private static final int TIMEOUT = 60;
    private static final String TAG = NetworkManagerModule.class.getSimpleName();
    private static OkHttpClient sOkClient;

    public static NetworkManager retrofitNetworkManager() {
        return new RetrofitNetworkManager(okClient(),
                resources().getString(R.string.base_url),
                gsonConverter(), resources());
    }

    private static OkHttpClient okClient() {
        if (sOkClient == null) {
            sOkClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }

        return sOkClient;
    }
}
