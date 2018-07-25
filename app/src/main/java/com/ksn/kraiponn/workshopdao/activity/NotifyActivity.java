package com.ksn.kraiponn.workshopdao.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ksn.kraiponn.workshopdao.R;

import java.io.IOException;

public class NotifyActivity extends AppCompatActivity {
    private NotificationManager mNotifyManager;
    private final int REFF_ID = 99;
    private int counter = 0;

    private Button btnShowNotify;
    private Button btnCancelNotify;
    private ImageView imgShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        btnShowNotify = findViewById(R.id.btnShowNotify);
        btnCancelNotify = findViewById(R.id.btnCancelNotify);
        imgShow = findViewById(R.id.imgShow);

        btnCancelNotify.setOnClickListener(btnCancelClickListener);
        btnShowNotify.setOnClickListener(btnShowClickListener);
        imgShow.setOnClickListener(imgShowClickListener);
    }


    private void showNotify() {
        //
    }

    private void showProgressNotify() {
        final Notification.Builder builder = new Notification.Builder(getBaseContext())
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle("Download file...");

        final Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Log.d("Progress_notify", "Start notify " + counter);
                if (counter <= 100) {
                    builder.setProgress(100, counter, false);
                    builder.setContentText(counter + " %");
                    handler.postDelayed(this, 50);
                } else {
                    builder.setContentText("Download successfully");
                    counter = 0;
                    handler.removeCallbacks(this);
                }

                counter++;
                Notification notification = builder.build();
                mNotifyManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                mNotifyManager.notify(REFF_ID, notification);
            }
        };

        handler.postDelayed(r, 50);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1809 && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), path
                );
                imgShow.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    View.OnClickListener imgShowClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent viewIntent = new Intent();
            viewIntent.setAction(Intent.ACTION_GET_CONTENT);
            viewIntent.setType("image/*");
            Intent chooserIntent =
                    Intent.createChooser(viewIntent, "Select target");
            PackageManager manager = getPackageManager();
            if (viewIntent.resolveActivity(manager) != null) {
                //startActivity(chooserIntent);
                startActivityForResult(chooserIntent, 1809);
            }

            //startActivity(viewIntent);
        }
    };

    View.OnClickListener btnShowClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showNotify();
            //showProgressNotify();
        }
    };

    View.OnClickListener btnCancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //mNotifyManager.cancel(REFF_ID);
            showProgressNotify();
        }
    };


}
