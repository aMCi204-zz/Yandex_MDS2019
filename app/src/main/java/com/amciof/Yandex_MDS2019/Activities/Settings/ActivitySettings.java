package com.amciof.Yandex_MDS2019.Activities.Settings;


import android.preference.PreferenceActivity;
import android.os.Bundle;
import com.amciof.Yandex_MDS2019.R;



public class ActivitySettings
        extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
