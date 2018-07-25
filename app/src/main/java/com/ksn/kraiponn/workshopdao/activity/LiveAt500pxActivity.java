package com.ksn.kraiponn.workshopdao.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ksn.kraiponn.workshopdao.R;
import com.ksn.kraiponn.workshopdao.fragment.LoginFragment;
import com.ksn.kraiponn.workshopdao.fragment.MainLive500pxFragment;
import com.ksn.kraiponn.workshopdao.fragment.UserListFeedFragment;

public class LiveAt500pxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_at500px);

        initInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(
                            R.id.content_live500_container,
                            MainLive500pxFragment.newInstance())
                    .commit();
        }
    }

    private void initInstance() {
        //
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_retrofit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:{
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.from_bottom, R.anim.to_top,
                                R.anim.from_top, R.anim.to_bottom
                        )
                        .replace(R.id.content_live500_container,
                                LoginFragment.newInstance())
                        .commit();
                return true;
            }
            case R.id.action_live_500px:{
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.from_bottom, R.anim.to_top,
                                R.anim.from_top, R.anim.to_bottom
                        )
                        .replace(R.id.content_live500_container,
                                MainLive500pxFragment.newInstance())
                        .commit();
                return true;
            }
            case R.id.action_show_user_list:{
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.from_bottom, R.anim.to_top,
                                R.anim.from_top, R.anim.to_bottom
                        )
                        .replace(R.id.content_live500_container,
                                UserListFeedFragment.newInstance())
                        .commit();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
