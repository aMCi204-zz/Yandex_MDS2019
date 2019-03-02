package com.amciof.Yandex_MDS2019.ListOfApplications;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.amciof.Yandex_MDS2019.Profile.ActivityMain;
import com.amciof.Yandex_MDS2019.R;
import com.amciof.Yandex_MDS2019.Settings.ActivitySettings;
import com.amciof.Yandex_MDS2019.SlidePagerAdapter;
import com.amciof.Yandex_MDS2019.WelcomePage.ActivityWelcomePage;
import com.crashlytics.android.Crashlytics;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;
import com.microsoft.appcenter.push.Push;
import java.util.ArrayList;
import java.util.List;
import io.fabric.sdk.android.Fabric;



public class ActivityLauncher
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SETTINGS_NAME = "settings";

    public static final String KEY_WAS_STARTED = "was_started";

    public static final String KEY_THEME = "theme";
    public static final String THEME_LIGHT = "light";
    public static final String THEME_DARK = "dark";

    public static final String KEY_MODEL = "model";
    public static final String MODEL_STANDART = "standart";
    public static final String MODEL_DENSE = "dense";

    private ViewPager viewPager;

    private SharedPreferences sharedPreferences;

    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE);
        boolean wasStarted = sharedPreferences.getBoolean(KEY_WAS_STARTED, false);
        if (!wasStarted) {
            Intent intent = new Intent(this, ActivityWelcomePage.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        list.add(new FragmentGrid());
        list.add(new FragmentList());

        viewPager = findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.action_grid:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.action_list:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), ActivitySettings.class);
                startActivity(intent);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
