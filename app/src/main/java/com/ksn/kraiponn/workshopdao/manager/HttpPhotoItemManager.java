package com.ksn.kraiponn.workshopdao.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ksn.kraiponn.workshopdao.manager.http.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpPhotoItemManager {
    private static HttpPhotoItemManager sInstance;

    public static HttpPhotoItemManager getsInstance() {
        if (sInstance == null) {
            sInstance = new HttpPhotoItemManager();
        }

        return sInstance;
    }

    private Context mContext;
    private ApiService service;

    private HttpPhotoItemManager() {
        Contextor.getsInstance().getContext();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nuuneoi.com/courses/500px/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);

    }

    public ApiService getService() {
        return service;
    }

    public void setService(ApiService service) {
        this.service = service;
    }
}
