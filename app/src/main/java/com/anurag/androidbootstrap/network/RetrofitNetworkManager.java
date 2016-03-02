package com.anurag.androidbootstrap.network;

import com.anurag.androidbootstrap.model.Repo;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by anurag on 02/03/16.
 */
public class RetrofitNetworkManager implements NetworkManager {
    private final OkHttpClient mOkClient;
    private final String mBaseUrl;

    public RetrofitNetworkManager(OkHttpClient okClient, String baseUrl){
        mOkClient = okClient;
        mBaseUrl = baseUrl;
    }

    private Retrofit.Builder getRetrofitBuilder(){
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(mOkClient);
    }

    @Override
    public void getAndroidBootstrapRepoInfo(Call<Repo> callback) {
        getRetrofitBuilder()
                .addConverterFactory(mRepoInfoConverter)
                .build()
                .create(ApiService.class)
                .getRepoInfo()
                .enqueue(callback);
    }
}
