package com.ksn.kraiponn.workshopdao.manager;

import android.content.Context;

public class Contextor {
    private static Contextor sInstance;

    public static Contextor getsInstance() {
        if (sInstance == null) {
            sInstance = new Contextor();
        }

        return sInstance;
    }

    private Context mContext;
    private Contextor() {}

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

}
