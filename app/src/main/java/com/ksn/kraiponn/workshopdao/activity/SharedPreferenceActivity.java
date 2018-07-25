package com.ksn.kraiponn.workshopdao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ksn.kraiponn.workshopdao.R;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        settingSharedPreference();
    }

    private void settingSharedPreference() {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Log.d("DEBUG", "Prefeence is OK");
            startActivity(new Intent(
                    SharedPreferenceActivity.this,
                    MyPreferenceActivity.class
            ));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
