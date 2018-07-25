package com.ksn.kraiponn.workshopdao.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class NotifyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent itn = new Intent(context, NotifyUnboundServices.class);
        itn.putExtra("CMD", "Notification");

        if (intent.getAction() == Intent.ACTION_POWER_DISCONNECTED) {
            Log.d("CheckBroadcast", "ACTION_POWER_DISCONNECTED");
            context.startService(itn);
        } else if (intent.getAction() == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("CheckBroadcast", "BOOT_COMPLETEED");
            context.startService(itn);
        }
    }
}
