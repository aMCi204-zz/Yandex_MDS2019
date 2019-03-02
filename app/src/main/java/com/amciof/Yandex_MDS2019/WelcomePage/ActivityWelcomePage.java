package com.amciof.Yandex_MDS2019.WelcomePage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher;
import com.amciof.Yandex_MDS2019.R;
import com.amciof.Yandex_MDS2019.WelcomePage.fragments.FragmentAuthor;
import com.amciof.Yandex_MDS2019.WelcomePage.fragments.FragmentApplication;
import com.amciof.Yandex_MDS2019.WelcomePage.fragments.FragmentTheme;
import com.amciof.Yandex_MDS2019.WelcomePage.fragments.FragmentModel;

import java.util.ArrayList;
import java.util.List;

import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.SETTINGS_NAME;
import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.KEY_WAS_STARTED;

public class ActivityWelcomePage extends AppCompatActivity {

    private ViewPager viewPager;

    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        list = new ArrayList<>();
        list.add(new FragmentAuthor());
        list.add(new FragmentApplication());
        list.add(new FragmentTheme());
        list.add(new FragmentModel());

        viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);
    }

    public void nextPage() {
        int currentPage = viewPager.getCurrentItem();
        if (currentPage == list.size() - 1) {
            SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_WAS_STARTED, true);
            editor.apply();
            Intent intent = new Intent(this, ActivityLauncher.class);
            startActivity(intent);
        } else {
            viewPager.setCurrentItem(currentPage + 1, true);
        }
    }
}
