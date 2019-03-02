package com.amciof.Yandex_MDS2019.ListOfApplications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.amciof.Yandex_MDS2019.R;
import com.amciof.Yandex_MDS2019.WelcomePage.ActivityWelcomePage;
import com.crashlytics.android.Crashlytics;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;
import com.microsoft.appcenter.push.Push;

import io.fabric.sdk.android.Fabric;

public class ActivityLauncher extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    public static final String SETTINGS_NAME = "settings";
    public static final String KEY_WAS_STARTED = "was_started";
    public static final String KEY_THEME = "theme";
    public static final String KEY_MODEL = "model";
    public static final String THEME_LIGHT = "light";
    public static final String THEME_DARK = "dark";
    public static final String MODEL_STANDART = "standart";
    public static final String MODEL_DENSE = "dense";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE);
        boolean wasStarted = sharedPreferences.getBoolean(KEY_WAS_STARTED, false);
        if (!wasStarted) {
            Intent intent = new Intent(this, ActivityWelcomePage.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (isChecked)
                    editor.putBoolean(KEY_WAS_STARTED, false);
                else
                    editor.putBoolean(KEY_WAS_STARTED, true);
                editor.apply();
            }
        });

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText(sharedPreferences.getString(KEY_THEME, "NOT EXIST"));

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(sharedPreferences.getString(KEY_MODEL, "NOT EXIST"));

        Fabric.with(this, new Crashlytics());
        AppCenter.start(
                getApplication(),
                "4f91900c-d92f-4bd0-be30-658a50f1e1bf",
                Push.class,
                Crashes.class,
                Analytics.class,
                Distribute.class
        );
    }
}
