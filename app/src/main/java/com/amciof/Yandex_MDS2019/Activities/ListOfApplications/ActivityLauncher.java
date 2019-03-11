package com.amciof.Yandex_MDS2019.Activities.ListOfApplications;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.amciof.Yandex_MDS2019.Activities.Profile.ActivityProfile;
import com.amciof.Yandex_MDS2019.R;
import com.amciof.Yandex_MDS2019.Activities.Settings.ActivitySettings;
import com.amciof.Yandex_MDS2019.SPHelper;
import com.amciof.Yandex_MDS2019.SlidePagerAdapter;
import com.amciof.Yandex_MDS2019.Activities.WelcomePage.ActivityWelcomePage;
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
        implements OnNavigationItemSelectedListener, GetInstalledApplications{

    private PackageManager packageManager;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        boolean willStart = SPHelper.getBoolean(this, SPHelper.WAS_STARTED, true);

        if (willStart) {
            Intent intent = new Intent(this, ActivityWelcomePage.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityProfile.class);
                startActivity(intent);
            }
        });

        List<Fragment> fragmentsList = new ArrayList<>();
        fragmentsList.add(new FragmentGrid());
        fragmentsList.add(new FragmentList());

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new SlidePagerAdapter(getSupportFragmentManager(), fragmentsList));

        packageManager = getPackageManager();

        Fabric.with(this, new Crashlytics());

        AppCenter.start(getApplication(), "4f91900c-d92f-4bd0-be30-658a50f1e1bf",
                Push.class, Crashes.class, Analytics.class, Distribute.class
        );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
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
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public List<ApplicationInfo> getInstalledApplications() {
        List<ApplicationInfo> applicationsList = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA);

        for (ResolveInfo resolveInfo : availableActivities) {
            CharSequence label = resolveInfo.loadLabel(packageManager);

            CharSequence packageName = resolveInfo.activityInfo.packageName;

            Drawable icon = resolveInfo.loadIcon(packageManager);

            long firstInstallTime;
            try {
                PackageInfo pi = packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, PackageManager.GET_META_DATA);
                firstInstallTime = pi.firstInstallTime;
            } catch (PackageManager.NameNotFoundException e) {
                firstInstallTime = 0;
            }

            Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName.toString());

            Intent settingsIntent = new Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + packageName.toString())
            );
            settingsIntent.addCategory(Intent.CATEGORY_DEFAULT);
            settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Intent deleteIntent = new Intent(
                    Intent.ACTION_DELETE,
                    Uri.parse("package:" + packageName.toString())
            );

            applicationsList.add(new ApplicationInfo(
                    label,
                    packageName,
                    icon,
                    firstInstallTime,
                    0,
                    launchIntent,
                    settingsIntent,
                    deleteIntent
            ));
        }

        return applicationsList;
    }

}
