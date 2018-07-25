package com.ksn.kraiponn.workshopdao.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.ksn.kraiponn.workshopdao.fragment.MyPreferenceFragment;


public class MyPreferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyPreferenceFragment setting = new MyPreferenceFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, setting)
                .commit();
    }
}
