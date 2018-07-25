package com.ksn.kraiponn.workshopdao.manager;

import android.content.Context;

public class SingletonTemplate {
    private static SingletonTemplate sInstance;

    public static SingletonTemplate getsInstance() {
        if (sInstance == null) {
            sInstance = new SingletonTemplate();
        }

        return sInstance;
    }

    private Context mContext;
    private SingletonTemplate() {
        Contextor.getsInstance().getContext();
    }


}
