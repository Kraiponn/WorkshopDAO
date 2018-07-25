package com.ksn.kraiponn.workshopdao.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    //private String mBroadcast = "";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == Intent.ACTION_POWER_CONNECTED) {
            toastMessage("The power is connecting...",
                    context.getApplicationContext());
        } else if (intent.getAction() == Intent.ACTION_POWER_DISCONNECTED) {
            toastMessage("The power disconnect...",
                    context.getApplicationContext());
        } else if (intent.getAction() == Intent.ACTION_BATTERY_CHANGED) {
            int battery = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            if (battery <= 30) {
                toastMessage("A battery lower than " + battery + "%",
                        context.getApplicationContext());
            }
        }
    }

    private void toastMessage(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
