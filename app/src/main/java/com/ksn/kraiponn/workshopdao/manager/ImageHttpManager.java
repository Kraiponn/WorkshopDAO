package com.ksn.kraiponn.workshopdao.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.ksn.kraiponn.workshopdao.manager.http.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageHttpManager {
    private static ImageHttpManager sInstance;
    private final String localUrl = "http://192.168.79.1/ATOM/retrofit/";
    private final String onlineUrl = "http://ksnajaroon.com/nurse_note/";

    public static ImageHttpManager getsInstance() {
        if (sInstance == null) {
            sInstance = new ImageHttpManager();
        }

        return sInstance;
    }

    private Context mContext;
    private ApiService service;

    private ImageHttpManager() {
        Contextor.getsInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(onlineUrl)
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
