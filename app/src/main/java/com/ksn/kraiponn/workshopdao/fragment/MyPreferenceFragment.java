package com.ksn.kraiponn.workshopdao.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.ksn.kraiponn.workshopdao.R;

public class MyPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);

        onPreferenceChangeValue();
    }

    private void onPreferenceChangeValue() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(
                getActivity()
        );

        pref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                boolean check_update =
                        sharedPreferences.getBoolean("update_check", false);
                boolean status_check =
                        sharedPreferences.getBoolean("status_check", true);
                String webste =
                        sharedPreferences.getString("website",
                                "http://www.ksnajaroon.com");

                Preference prefUpdate = findPreference("update_check");
                Preference prefStatus = findPreference("status_check");
                Preference prefWebSite = findPreference("website");
                if (check_update == true) {
                    prefUpdate.setSummary("Update your profile");
                } else {
                    prefUpdate.setSummary("No update");
                }

                if (status_check == true) {
                    prefStatus.setSummary("Open your profile");
                } else {
                    prefStatus.setSummary("Do not open profile");
                }

                prefWebSite.setSummary(webste);

            }
        });
    }
}
