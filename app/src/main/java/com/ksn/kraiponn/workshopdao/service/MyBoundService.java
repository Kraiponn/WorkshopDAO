package com.ksn.kraiponn.workshopdao.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;

public class MyBoundService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder{
        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    public String getTimeService() {
        String time = "";
        Calendar cal = Calendar.getInstance();
        int hh = cal.get(Calendar.HOUR_OF_DAY);
        int mm = cal.get(Calendar.MINUTE);
        int ss = cal.get(Calendar.SECOND);

        time = hh + ":" + mm + ":" + ss;
        return time;
    }

}
