package com.ksn.kraiponn.workshopdao;

import android.app.Application;

import com.ksn.kraiponn.workshopdao.manager.Contextor;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getsInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
