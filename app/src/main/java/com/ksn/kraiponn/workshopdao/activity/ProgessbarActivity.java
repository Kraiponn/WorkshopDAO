package com.ksn.kraiponn.workshopdao.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ksn.kraiponn.workshopdao.R;
import com.ksn.kraiponn.workshopdao.service.MyBoundService;
import com.ksn.kraiponn.workshopdao.service.MyUnBoundService;

public class ProgessbarActivity extends AppCompatActivity {
    private LinearLayout layoutContent;
    private Button btnProcess;
    private TextView tvLoader;
    private ProgressBar progressBar;
    private Button btnPlayMusic;
    private Button btnPauseMusic;
    private Handler handler = new Handler();
    private int counter_state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progessbar);
        IntentFilter batteryFilter = new IntentFilter(
                "android.intent.action.BATTERY_CHANGED");
        IntentFilter pDisconnectFilter = new IntentFilter(
                "android.intent.action.ACTION_POWER_CONNECTED"
        );
        IntentFilter pConnectFilter = new IntentFilter(
                "android.intent.action.ACTION_POWER_DISCONNECTED"
        );
        registerReceiver(mReceiver, batteryFilter);
        registerReceiver(mReceiver, pConnectFilter);
        registerReceiver(mReceiver, pDisconnectFilter);

        initInstance();
    }


    private void initInstance() {
        layoutContent = findViewById(R.id.layoutContent);
        btnProcess = findViewById(R.id.btnProcess);
        progressBar = findViewById(R.id.progessBar);
        tvLoader = findViewById(R.id.tvTopicLoader);
        btnPlayMusic = findViewById(R.id.btnPlayMusic);
        btnPauseMusic = findViewById(R.id.btnPauseMusic);


        btnProcess.setOnClickListener(btnProcessClickListener);

        /*final Intent itn = new Intent(
                ProgessbarActivity.this, MyUnBoundService.class);
        itn.putExtra("song", R.raw.clean_bandit_solo);*/

        btnPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService(itn);
                /*btnPlayMusic.setEnabled(false);
                btnPauseMusic.setEnabled(true);*/
            }
        });
        btnPauseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stopService(itn);
                /*btnPlayMusic.setEnabled(true);
                btnPauseMusic.setEnabled(false);*/
            }
        });

        //init();
        //
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter_state < 100) {
                    counter_state++;
                    android.os.SystemClock.sleep(50);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(counter_state);
                        }
                    });
                }

                //counter_state = 0;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //tvLoader.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        layoutContent.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();
    }

    private void downLoad(View v) {
        counter_state = 0;
        layoutContent.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Log.d("Progess Start", "Please waiting...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter_state < 100) {
                    counter_state++;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(counter_state);
                            Log.d("Progess Start", "Counter_state " + counter_state);
                        }
                    });
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        layoutContent.setVisibility(View.VISIBLE);
                        Log.d("Progess Start", "Start successfully");
                    }
                });
            }
        }).start();
    }

    private void toastMessage(String text) {
        Toast.makeText(this,
                text,
                Toast.LENGTH_SHORT).show();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == Intent.ACTION_BATTERY_CHANGED) {
                int batteryLevel = intent.getIntExtra(
                        BatteryManager.EXTRA_LEVEL, 0
                );
                if (batteryLevel <= 30) {
                    toastMessage("Low batterry is " + batteryLevel + " %");
                }
            } else if (intent.getAction() == Intent.ACTION_POWER_CONNECTED) {
                toastMessage("The power is connecting...");
            } else if (intent.getAction() == Intent.ACTION_POWER_DISCONNECTED) {
                toastMessage("The power is disconnect...");
            }
        }
    };

    private MyBoundService myBoundService;
    private boolean mIsBound = false;

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIsBound = true;
            MyBoundService.LocalBinder binder =
                    (MyBoundService.LocalBinder) service;
            myBoundService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBound = false;
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(
                ProgessbarActivity.this,
                MyBoundService.class
        );
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConn);
    }

    View.OnClickListener btnProcessClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //downLoad(v);
            String str = "";
            if (mIsBound) {
                str = myBoundService.getTimeService();
            } else {
                str = "Service no connect";
            }
            toastMessage(str);
        }
    };

}
