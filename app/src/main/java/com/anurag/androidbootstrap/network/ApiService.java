package com.anurag.androidbootstrap.network;

import com.anurag.androidbootstrap.model.Owner;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Anurag_Arora on 3/2/2016.
 */
public interface ApiService {
    @GET("https://api.github.com/repos/anuragarora/Android-Bootstrap")
    Call<Owner> getRepoInfo();
}
