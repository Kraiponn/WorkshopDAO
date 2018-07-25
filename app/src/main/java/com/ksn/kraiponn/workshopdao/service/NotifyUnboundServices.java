package com.ksn.kraiponn.workshopdao.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ksn.kraiponn.workshopdao.R;

public class NotifyUnboundServices extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String cmd = intent.getStringExtra("CMD");
        if (cmd.equals("Notification")) {
            Intent itn = new Intent(Intent.ACTION_VIEW);
            itn.setData(Uri.parse("http://www.developerthai.com"));

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    getBaseContext(), 1986,
                    itn, PendingIntent.FLAG_CANCEL_CURRENT
            );

            Notification.Builder builder = new Notification.Builder(getBaseContext())
                    .setSmallIcon(R.drawable.ic_finger)
                    .setContentTitle("การแจ้งเตือนรายวัน")
                    .setContentText("มีหนังสือเข้าใหม่ โดยอาจารย์บัญชา")
                    .setAutoCancel(false)
                    .setContentIntent(pendingIntent);
            Notification notification = builder.build();
            NotificationManager notifyManager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            notifyManager.notify(999, notification);
        }
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
