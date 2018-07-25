package com.ksn.kraiponn.workshopdao.manager;

import android.content.Context;

import com.ksn.kraiponn.workshopdao.manager.http.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUserListManager {
    private static HttpUserListManager sInstance;

    public static HttpUserListManager getsInstance() {
        if (sInstance == null) {
            sInstance = new HttpUserListManager();
        }

        return sInstance;
    }

    private Context mContext;
    private ApiService service;

    private HttpUserListManager() {
        Contextor.getsInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksnajaroon.com/nurse_note/")
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
